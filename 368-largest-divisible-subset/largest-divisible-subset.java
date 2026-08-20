class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        int[] dp = new int[n];
        int[] parent = new int[n];

        Arrays.fill(dp, 1);

        int maxLen = 1;
        int last = 0;

        for (int i = 0; i < n; i++) {
            parent[i] = -1;

            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }

            if (dp[i] > maxLen) {
                maxLen = dp[i];
                last = i;
            }
        }

        List<Integer> ans = new ArrayList<>();

        while (last != -1) {
            ans.add(nums[last]);
            last = parent[last];
        }

        return ans;
    }
}
