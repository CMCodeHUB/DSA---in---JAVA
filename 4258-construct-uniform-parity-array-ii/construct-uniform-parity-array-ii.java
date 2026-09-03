class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = nums1[0];
        boolean hasOdd = false;

        for (int num : nums1) {
            min = Math.min(min, num);

            if (num % 2 != 0) {
                hasOdd = true;
            }
        }

        // All elements are even
        if (!hasOdd) {
            return true;
        }

        // Minimum is odd -> can make all elements odd
        return min % 2 != 0;
    }
}