package question30;

/**
 * 24.两两交换链表中的节点
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
 * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 */
public class Solution {
    public ListNode swapPairs(ListNode head) {
        // 迭代法,维护每一个段的前一个节点
        ListNode first = new ListNode(-1,head);
        ListNode pre = first;

        while(pre.next != null && pre.next.next != null ){
            // 获得要交换的两个结点
            ListNode node1 = pre.next;
            ListNode node2 = pre.next.next;
            // 执行交换的逻辑
            pre.next = node2;   // 链表要接上
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
