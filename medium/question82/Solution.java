package question82;

/**
 * 82. 删除排序链表中的重复元素 II
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // 虚节点用来返回结果
        ListNode dummy = new ListNode(0,head);
        // 前置节点用于移除节点 指向当前确认不重复的节点
        ListNode pre = dummy;
        while(head != null){
            // 当当前节点的值等于后面节点的值时
            if(head.next != null && head.val == head.next.val){
                int val = head.val;
                while(head != null && val == head.val){
                    head = head.next;
                }
                // 节点删除完后前置节点接上
                pre.next = head;
            }else{
                head = head.next;
                pre = pre.next;
            }
        }
        return dummy.next;
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}