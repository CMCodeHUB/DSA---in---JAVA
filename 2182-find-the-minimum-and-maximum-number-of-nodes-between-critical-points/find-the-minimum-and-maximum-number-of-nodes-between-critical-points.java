/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first = -1;
        int prev = -1;
        int minDist = Integer.MAX_VALUE;

        ListNode before = head;
        ListNode curr = head.next;
        int index = 1;

        while (curr != null && curr.next != null) {
            if ((curr.val > before.val && curr.val > curr.next.val) ||
                (curr.val < before.val && curr.val < curr.next.val)) {

                if (first == -1) {
                    first = index;
                }

                if (prev != -1) {
                    minDist = Math.min(minDist, index - prev);
                }

                prev = index;
            }

            before = curr;
            curr = curr.next;
            index++;
        }

        if (first == -1 || prev == first) {
            return new int[]{-1, -1};
        }

        int maxDist = prev - first;

        return new int[]{minDist, maxDist};
    }
}