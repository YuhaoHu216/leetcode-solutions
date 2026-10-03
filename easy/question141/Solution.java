package question141;

import java.util.HashSet;
import java.util.Set;

/**
 * 141.环形链表
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 */
// time O(n)  无环时 fast 走到底就退出，迭代 n/2 次；有环时 slow 进环后最多走一圈就会被 fast 追上，两者走的步数都不超过 n
// space O(1)  只用了 slow、fast 两个指针，跟链表长度无关
class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow ) return true;
        }
        return false;
    }
}

// time O(n)  每个节点只访问一次，HashSet 的 contains/add 均摊 O(1)
// space O(n)  最坏情况（无环）要把 n 个节点全部存进集合里
class Solution2 {
    // 集合的方式效率太低 双指针效率高些
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while(head != null){
            if(set.contains(head)){
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;

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
