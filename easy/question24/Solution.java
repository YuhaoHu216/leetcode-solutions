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
