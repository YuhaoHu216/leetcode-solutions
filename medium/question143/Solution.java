package question143;


/**
 * 143. 重排链表
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
class Solution {
    public void reorderList(ListNode head) {
        ListNode mid  = findMid(head);
        ListNode head2 = reverse(mid);

        while(head2.next != null){
            ListNode next = head.next;
            ListNode next2 = head2.next;
            // 重排节点
            head.next = head2;
            head2.next = next;
            // 更新节点
            head = next;
            head2 = next2;
        }

    }

    // 寻找链表中心节点
    ListNode findMid(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 反转链表
    ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode cur = head;

        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
