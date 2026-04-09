package LCR140;


 // 快慢指针 快指针比慢指针快 k - 1 步
class Solution {
    public ListNode trainingPlan(ListNode head, int cnt) {
        ListNode slow = head;
        ListNode fast = head;

        for(int i = 0; i < cnt - 1 && fast != null; i++){
            fast = fast.next;
        }

        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}