package question30;

public class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode first = new ListNode(-1,head);
        ListNode pre = first;

        while(pre.next != null && pre.next.next != null ){
            ListNode node1 = pre.next;
            ListNode node2 = pre.next.next;

            pre.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            pre = node1;
        }
        return first.next;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
