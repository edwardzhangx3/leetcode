class TrieCon {
	public static void main(String[] args) {
		
	}
	Set<String> set = new HashSet<>();
	public List<String> findWords(char[][] board, String[] words) {
		Trie trie = new Trie();
		for (String word : words){
			trie.insert(word);
		}
		int m = board.length;
		int n = board[0].length;
        boolean [][] visited = new boolean[m][n];
		for (int i = 0;i < m;i++){
			for (int j = 0;j < n;j++){
				dfs(board, i, j, trie, visited, "");
			}
		}
		return new ArrayList<>(set);
	} 
	public void dfs(char[][] board, int x, int y, Trie trie, boolean[][] visited, String str){
		if (x < 0 || x == board.length || y < 0 || y == board[0].length || visited[x][y]) return;
		str += board[x][y];
		if (!trie.startsWith(str)) return;
		
		if (trie.search(str)) set.add(str);
		visited[x][y] = true;
		dfs(board, x + 1, y, trie, visited, str);
		dfs(board, x - 1, y, trie, visited, str);
		dfs(board, x, y - 1, trie, visited, str);
		dfs(board, x, y + 1, trie, visited, str);
		visited[x][y] = false;
	}

}
class TrieNode {
	public TrieNode[] children = new TrieNode[26];
	public String item = "";
	
	// Initialize your data structure here.
	public TrieNode() {
	}
}

class Trie {
	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	// Inserts a word into the trie.
	public void insert(String word) {
		TrieNode node = root;
		for (char c : word.toCharArray()) {
			if (node.children[c - 'a'] == null) {
				node.children[c - 'a'] = new TrieNode();
			}
			node = node.children[c - 'a'];
		}
		node.item = word;
	}

	// Returns if the word is in the trie.
	public boolean search(String word) {
		TrieNode node = root;
		for (char c : word.toCharArray()) {
			if (node.children[c - 'a'] == null) return false;
			node = node.children[c - 'a'];
		}
		return node.item.equals(word);
	}

	// Returns if there is any word in the trie
	// that starts with the given prefix.
	public boolean startsWith(String prefix) {
		TrieNode node = root;
		for (char c : prefix.toCharArray()) {
			if (node.children[c - 'a'] == null) return false;
			node = node.children[c - 'a'];
		}
		return true;
	}
	
	//search the string with '.' which means that '.' can represent any letters
	public boolean searchSpecial(String word){
		return match(word.toCharArray(), 0, root);
	}
	private boolean match(char[] chs, int k, TireNode node){
		if (chs.length == k) return !node.item.equals("");
		if (chs[k] != '.'){
			return node.children[chs[k] - 'a'] != null && match(chs, k + 1, node.children[chs[k] - 'a']);
		}else{
			for (TrieNode n : node.children[chs[k] - 'a']){
				if (n != null && match(chs, k + 1, n)){
					return true;
				}
			}
		}
		return false;
	}
}