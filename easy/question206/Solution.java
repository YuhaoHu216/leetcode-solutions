package question206;

import java.util.Scanner;

/**
 * 206.反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */
// 迭代
// time O(n)
// space O(1) 全程只用到 pre、current、next 三个指针变量，跟链表长度无关。
public class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode current = head;
        while(current != null){
            ListNode next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 输入一行链表各节点的值，例如：1 2 3 4 5
        String line = scanner.nextLine();
        scanner.close();

        // 构建链表
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (String s : line.split("[,\\s\\[\\]]+")) {
            if (s.isEmpty()) continue;
            tail.next = new ListNode(Integer.parseInt(s));
            tail = tail.next;
        }

        ListNode head = new Solution().reverseList(dummy.next);

        // 输出，例如：[5, 4, 3, 2, 1]
        StringBuilder sb = new StringBuilder("[");
        for (ListNode cur = head; cur != null; cur = cur.next) {
            sb.append(cur.val);
            if (cur.next != null) sb.append(", ");
        }
        System.out.println(sb.append("]"));
    }
}

// 递归
// time O(n)
// space O(n) reverseList(head.next) 会一路深入到链表末尾，调用栈上压了 n 层栈帧，每层都要保存自己的 head 局部变量。峰值深度就是 n。
class Solution2 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

