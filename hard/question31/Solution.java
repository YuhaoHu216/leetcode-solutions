package question31;

/**
 * 25.K个一组翻转链表
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 */


class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 用来记录每段链表的头尾
        ListNode pre = dummy;  // 每段链表的前节点
        ListNode end = dummy;

        while(true){
            for(int i = 0; i < k && end != null; i++){
                end = end.next;
            }
            if(end == null) break;

            // 获取下一段的开头
            ListNode nextStart = end.next;
            end.next = null;        // 将当前段与后边断开
            ListNode start = pre.next;  // 获取当前段的开头
            // 翻转链表片段
            pre.next = reverse(start);
            start.next = nextStart;  // 翻转后头变尾,重新连接

            pre = start;  // 更新pre
            end = pre;    // 更新end
        }
        return dummy.next;
    }

    // 翻转链表
    private ListNode reverse(ListNode head){
        ListNode cur = head;
        ListNode pre = null;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
