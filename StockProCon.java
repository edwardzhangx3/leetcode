class StockProCon {
	public static void main(String[] args) {
		int [] nums = {5, 2, 3, 4, 5};
		stockOne();
	}
	public static int stockOne(int[] nums){
		int maxCur = 0, max = 0;
		for (int i = 0;i < nums.length - 1;i++){
			maxCur = Math.max(0, maxCur += nums[i + 1] - nums[i]);
			max = Math.max(max, maxCur);
			
		}
		return max;
	}
}