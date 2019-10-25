import java.util.*;
class BinarySearchCon {
	public static void main(String[] args) {
		int [] nums = {4,5,6,7,0,1,2};
		System.out.println(findMinRotate(nums));
		System.out.println(1<<1);
	}
	
	public static int mountainSequence(int [] nums){
		if (nums == null || nums.length == 0) return -1;
		int start = 0, end = nums.length - 1;
		while(start + 1< end){
			int mid = start + ((end - start) >> 1);
			if (nums[mid] > nums[mid - 1]){
				start = mid;
			}else{
				end = mid;
			}
		}
		return Math.max(nums[start], nums[end]);
	}
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0|| matrix[0].length == 0) return false;
		int m = matrix.length;
		int n = matrix[0].length;
		int begin = 0, end = m * n - 1;
		while(begin <= end){
		    int mid = (begin + end) / 2;
		    int mid_value = matrix[mid/n][mid%n];
		
			if( mid_value == target){
				return true;

			}else if(mid_value < target){
				//Should move a bit further, otherwise dead loop.
				begin = mid+1;
			}else{
				end = mid-1;
			}
	    }
		return false;
	}
	
	public boolean searchMatrix2(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
			return false;
		}
		int m = matrix[0].length - 1;
		int n = 0;
		while(m >= 0 && n <= matrix.length - 1){
			if (matrix[n][m] == target){
				return true;
			}else if (matrix[n][m] < target){
				n++;
			}else{
				m--;
			}
		}
		return false;
	}

	public static int [] searchRange(int [] nums, int target){
		int start = firstGreater(nums, target);
		if (start == nums.length || nums[start] != target){
			return new int [] {0,0};
		}
		return new int [] {start, firstGreater(nums, target + 1) - 1};
		
	}
	public static int firstGreater(int [] nums, int target){
		int l = 0, r = nums.length;
		while(l < r){
			int mid = l + ((r - l) >> 1);
			if (nums[mid] < target){
				l = mid + 1;
			}else{
				r = mid;
			}
		}
		return l;
	}
	
	
	public static int searchRotate(int[] nums, int target) {
		if (nums == null || nums.length == 0) return -1;
		int l = 0, r = nums.length - 1;
		while(l < r){
			int mid = l + (r - l) / 2;
			if (nums[mid] == target) return mid;
			if (nums[mid] > nums[r]){
				if (target >= nums[l] && target < nums[mid]){
					r = mid - 1;
				}else{
					l = mid + 1;
				}
			}else{
				if (target > nums[mid] && target <= nums[r]){
					l = mid + 1;
				}else{
					r = mid - 1;
				}
			}
		}
		return nums[l] == target ? l : -1;
	}
	public static int findMinRotate(int [] nums){
		if (nums == null || nums.length == 0) return 0;
		if (nums.length == 1) return nums[0];
		int l = 0, r = nums.length - 1, mid = 0;
		while(l < r){
			mid = l + (r - l) / 2;
			if (mid > 0 && nums[mid] < nums[mid - 1]){
				return nums[mid];
			}
			if (nums[mid] >= nums[l] && nums[mid] > nums[r]){
				l = mid + 1;
			}else{
				r = mid - 1;
			}
		}
		return nums[l];
	}
	
	public static int findMinDup(int[] nums) {
		int l = 0, r = nums.length - 1;
		while(l < r ){
			int mid = l + (r - l) / 2;
			if (nums[mid] > nums[r]){
				l = mid + 1;
			}else if (nums[mid] < nums[r]){
				r = mid;
		
			}else{
				r--;
			}
		}
		return nums[l];
	}
}
