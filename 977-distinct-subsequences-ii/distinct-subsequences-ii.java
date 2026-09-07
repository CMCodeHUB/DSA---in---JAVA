class Solution {
    public int distinctSubseqII(String s) {
        final long MOD = 1000000007;
        long[] dp = new long[26];

        for (char c : s.toCharArray()) {
            int idx = c - 'a';

            long total = 1;
            for (long x : dp) {
                total = (total + x) % MOD;
            }

            dp[idx] = total;
        }

        long ans = 0;
        for (long x : dp) {
            ans = (ans + x) % MOD;
        }

        return (int) ans;
    }
}