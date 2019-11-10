import java.util.*;
class TwoPointersCon {
	public static void main(String[] args) {
		
	}
	/*Here is a conclusion of the Deque ArrayDeque
	First Innitialized it as Deque<Ineger> q = new ArrayDeque<>();
	We can use this for both queue and stack here is a Example how Deque can be equivelent to Queue and Stack
	Queue          	Deque
	add/offer      	addLast()/ offerLast()
	remove/poll    	removeFirst()/pollFirst()
	element/peek() 	getFirst()/peekFirst()
	
	Stack			Deque
	push			addFirst()
	pop				removeFirst()
	peek			peekFirst()
	
	Besides this deque can also pollLast(), peekLast(), removeLast(), that's why we call it double ended queue;
	Below is an example of the implement of deque:
	*/
	public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums == null || k <= 0) return new int [0];
		int n = nums.length;
		int [] r = new int[n - k + 1];
		int ri = 0;
		Deque<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < nums.length; i++){
			if (!q.isEmpty() && q.peek() < i - k + 1){
				q.poll();
			}
			while(!q.isEmpty() && nums[q.peekLast()] < nums[i]){
				q.pollLast();
			}
			q.offer(i);
			if (i >= k - 1){
				r[ri++] = nums[q.peek()];
			}
		}
		return r;
	}
	//"eceba" return 3, because "ece"
	// This proble could chang to K distinct by change the cnt's thresdhold
	public static int lengthOfLongestSubstringTwoDistinct(String s) {
		char [] chs = s.toCharArray();
		int i = 0, j = 0, cnt = 0, max = 0;
		int [] freq = new int[256];
		while(i < chs.length){
			while(j < chs.length && cnt < 3){
				freq[chs[j]]++;
				if (freq[chs[j++]] == 1) cnt++;
			}
			max = cnt == 3 ? Math.max(max, j - i - 1) : Math.max(max, j - i);
			if (j == chs.length) break;
			
			while(i < j && cnt > 2){
				if (--freq[chs[i++]] == 0) cnt--;
			}
		}
		return max;
	}
	
	//A = [1,2,1,2,3], K = 2 return 7; [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
	public int subarraysWithKDistinct(int[] A, int K) {
		return atMost(A, K) - atMost(A, K - 1);
	}
	private int atMost(int[] A, int K){
		int i = 0, res = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int j = 0; j < A.length; i++){
			if (map.getOrDefault(nums[j], 0) == 0){
				K--;
			}
			map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
			while(K < 0){
				map.put(A[i], map.get(A[i]) - 1);
				if (map.get(A[i]) == 0) K++;
				i++;
			}
			res += j - i + 1;
		}
		return res;
	}
		
	public int minSubArrayLen(int s, int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		int j = 0, i = 0, sum = 0, res = nums.length + 1;
		while (j < nums.length){
			sum += nums[j++];
			while (sum >= s) {
				res = Math.min(res, j - i);
				sum -= nums[i++];
			}
			
		}
		return res <= nums.length ? res : 0;
	}
		
	public List<Integer> findSubstring(String s, String[] words) {
		List<Integer> res = new ArrayList<>();
		if (words == null || words.length == 0) return res;
		Map<String, Integer> map = new HashMap<>();
		for (String word : words){
			map.put(word, map.getOrDefault(word, 0) + 1);
		}
		int n = s.length(), num = words.length, len = words[0].length();
		
		for(int i = 0; i < n - num * len + 1; i++){
			Map<String, Integer> count = new HashMap<>();
			int j = 0;
			while(j < num){
				String word = s.substring(i + j * len, i + len * (j + 1));
				if (map.containsKey(word)){
					count.put(word, count.getOrDefault(word, 0) + 1);
					if (count.get(word) > map.get(word)){
						break;
					}
				}
				else{
					break;
				}
				j++;
			}
			if (j == num){
				res.add(i);
			}
		}
		return res;
	}
    public int triangle(int [] nums){
        Arrays.sort(nums);
        int n = nums.length, cnt = 0;
        for (int i = n - 1;i >= 2;i--){
            int l = 0, r = i - 1;
            while(l < r){
                if (nums[l] + nums[r] > nums[i]){
                    cnt += r - l;
                    r--;
                }else{
                    l++;
                }
            }
        }
        return cnt;
    }		
	public int strStr(String haystack, String needle) {
		for(int i = 0;;i++){
			for (int j = 0;; j++){
				if (j == needle.length()) return i;
				if (i + j == haystack.length()) return -1;
				if (haystack.charAt(i + j) != needle.charAt(j)) break;
			}
		}
	}
	//time com is O(n^2)
	public String minWindowSubsequence(String S, String T) {
		char[] s = S.toCharArray();
		char[] t = T.toCharArray();
		
		int l = 0, r = 0, ansl = -1, ansr = -1, tIndex = 0;
		while(l < s.length){
			if (s[l] != t[0]){
				l++;
				continue;
			}
			r = l;
			while(tIndex < t.length && r < s.length){
				if (s[r] == t[tIndex]){
					tIndex++;
				}
				r++;
				if (tIndex == t.length){
					if (r - l < ansr - ansl || ansl == -1){
						ansr = r;
						ansl = l;
					}
				}
			}
			l++;
			tIndex = 0;
		}
		if (ansl == -1){
			return "";
		}
		return S.substring(ansl, ansr);	
	}
	//optimized
	public String minWindowSubsequenceOp(String S, String T) {
		char[] s = S.toCharArray(), t = T.toCharArray();
		int sindex = 0, tindex = 0, slen = s.length, tlen = t.length, start = -1, len = slen;
		while(sindex < slen) {
			if(s[sindex] == t[tindex]) {
				if(++tindex == tlen) {
					//check feasibility from left to right of T
					int end = sindex+1;
					//check optimization from right to left of T
					while(--tindex >= 0) {
						while(s[sindex--] != t[tindex]);
					}
					++sindex;
					++tindex;
					//record the current smallest candidate
					if(end - sindex < len) {
						len = end - sindex;
						start = sindex;
					}
				}
			}
			++sindex;
		}
		return start == -1? "" : S.substring(start, start + len);
		
	}
	//dp solution
	public String minWindowSubsequenceDp(String S, String T) {
		int m = T.length(), n = S.length();
		int[][] dp = new int[m + 1][n + 1];
		for (int j = 0; j <= n; j++) {
			dp[0][j] = j + 1;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (T.charAt(i - 1) == S.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = dp[i][j - 1];
				}
			}
		}

		int start = 0, len = n + 1;
		for (int j = 1; j <= n; j++) {
			if (dp[m][j] != 0) {
				if (j - dp[m][j] + 1 < len) {
					start = dp[m][j] - 1;
					len = j - dp[m][j] + 1;
				}
			}
		}
		return len == n + 1 ? "" : S.substring(start, start + len);
	}
	
	public String minWindowSubstring(String st, String tt){
		char [] s = st.toCharArray();
		char [] t = tt.toCharArray();
		if (t.length == 0){
			return "";
		}
		int [] cntS = new int [256];
		int [] cntT = new int [256];
		int K = 0;
		for(char c : t){
			cntT[c]++;
			if (cntT[c] == 1){
				K++;
			}
		}
		int C = 0;
		int ansl = -1, ansr = -1;
		int l = 0, r = 0;
		for (l = 0; l < s.length;l++){
			while(r < s.length && C < K){
				cntS[s[r]]++;
				if (cntS[s[r]] == cntT[s[r]]) C++;
				++r;
			}
			if (C == K){
				if ((r - l) < (ansr - ansl) || ansl = -1){
					ansr = r;
					ansl = l;
				}
			}
			if (cntS[s[l]] == cntT[s[l]]){
				C--;
			}
			cntS[s[l]]--;
		}
		if (ansl == -1){
			return "";
		}else{
			return st.substring(ansl, ansr);
		}
		
	}
	public int lengthOfLongestSubstring(String s) {
		if (s.length() == 0) return 0;
		Map<Character, Integer> map = new HashMap<>();
		int max = 0;
		for (int i = 0, j = 0;i < s.length();i++){
			if (map.containsKey(s.charAt(i))){
				j = Math.max(j, map.get(s.charAt(i) + 1));
			}
			map.put(s.charAt(i), i);
			max = Math.max(max, i - j + 1);
		}
		return max;
			
	}
	//This is not a two pointers problem, but it's related to the follow up questions
	public int[] twoSum(int [] nums, int target){
		//if (nums == null) || nums.length == 0) return new int []{0,0};
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++){
			int complement = target - nums[i];
			if (map.containsKey(complement)){
				return new int []{map.get(complement), i};
			}
			map.put(nums[i], i);
		}
		return new int [] {0, 0};
	}
	public List<List<Integer>> threeSum(int [] nums){
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++){
			if (i > 0 && nums[i] == nums[i - 1]) continue;
			int l = i + 1, r = nums.length - 1;
			while(l < r){
				if (nums[i] + nums[l] + nums[r] == 0){
					res.add(Arrays.asList(nums[i], nums[l], nums[r]));
					while(l < r && nums[l + 1] == nums[l]) l++;
					while(l < r && nums[r - 1] == nums[r]) r--;
					l++;
					r--;
				}else if (nums[i] + nums[l] + nums[r] < 0){
					l++;
				}else{
					r--;
				}
			}
		}
		return res;
	}
	public int threeSumCloset(int [] nums, int targt){
		int res = nums[0] + nums[1] + nums[nums.length];
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++){
			int l = i + 1, r = nums.length - 1;
			while(l < r){
				int sum = nums[l] + nums[r] + nums[i];
				if (sum < target){
					l++;
				}else{
					r--;
				}
				if (Math.abs(sum - target) < Math.abs(ans - target)){
					ans = sum;
				}
			}
		}
		return res;
	}
	
	public int threeSumSmaller(int[] nums, int target) {
		int res = 0;
		int len = nums.length;
		Arrays.sort(nums);
		for(int i = 0; i < len;i++){
			int l = i + 1, r = len - 1;
			while(l < r){
				if (nums[i] + nums[l] + nums[r] < target){
				res += r - l;
				l++;
				}else{
					r--;
				}   
			}
		}
		return res;
	}
	public List<List<Integer>> fourSum(int [] nums, int target){
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);
		for (int i = 0; i < nums.length;i++){
			if (i > 0 && nums[i] == nums[i - 1]) continue;
			for (int j = i + 1; j < nums.length; j++){
				if (j > i + 1 && nums[j] == nums[j - 1]) continue;
				int l = j + 1, r = nums.length - 1;
				while(l < r){
					int sum = nums[i] + nums[j] + nums[l] + nums[r];
					if (sum == target){
						res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
						while(l < r && nums[l] == nums[l + 1]) l++;
						while(l < r && nums[r] == nums[r -  1]) r--;
						l++;
						r--;
					}else if(sum < target){
						l++;
					}else{
						r--;
					}
				}
			}
		}
		return res;
	}
	
	
}
