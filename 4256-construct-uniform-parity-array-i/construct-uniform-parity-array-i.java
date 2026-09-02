class Solution {
    public boolean uniformArray(int[] nums1) {
        boolean hasEven = false;
        boolean hasOdd = false;

        for (int x : nums1) {
            if (x % 2 == 0) {
                hasEven = true;
            } else {
                hasOdd = true;
            }
        }


        if (!hasEven || !hasOdd) {
            return true;
        }
        return true;
    }
}