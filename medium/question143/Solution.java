package question143;


/**
 * 143.重排链表
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * time O(n)
 * space O(1)
 *
 */
// 快慢指针找到链表中点，将后半段链表反转，再与前半段链表交替穿插合并。
class Solution {
    public void reorderList(ListNode head) {
        ListNode mid = findMid(head);
        ListNode head2 = reverse(mid);

        ListNode p1 = head;
        ListNode p2 = head2;

        // 这里条件这么设置的原因是第二条链表的最后一个节点必定是合并后的最后一个节点
        // 防止出现环
        while(p2.next != null){
            // 先获取两条链表的下一节点
            ListNode next1 = p1.next;
            ListNode next2 = p2.next;
            // 对选定的两链表的当前节点进行重排操作
            p1.next = p2;
            p2.next = next1;
            // 选定节点更新到下一节点
            p1 = next1;
            p2 = next2;
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
