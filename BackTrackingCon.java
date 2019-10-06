import java.util.*;
class BackTrackingCon {
	public static void main(String[] args) {
		int [] nums = {1, 2, 3};
		System.out.println(subsets(nums));
		int [] nums_dup = {1, 2, 2};
		System.out.println(subsetsDup(nums_dup));
		System.out.println(permute(nums));
		System.out.println(permuteDup(nums_dup));
		String s = "aaabb";
		System.out.println(partition(s));
		System.out.println(letterCombination("234"));
	}
	//letter combination of phone number
	private static String [] keys = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
	public static List<String> letterCombination(String digits){
		List<String> res = new LinkedList<String>();
		letterComHelper(res, digits, 0, "");
		return res;
		
	}
	public static void letterComHelper(List<String> res, String digits, int offset, String temp){
		if (offset == digits.length()){
			res.add(temp);
			return;
		}
		String letters = keys[(digits.charAt(offset) - '0')];
		for (int i = 0; i < letters.length(); i++){
			
			letterComHelper(res, digits, offset + 1, temp + letters.charAt(i));
		}
	}
	
	public static List<List<Integer>> subsets(int[] nums){
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0){
			return res;
		}
		
		List<Integer> list = new ArrayList<>();
		subsetHelper(nums, res, list, 0);
		return res;
	}
	
	public static void subsetHelper(int [] nums, List<List<Integer>> res, List<Integer> list, int index){
		res.add(new ArrayList<Integer>(list));
		
		for (int i = index; i < nums.length; i++){
			list.add(nums[i]);
			subsetHelper(nums, res, list, i + 1);
			list.remove(list.size() - 1);
		}
	}
	
	public static List<List<Integer>> subsetsDup(int [] nums){
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0){
			return res;
		}
		Arrays.sort(nums);
		subsetDupHelper(nums, res, new ArrayList<>(), 0);
		return res;
	}
	
	public static void subsetDupHelper(int [] nums, List<List<Integer>> res, List<Integer> tempList, int index){
		
		res.add(new ArrayList<Integer>(tempList));
		
		for (int i = index; i < nums.length; i++){
			if (i > index && nums[i] == nums[i - 1]) continue;
			tempList.add(nums[i]);
			subsetDupHelper(nums, res, tempList, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}
	
	public static List<List<Integer>> permute(int [] nums){
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0){
			return res;
		}
		permutationHelper(nums, res, new ArrayList<>());
		return res;
		
	}
	
	public static void permutationHelper(int[] nums, List<List<Integer>> res, List<Integer> tempList){
		if (tempList.size() == nums.length){
			res.add(new ArrayList<>(tempList));
		}
		for (int i = 0; i < nums.length; i++){
			if (tempList.contains(nums[i])) continue;
			tempList.add(nums[i]);
			permutationHelper(nums, res, tempList);
			tempList.remove(tempList.size() - 1);
		}
	}
	
	public static List<List<Integer>> permuteDup(int [] nums){
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0){
			return res;
		}
		Arrays.sort(nums);
		permutationDupHelper(nums, res, new ArrayList<>(), new boolean[nums.length]);
		return res;

	}
	
	public static void permutationDupHelper(int[] nums, List<List<Integer>> res, List<Integer> tempList, boolean [] used){
		if(tempList.size() == nums.length){
			res.add(new ArrayList<Integer>(tempList));
		}
		for (int i = 0; i < nums.length; i++){
			if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) continue;
			used[i] = true;
			tempList.add(nums[i]);
			permutationDupHelper(nums, res, tempList, used);
			used[i] = false;
			tempList.remove(tempList.size() - 1);		
		}
	}
	public static List<List<Integer>> combinationSum(int[] candidates, int target){
		List<List<Integer>> res = new ArrayList<>();
		if (candidates == null || candidates.length == 0){
			return res;
		}
		Arrays.sort(candidates);
		combiSumHelper(candidates, res, new ArrayList<>(), target, 0);
		return res;
	}
	public static void combiSumHelper(int [] candidates, List<List<Integer>> res, List<Integer> tempList, int remain, int start){
		if (remain == 0){
			res.add(new ArrayList<>(tempList));
		}else if (remain < 0){
			return;
		}else{
			for (int i = start; i < candidates.length; i++){
				tempList.add(candidates[i]);
				combiSumHelper(candidates, res, tempList, remain - candidates[i], i);
				tempList.remove(tempList.size() - 1); 
			}	
		}	
	}
	
	public static List<List<Integer>> conbinationSum2(int[] candidates, int target){
		List<List<Integer>> res = new ArrayList<>();
		if (candidates == null || candidates.length == 0){
			return res;
		}
		Arrays.sort(candidates);
		combiSumHelper2(candidates, res, new ArrayList<>(), target, 0);
		return res;
	}
	public static void combiSumHelper2(int [] candidates, List<List<Integer>> res, List<Integer> tempList, int remain, int start){
		if (remain < 0){
			return;
		} else if (remain == 0){
			res.add(new ArrayList<>(tempList));
		} else {
			for (int i = start; i < candidates.length; i++){
				if (i > start && candidates[i] == candidates[i - 1]) continue;
				tempList.add(candidates[i]);
				combiSumHelper2(candidates, res, tempList, remain - candidates[i], i + 1);
				tempList.remove(tempList.size() - 1);
			}
		}
	}
	//String s = "aab" -> "a", "a", "b" /"aa", "b"
	public static List<List<String>> partition(String s){
		List<List<String>> res = new ArrayList<>();
		partitionHelper(s, res, new ArrayList<>(), 0);
		return res;
	}
	public static void partitionHelper(String s, List<List<String>> res, List<String> tempList, int start){
		if (start == s.length()){
			res.add(new ArrayList<>(tempList));
		}
		else{
			for (int i = start; i < s.length();i++){
				if (isPalindrome(s, start, i)){
					tempList.add(s.substring(start, i + 1));
					partitionHelper(s, res, tempList, i + 1);
					tempList.remove(tempList.size() - 1);
				}
			}
		}
		
	}
	public static boolean isPalindrome(String s, int l, int r){
		while(l < r){
			if (s.charAt(l++) != s.charAt(r--)) return false;
		}
		return true;
	}
	/* Letter case pemutation: typical bfs/dfs problem */
	
	public static List<String> letterCasePermutationBfs(String S) {
		if (S == null){
			return new LinkedList<>();
		}
		Queue<String> queue = new LinkedList<>();
		queue.offer(S);
		
		for (int i = 0; i < S.length();i++){
			if (Character.isDigit(S.charAt(i))) continue;
			int size = queue.size();
			for (int j = 0; j < size; j++){
				String cur = queue.poll();
				char [] chars = cur.toCharArray();
				
				chars[i] = Character.toUpperCase(chars[i]);
				queue.offer(String.valueOf(chars));
				
				chars[i] = Character.toLowerCase(chars[i]);
				queue.offer(String.valueOf(chars));
			}
		}	
		return new LinkedList<>(queue);
	}
	public static List<String> letterCasePermutationDfs(String S){
		if (S == null){
			return new LinkedList<>();
		}
		List<String> res = new LinkedList<>();
		letterCasePermuHelper(res, S.toCharArray(), 0);
		return res;
		
	}
	public static void letterCasePermuHelper(List<String> res, char [] chs, int pos){
		if (pos == chs.length){
			res.add(new String(chs));
			return;
		}
		if (chs[pos] >= '0' && chs[pos] <= '9'){
			letterCasePermuHelper(res, chs, pos + 1);
			return;
		}
		chs[pos] = Character.toUpperCase(chs[pos]);
		letterCasePermuHelper(res, chs, pos + 1);
		
		chs[pos] = Character.toLowerCase(chs[pos]);
		letterCasePermuHelper(res, chs, pos + 1);
	}	
}