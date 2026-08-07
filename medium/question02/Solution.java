package question02;


/**
 * 2.两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 直接模拟 注意短的链表后面没有节点值用0代替 注意下一个节点的null判定
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        int carry = 0;
        while(l1 != null || l2 != null){
            // 注意判断如果链表遍历完了数字用0占位
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            int sum = num1 + num2 + carry;
            // 计算出当前的数字和进位
            int num = sum % 10;
            carry = sum / 10;

            ListNode next = new ListNode(num);

            head.next = next;
            head = head.next;
            // 链表节点更新
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        // 如果算到最后还有进位就创建一个节点
        if(carry != 0){
            ListNode last = new ListNode(carry);
            head.next = last;
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