import java.util.*;

class GraphCon {
	public static void main(String[] args) {
		
	}
	//actually is a array problem with HashMap in use 
	//nums = [100, 1, 200, 3, 4, 2] return 4 because of 1, 2, 3, 4
	public int longestConsecutive(int[] nums) {
		if (nums == null || nums.length == 0){
			return 0;
		}
		Map<Integer, Integer> map = new HashMap<>();
		int max = 0;
		for (int num : nums){
			if (!map.containsKey(num)){
				int left = map.getOrDefault(num - 1, 0);
				int right = map.getOrDefault(num + 1, 0);
				int sum = left + right + 1;
				map.put(num, sum);
				max = Math.max(sum, max);
				map.put(num - left, sum);
				map.put(num + right, sum);
			}
			else{
				continue;
			}
			return max;
		}
			
	}
	
	//word Search
	public List<String> findWords(char[][] board, String[] words) {
		Set<String> res = new HashSet<>();
		for (String word : words){
			boolean [][] visited = new boolean[board.length][board[0].length];
			for (int i = 0; i < board.length; i++){
				for (int j = 0; j < board[0].length;j++){
					if (dfs (i, j, word, board, 0, visited)){
						res.add(word);
					}
				}
			}
		}
		return new ArrayList<>(res);
	}
	private boolean dfs(int x, int y, String word, char[][] board, int index, boolean [][] visited){
		if (word.length() == index) return true;
		if (x < 0 || x == board.length || y < 0 || y == board[0].length || visited[x][y] || word.charAt(index) != board[x][y]){
			return false;
		}
		visited[x][y] = true;
		if (dfs(x + 1, y , word, board, index + 1, visited) ||
			dfs(x - 1, y , word, board, index + 1, visited) ||
			dfs(x , y + 1, word, board, index + 1, visited) ||
			dfs(x , y - 1, word, board, index + 1, visited)){
			return true;
		}
		visited[x][y] = false;
		return false;
	}
	//Dijkstra Alorgithm
	public int networkDelayTime(int[][] times, int N, int K) {
		Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
		for (int [] time : times){
			map.putIfAbsent(time[0], new HashMap<Integer, Integer>());
			map.get(time[0]).put(time[1], time[2]);
		}
		Queue<int []> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
		pq.offer(new int[]{0, K});
		boolean [] visited = new boolean [N + 1];
		int res = 0;
		while(!pq.isEmpty()){
			int [] cur = pq.remove();
			int dist = cur[0];
			int node = cur[1];
			if (visited[node]) continue;
			visited[node] = true;
			res = dist;
			N--;
			if (map.containsKey(node)){
				for (int next : map.get(node).keySet()){
					pq.add(new int []{dist + map.get(node).get(next), next});
				}
			}
		}
		return N == 0 ? res : -1;
	}
	/* Word Ladder I*/
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		if (!wordList.contains(endWord)) {
			return 0;
		}
		Set<String> dict = new HashSet<>(wordList);
		Set<String> beginSet = new HashSet<>();
		Set<String> endSet = new HashSet<>();
		beginSet.add(beginWord);
		endSet.add(endWord);

