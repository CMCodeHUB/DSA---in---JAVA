class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        String ans = "";
        int left = 0;
        int ones = 0;

        for (int right = 0; right < s.length(); right++) {
            if (s.charAt(right) == '1') {
                ones++;
            }

            while (ones > k) {
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }

            if (ones == k) {
                while (s.charAt(left) == '0') {
                    left++;
                }

                String cur = s.substring(left, right + 1);

                if (ans.equals("") ||
                    cur.length() < ans.length() ||
                    (cur.length() == ans.length() && cur.compareTo(ans) < 0)) {
                    ans = cur;
                }
            }
        }

        return ans;
    }
}