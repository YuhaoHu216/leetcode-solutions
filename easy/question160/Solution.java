package question160;

import java.util.HashSet;
import java.util.Set;

/**
 * 160.相交链表
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 *
 */
// time O(m+n) pA、pB 各自最多走 m+n 步就会相遇或同时到达 null
// space O(1) 只用了两个指针
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 双指针的原理是两个指针走同样的路程最后一定会走到相同点，如果没有交点就同时为null
        if (headA == null || headB == null) return null;

        ListNode pA = headA, pB = headB;

        while (pA != pB) {
            // 判断，如果一条链表走完了，就从另一条链表起始点开始走
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }

        return pA;
    }
}

// 用哈希表来实现
// time O(m+n) 先遍历 A 存集合，再遍历 B 查集合，各走一遍
// space O(m) 最坏把 A 的所有节点都存进 HashSet
class Solution2 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode temp = headA;
        while(temp != null){
            set.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while(temp != null){
            if(set.contains(temp)){
                return temp;
            }
            temp = temp.next;
        }

        return null;
    }
}

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
          val = x;
          next = null;
     }
}
