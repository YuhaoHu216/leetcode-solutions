package question92;



class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 创建dummy哨兵节点为了防止left等于1的时候head要做反转
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode nextStartNode = null;

        // 找到反转区间前面的节点
        ListNode preNode = dummy;
        for(int i = 1; i < left; i++){
            preNode = preNode.next;
        }

        // 定义反转区间的开头(反转后变为尾部)
        ListNode startNode = preNode.next;

        /**
         * 反转的逻辑
         */
        ListNode pre = null;
        ListNode cur = startNode;

        for(int i = 0; i < right - left + 1; i++){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        // 链接起原链表
        preNode.next = pre;
        startNode.next = cur;   // 因为没有断开链表所以cur就是原链表的下一个节点

        return dummy.next;
    }


}
