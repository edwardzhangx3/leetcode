import java.util.*;
class BackTrackingCon {
	public static void main(String[] args) {
		int [] nums = {1, 2, 3};
		System.out.println(subsets(nums));
		int [] nums_dup = {1, 2, 2};
		System.out.println(subsetsDup(nums_dup));
		System.out.println(permute(nums));
		System.out.println(permuteDup(nums_dup));
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
//	public static List<List<Integer>> 
	
}