		int step = 1;
		Set<String> visited = new HashSet<>();
		while (!beginSet.isEmpty() && !endSet.isEmpty()) {
			if (beginSet.size() > endSet.size()) {
				Set<String> set = beginSet;
				beginSet = endSet;
				endSet = set;
			}
			Set<String> temp = new HashSet<>();
			for (String word : beginSet) {
				for (int i = 0; i < word.length(); i++) {
					char[] chs = word.toCharArray();
					for (char c = 'a'; c <= 'z'; c++) {
						chs[i] = c;
						String target = String.valueOf(chs);
						if (endSet.contains(target)) {
							return step + 1;
						}
						if (!visited.contains(target) && dict.contains(target)) {
							temp.add(target);
							visited.add(target);
						}
					}
				}
			}
			beginSet = temp;
			step++;
		}
		return 0;
	}

	//Minimum Height Trees
	int n;
	List<Integer>[] e;

	private void bfs(int start, int[] dist, int[] pre) {
		boolean[] visited = new boolean[n];
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		dist[start] = 0;
		visited[start] = true;
		pre[start] = -1;
		while (!queue.isEmpty()) {
			int u = queue.poll();
			for (int v : e[u])
				if (!visited[v]) {
					visited[v] = true;
					dist[v] = dist[u] + 1;
					queue.add(v);
					pre[v] = u;
				}
		}
	}

	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		if (n <= 0) return new ArrayList<>();
		this.n = n;
		e = new List[n];
		for (int i = 0; i < n; i++)
			e[i] = new ArrayList<>();
		for (int[] pair : edges) {
			int u = pair[0];
			int v = pair[1];
			e[u].add(v);
			e[v].add(u);
		}

		int[] d1 = new int[n];
		int[] d2 = new int[n];
		int[] pre = new int[n];
		bfs(0, d1, pre);
		int u = 0;
		for (int i = 0; i < n; i++)
			if (d1[i] > d1[u]) u = i;

		bfs(u, d2, pre);
		int v = 0;
		for (int i = 0; i < n; i++)
			if (d2[i] > d2[v]) v = i;

		List<Integer> list = new ArrayList<>();
		while (v != -1) {
			list.add(v);
			v = pre[v];
		}

		if (list.size() % 2 == 1) return Arrays.asList(list.get(list.size() / 2));
		else return Arrays.asList(list.get(list.size() / 2 - 1), list.get(list.size() / 2));
	}

	
	//union find find number of connected compnenets in undirected graph
	private int [] union;
	public int countComponents(int n, int [][] edges){
		union = new int [n];
		for (int i = 0;i < n; i++){
			union[i] = i;
		}
		for (int [] edge : edges){
			int node1 = find(edge[0]);
			int node2 = find(edge[1]);
			if (node1 != node2){
				union[node1] = node2;
				n--;
			}
		}
		return n;
	}
	public int findcC(int node){
		if (union[i] == i){
			return i;
		}
		return find(union[i]);
	}
	//dfs
	public int countComponentsDFS(int n, int [][] edges){
		List<List<Integer>> neighbors = new ArrayList<>();
		for (int i = 0; i < n; i++){
			neighbors.add(new ArrayList<Integer>());
		}
		for (int [] edge : edges){
			neighbors.get(edge[0]).add(edge[1]);
			neighbors.get(edge[1]).add(edge[0]);
		}
		boolean [] visited = new boolean[n];
		int count = 0;
		for (int i = 0; i < n;i++){
			if (!visited[i]){
				count++;
			}
			cCDfs(i, visited, neighbors);
		}
		return count;
	}
	private void cCDfs(int index, boolean [] visited, List<List<Integer>> neighbors ){
		visited[index] = true;
		for (int nei : neighbors.get(index)){
			if (!visited[nei]){
				cCDfs(nei, visited, neighbors);
			}
		}
	}	
	//bfs
	public int countComponentsBfs(int n, int [][] edges){
		if (n <= 1) {
			return n;
		}
		List<List<Integer>> neighbors = new ArrayList<List<Integer>>();
		for (int i = 0; i < n; i++) {
			neighbors.add(new ArrayList<Integer>());
		}
		for (int[] edge : edges) {
			neighbors.get(edge[0]).add(edge[1]);
			neighbors.get(edge[1]).add(edge[0]);
		}
		
		int count = 0;
		boolean[] visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
                count++;
				Queue<Integer> queue = new LinkedList<Integer>();
				queue.offer(i);
				while (!queue.isEmpty()) {
					int index = queue.poll();
					visited[index] = true;
					for (int next : neighbors.get(index)) {
						if (!visited[next]) {
							queue.offer(next);
						}
					}
				}
			}
		}
        return count;
	}		
		
	Map<Node,Node> map = new HashMap<>();
	public Node copyRandomList(Node head){
		Node cur = head;
		while(cur != null){
			map.put(cur, new Node(cur.val));
			cur = cur.next;
			
		}
		cur = head;
		while(cur != null){
			map.get(cur).next = map.get(cur.next);
			map.get(cur).random = map.get(cur.random);
			cur = cur.next;
		}
		return map.get(head);
		
	}
	//bfs
	public boolean validTreeBFS(int n, int[][] edges) {
		// n must be at least 1
		if (n < 1) return false;

		// create hashmap to store info of edges
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for (int i = 0; i < n; i++) map.put(i, new HashSet<>());
		for (int[] edge : edges) {
			map.get(edge[0]).add(edge[1]);
			map.get(edge[1]).add(edge[0]);
		}

		// bfs starts with node in label "0"
		Set<Integer> set = new HashSet<>();
		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);
		while (!queue.isEmpty()) {
			int top = queue.remove();
			// if set already contains top, then the graph has cycle
			// hence return false
			if (set.contains(top)) return false;

			for (int node : map.get(top)) {
				queue.add(node);
				// we should remove the edge: node -> top
				// after adding a node into set to avoid duplicate
				// since we already consider top -> node
				map.get(node).remove(top);
			}
			set.add(top);
		}
		return set.size() == n;
	}
	//union find
	private int [] union;
	public boolean validTreeUF(int n, int[][] edges) {
		
		return unionFind(n, edges);
	}
	public boolean unionFind(int n, int[][] edges){
		union = new int[n];
		for (int i = 0; i < n; i++){
			union[i] = i;
		}
		for (int[] edge : edges){
			if (find(edge[0]) == find(edge[1])){
				return false;
			}
			unions(edge[0], edge[1]);
		}
		return edges.length == n - 1;
	}
	public int find (int node){
		if (union[node] == node){
			return node;
		}
		return find(union[node]);
	}
	public void unions(int node1, int node2){
		int n1 = find(node1);
		int n2 = find(node2);
		union[n1] = n2;
	}

	//course schedule bfs
	public boolean canFinishBFS(int n, int[][] prerequisites) {
		List<Integer> [] goCourses = new List[n];
		int [] inDegrees = new int[n];
		for (int i = 0;i < n; i++){
			goCourses[i] = new ArrayList<>();
		}
		for (int [] pair : prerequisites){
			inDegrees[pair[0]]++;
			goCourses[pair[1]].add(pair[0]);
		}
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0;i < n;i++){
			if (inDegrees[i] == 0){
				q.offer(i);
			}
		}
		int edgeCnt = prerequisites.length;
		while(!q.isEmpty()){
			int course = q.poll();
			for (int next : goCourses[course]){
				edgeCnt--;
				if (--inDegrees[next] == 0){
					q.offer(next);
				}
			}
		}
		return edgeCnt == 0;
	}
	//dfs
	public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
		ArrayList[] graph = new ArrayList[numCourses];
		for(int i=0;i<numCourses;i++)
			graph[i] = new ArrayList();
			
		boolean[] visited = new boolean[numCourses];
		for(int i=0; i<prerequisites.length;i++){
			graph[prerequisites[i][1]].add(prerequisites[i][0]);
		}

		for(int i=0; i<numCourses; i++){
			if(!dfs(graph,visited,i))
				return false;
		}
		return true;
	}

	private boolean dfs(ArrayList[] graph, boolean[] visited, int course){
		if(visited[course])
			return false;
		else
			visited[course] = true;;

		for(int i=0; i<graph[course].size();i++){
			if(!dfs(graph,visited,(int)graph[course].get(i)))
				return false;
		}
		visited[course] = false;
		return true;
	}
	//return the path
	public int[] findOrder(int n, int[][] prerequisites) {
		int [] res = new int [n];
		List<Integer> [] goCourses = new List [n];
		int [] indegrees = new int [n];
		
		for (int i = 0; i < n;i++){
			goCourses[i] = new ArrayList<>();
		}
		for (int [] pair : prerequisites){
			goCourses[pair[1]].add(pair[0]);
			indegrees[pair[0]]++;
		}
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < n;i++){
			if (indegrees[i] == 0){
				q.offer(i);
			}
		}
		int cntEdges = prerequisites.length, i = 0;
		while(!q.isEmpty()){
			int course = q.poll();
			res[i++] = course;
			for (int next : goCourses[course]){
				cntEdges--;
				if (--indegrees[next] == 0){
					q.offer(next);
				}
			}
		}
		return cntEdges == 0 ? res : new int [0];
	}
	
	//graph dfs
	private Map<Node, Node> map = new HashMap<>();
	public Node cloneGraph(Node node){
		if (node == null) return null;
		if (map.containsKey(node)) return map.get(node);
		Node root = new Node(node.val);
		map.put(node, root);
		root.neighbors = new ArrayList<>();
		for (Node nei : node.neighbors){
			root.neighbors.add(cloneGraph(nei));
		}
		return root;
	}
	//graph bfs
	private Map<Node, Node> copied = new HashMap<>();
	public Node cloneGraph(Node node){
		if (node == null) return null;
		
		Queue<Node> q = new LinkedList<>();
		q.add(node);
		Node root = new Node(node.val, new ArrayList<>());
		copied.put(node, root);
		while(!q.isEmpty()){
			Node cur = q.poll();
			for (Node nei : cur.neighbors){
				if (!copied.containsKey(nei)){
					copied.put(nei, new Node(nei.val, new ArrayList<>()));
					q.add(nei);
				}
				copied.get(cur).neighbors.add(copied.get(nei));
			}
		}
		return root;
	}	
}
class Node{
	public int val;
	public List<Node> neighbors;
	public Node(){
		
	}
	public Node(int val, List<Node> neighbors){
		this.val = val;
		this.neighbors = neighbors;
	}
}