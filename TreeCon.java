import java.util.*;
class TreeCon {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(6);
		root.left.right.left = new TreeNode(10);
		root.left.right.right = new TreeNode(13);
		root.left.right.left.left = new TreeNode(14);
		root.right.left = new TreeNode(8);
		
		List<String> paths = BinaryTreePaths(root);
		System.out.println("All the paths from root to leaf: " + paths);
		System.out.println("The max depth of the tree: " + maxDepth(root));
		System.out.println("The min depth of the tree: " + minDepth(root));
		System.out.println("The minimum subtree is: " + findMinSub(root));
		System.out.println("Binary tree is balanced: " + isBalanced(root));
		System.out.println("Max average subtree is: " + maximumAverageSubtree(root));
		System.out.println("The longest consecutive sequence's length is: " + longestConsecutive(root));
		System.out.println("Binary Tree sum path: " + binaryTreePathSum(root, 12));
		System.out.println("Check if the binary tree is bst " + isBST(root));
		System.out.println("level order of tree: " + levelOrder(root));
		System.out.println("level order of tree in dfs: " + levelOrderDfs(root));
		System.out.println("level order from bottom dfs: " + levelOrderBottomDfs(root));
		System.out.println("level order from bottom bfs: " + levelOrderBottomBfs(root));
		System.out.println("level order in a zigzag way of a tree" + zigzagLevelOrder(root));
		System.out.println("The vertical order traverse of a tree: " + verticalOrder(root));
		System.out.println("right side view" + rightSideView(root));
		System.out.print("Preorder of a tree recursively: ");
		preorder(root);
		System.out.println("");
		System.out.print("Inorder of a tree recursively: ");
		inorder(root);
		System.out.println("");
		System.out.print("Postorder of a tree recursively: ");
		postorder(root);
		System.out.println("");
	}
	
	int max = 0;
	public int longestConsecutive(TreeNode root) {
		helper(root);
		return max;
	}
	public int[] helper(TreeNode root){
		if(root == null) return new int[]{0,0};
		int[] left = helper(root.left);
		int[] right= helper(root.right);
		int inc = 1, des = 1;
		if(root.left != null){
			if(root.val - root.left.val == 1){
				des = left[1]+1;
			}else if(root.val - root.left.val == -1){
				inc = left[0]+1;
			}
		}
		if(root.right != null){
			if(root.val - root.right.val == 1){
				des = Math.max(des,right[1]+1);
			}else if(root.val - root.right.val == -1){
				inc = Math.max(inc,right[0]+1);
			}
		}
		max = Math.max(max,inc+des-1);
		return new int[]{inc,des};
	}
		
	public static int widthOfBinaryTree(TreeNode root) {
		
			
	} 
	public static boolean isBST(TreeNode root){
		return bstHelper(root, Long.MAX_VALUE, Long.MIN_VALUE);
	}
	public static boolean bstHelper(TreeNode root, long max, long min){
		if (root == null){
			return true;
		}
		if (root.val >= max || root.val <= min) return false;
		boolean left = bstHelper(root.left, root.val, min);
		boolean right = bstHelper(root.right, max, root.val);
		return left && right;
		
	}
	public static List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		while(!q.isEmpty()){
			int levelNum = q.size();
			List<Integer> level = new ArrayList<>();
			for (int i = 0; i < levelNum; i++){
				TreeNode node = q.poll();
				if (node.left != null) q.add(node.left);
				if (node.right != null) q.add(node.right);
				level.add(node.val);
			}
			res.add(level);
		}
		return res;
			
	}
	public static List<List<Integer>> levelOrderDfs(TreeNode root){
		List<List<Integer>> res = new ArrayList<>();
		levelOrderHelper(root, 0, res);
		return res;
	}
	public static void levelOrderHelper(TreeNode root, int level, List<List<Integer>> ans){
		if (root == null) return;
		if (level == ans.size()){
			ans.add(new ArrayList<>());
		}
		ans.get(level).add(root.val);
		levelOrderHelper(root.left, level + 1, ans);
		levelOrderHelper(root.right, level + 1, ans);

	}
	public static List<List<Integer>> levelOrderBottomDfs(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		levelOrderBottomHelper(root, 0, res);
		return res;
			
	}
	public static void levelOrderBottomHelper(TreeNode root, int level, List<List<Integer>> res){
		if (root == null) return;
		if (res.size() == level){
			res.add(0, new ArrayList<Integer>());
		}
		res.get(res.size() - level - 1).add(root.val);
		levelOrderBottomHelper(root.left, level + 1, res);
		levelOrderBottomHelper(root.right, level + 1, res);
		
	}
	public static List<List<Integer>> levelOrderBottomBfs(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		List<List<Integer>> res = new LinkedList<List<Integer>>();
			
		if(root == null) return res;
			
		queue.offer(root);
		while(!queue.isEmpty()){
			int levelNum = queue.size();
			List<Integer> subList = new ArrayList<>();
			for (int i = 0; i < levelNum; i++){
				TreeNode node = queue.poll();
				if (node.left != null) queue.add(node.left);
				if (node.right != null) queue.add(node.right);
				subList.add(node.val);
			}
			res.add(0, subList);
		}
		return res;
	}
	public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) return res;
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		boolean flag = true;
		while(!q.isEmpty()){
			int levelNum = q.size();
			List<Integer> cur = new ArrayList<>();
			
			for (int i = 0; i < levelNum; i++){
				TreeNode node = q.poll();
				if (node.left != null) q.add(node.left);
				if (node.right != null) q.add(node.right);
				if (flag){
					cur.add(node.val);
				}else{
					cur.add(0, node.val);
				}
			}
			res.add(cur);
			flag = !flag;
		}
		return res;	
	}
	public static List<List<Integer>> verticalOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		
		Map<Integer, ArrayList<Integer>> map = new HashMap<>();
		Queue<TreeNode> q = new LinkedList<>();
		Queue<Integer> cols = new LinkedList<>();

		q.add(root); 
		cols.add(0);

		int min = 0;
		int max = 0;
		
		while (!q.isEmpty()) {
			TreeNode node = q.poll();
			int col = cols.poll();
			
			if (!map.containsKey(col)) {
				map.put(col, new ArrayList<Integer>());
			}
			map.get(col).add(node.val);

			if (node.left != null) {
				q.add(node.left); 
				cols.add(col - 1);
				min = Math.min(min, col - 1);
			}
			
			if (node.right != null) {
				q.add(node.right);
				cols.add(col + 1);
				max = Math.max(max, col + 1);
			}
		}

		for (int i = min; i <= max; i++) {
			res.add(map.get(i));
		}

		return res;
	}
	public static List<Integer> rightSideView(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) return res;
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		while(!q.isEmpty()){
			int lvlNum = q.size();
			for (int i = 0; i < lvlNum; i++){
				TreeNode node = q.poll();
				if (node.left != null) q.add(node.left);
				if (node.right != null) q.add(node.right);
				if (i == lvlNum - 1){
					res.add(node.val);
				}
				
			} 
		}
		return res;
			
	}
	
	public static List<List<Integer>> binaryTreePathSum2(TreeNode root, int target){
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) return res;
		List<Integer> path = new ArrayList<>();
		btpsHelper2(root, target, 0, res, path);
		return res;
	}
	public static void btpsHelper2(TreeNode root, int target, int sum, List<List<Integer>> res, List<Integer> path){
		if (root == null){
			return;
		}
		
	}
	public static List<List<Integer>> binaryTreePathSum(TreeNode root, int target){
		List<List<Integer>> res = new ArrayList<>();
		if (root == null){
			return res;
		}
		List<Integer> path = new ArrayList<>();
		path.add(root.val);
		btpsHelper(root, target, root.val, res, path);
		
		return res;
	}
	public static void btpsHelper(TreeNode root, int target, int sum, List<List<Integer>> res, List<Integer> path){
		if (root.left == null && root.right == null){
			if (sum == target){
				res.add(new ArrayList<Integer>(path));
			}
			return;
		}
		if (root.left != null){
			path.add(root.left.val);
			btpsHelper(root.left, target, sum + root.left.val, res, path);
			path.remove(path.size() - 1);
			
		}
		if (root.right != null){
			path.add(root.right.val);
			btpsHelper(root.right, target, sum + root.right.val, res, path);
			path.remove(path.size() - 1);
			
		}
		
	}
	public static boolean isBalanced(TreeNode root){
		if (root == null) return true;
		int left = maxDepth(root.left);
		int right = maxDepth(root.right);
		if (Math.abs(left - right) > 1){
			return false;
		}
			
		return isBalanced(root.left) && isBalanced(root.right);
	}
	private static int longest;
	public static int longestConsecutive(TreeNode root){
		longest = 0;
		lcHelper(root, 0, root.val);
		return longest;
	}
	public static void lcHelper(TreeNode root, int cur, int target){
		if (root == null) return;
		if (root.val == target) cur++;
		else cur = 1;
		longest = Math.max(longest, cur);
		lcHelper(root.left, cur, root.val + 1);
		lcHelper(root.right, cur, root.val + 1);
	}
	
	public static void flatten (TreeNode root){
		if (root == null) return;
		TreeNode left = root.left;
		TreeNode right = root.right;
		root.left = null;
		flatten(left);
		flatten(right);
		root.right = left;
		while(root.right != null) root = root.right;
		root.right = right;
	}
	
	private static double maxAveSub;
	public static double maximumAverageSubtree(TreeNode root){	
		maxAveSub = 0;
		sumAndNum(root);
		return maxAveSub;
		
	}
	public static int[] sumAndNum(TreeNode root){
		if (root == null) return new int []{0,0};
		int sum = root.val;
		int num = 1;
		int [] left = sumAndNum(root.left);
		int [] right = sumAndNum(root.right);
		num += left[0] + right[0];
		sum += left[1] + right [1];
		maxAveSub = Math.max(maxAveSub, (double)sum / (double)num);
		return new int[] {num, sum};
		
	}
	public static List<String> BinaryTreePaths (TreeNode root){
		List<String> paths = new ArrayList<>();
		if (root == null) return paths;
		
		if (root.left == null && root.right == null){
			paths.add("" + root.val);
			return paths;
		}
		List<String> leftPaths = BinaryTreePaths(root.left);
		List<String> rightPaths = BinaryTreePaths(root.right);
		for (String path : leftPaths){
			paths.add(root.val + "->" + path);
			
		}
		for (String path : rightPaths){
			paths.add(root.val + "->" + path);
			
		}
		return paths;
	}
	public static int maxDepth(TreeNode root){
		if (root == null) return 0;
		int left = maxDepth(root.left);
		int right = maxDepth(root.right);
		return Math.max(left, right) + 1;
	}
	public static int minDepth(TreeNode root){
		if (root == null) return 0;
		int left = minDepth(root.left);
		int right = minDepth(root.right);
		return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
	}
	private static int minSubTree = Integer.MAX_VALUE;
	private static TreeNode subTree = null;
	public static int findMinSub(TreeNode root){
		minSubHelper(root);
		return minSubTree;
	}
	public static int minSubHelper(TreeNode root){
		if (root == null) return 0;
		int sum = minSubHelper(root.left) + minSubHelper(root.right) + root.val;
		if (sum < minSubTree){
			minSubTree = sum;
			subTree = root;
		}
		return sum;
		
	}
	public static void inorder(TreeNode root){
		if (root == null) return;
		inorder(root.left);
		System.out.print(root.val + " ");
		inorder(root.right);
	}
	public static void inorderIterative(TreeNode root){
		
		
	}
	public static void preorder(TreeNode root){
		if (root == null) return;
		System.out.print(root.val + " ");
		inorder(root.left);
		inorder(root.right);
			
	}
	public static void postorder(TreeNode root){
		if (root == null) return;
		inorder(root.left);
		inorder(root.right);
		System.out.print(root.val+ " ");
			
	}
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
    /*count nodes of the complete binary tree which means  except possibly the last, is completely filled, and all nodes in the last 
      level are as far left as possible. It can have between 1 and 2^h nodes inclusive at the last level h.
   */
    private static int height(TreeNode root){
        return root == null ? -1 : height(root.left) + 1;
    }
    //time complexity is O((logn)^2)
    public static int countNodes(TreeNode root) {
        int h = height(root);
        return h < 0 ? 0 : height(root.right) == h - 1 ? (1 << h) + countNodes(root.right) : (1 << h - 1) + countNodes(root.left);

    }
}
class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int val){
		this.val = val;
	}
	
}
