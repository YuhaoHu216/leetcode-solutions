package question24;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 234.回文链表
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 */
public class Solution {
    public boolean isPalindrome(ListNode head) {
        List<Integer> temp = new ArrayList<>();
        ListNode p = head;
        while(p != null){
            temp.add(p.val);
            p = p.next;
        }

        int left = 0;
        int right = temp.size() - 1;
        while(left < right){
            if(!Objects.equals(temp.get(left), temp.get(right))){
                return false;
            }
            left++;
            right--;
        }
        return true;

    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution2 {
    public boolean isPalindrome(ListNode head) {
        // 最优解思路,快慢指针找终点,将右半链表翻转,再依次比较
        // 不最优解可以将值存入动态数组然后左右双指针比较

        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        // fast为null就说明有偶数个节点,否则为奇数个节点
        // 当为奇数个节点时,slow指针要多跳一个(不要中心节点)
        if(fast != null){
            slow = slow.next;
        }

        ListNode p1 = head;
        ListNode p2 = reverse(slow);

        while(p2 != null){
            if(p1.val != p2.val){
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        return true;
    }

    // 反转链表
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
