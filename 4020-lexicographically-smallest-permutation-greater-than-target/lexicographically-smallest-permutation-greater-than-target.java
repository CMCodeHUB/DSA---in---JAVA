class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();

        for (int i = n - 1; i >= 0; i--) {
            int[] freq = new int[26];

            for (char c : s.toCharArray()) {
                freq[c - 'a']++;
            }

            // Use the same prefix as target
            boolean possible = true;

            for (int j = 0; j < i; j++) {
                int c = target.charAt(j) - 'a';

                if (freq[c] == 0) {
                    possible = false;
                    break;
                }

                freq[c]--;
            }

            if (!possible) {
                continue;
            }

            // Find the smallest character greater than target[i]
            int current = target.charAt(i) - 'a';

            for (int c = current + 1; c < 26; c++) {
                if (freq[c] > 0) {
                    StringBuilder ans = new StringBuilder();

                    // Same prefix
                    for (int j = 0; j < i; j++) {
                        ans.append(target.charAt(j));
                    }

                    // Make it greater
                    ans.append((char) ('a' + c));
                    freq[c]--;

                    // Smallest possible suffix
                    for (int x = 0; x < 26; x++) {
                        while (freq[x] > 0) {
                            ans.append((char) ('a' + x));
                            freq[x]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}