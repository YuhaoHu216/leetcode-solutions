package question19;

/**
 * 19.删除链表的倒数第N个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        // 注意这里不要new 新节点，否则不是一条链表
        ListNode slow = dummy;
        ListNode fast = dummy;

        // 两次循环可以用一次代替
        while(fast != null){
            fast = fast.next;
            if(n < 0){
                slow = slow.next;
            }
            n--;
        }

//        // fast 先走 n 步
//        for (int i = 0; i < n; i++) {
//            fast = fast.next;
//        }
//
//        // slow 和 fast 一起走
//        while (fast.next != null) {
//            slow = slow.next;
//            fast = fast.next;
//        }

        // 删除 slow 后面的节点
        slow.next = slow.next.next;

        return dummy.next;
    }
}

class Solution2 {
    public ListNode removeNthFromEnd(ListNode head, int n) {

            // 注意从前置节点开始遍历 防止只有一个节点的情况
            ListNode dummy = new ListNode(0, head);
            int length = getLength(head);
            ListNode cur = dummy;
            for (int i = 1; i < length - n + 1; ++i) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
            ListNode ans = dummy.next;
            return ans;
        }

        public int getLength(ListNode head) {
            int length = 0;
            while (head != null) {
                ++length;
                head = head.next;
            }
            return length;
        }

}

class ListNode {
 int val;
 ListNode next;
 ListNode() {}
 ListNode(int val) { this.val = val; }
 ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
