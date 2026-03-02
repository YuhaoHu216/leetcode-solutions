package question23;

import java.util.PriorityQueue;

/**
 * 23.合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[]
 */
class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

 // 归并解法
class Solution1 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        int mid = left + (right - left) / 2;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }
    // 合并两个升序链表
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        tail.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }
}

// 手写堆解法
class Solution2 {
    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) return null;

        MinHeap heap = new MinHeap(lists.length);

        // 把每个链表头加入堆
        for (ListNode node : lists) {
            if (node != null) heap.offer(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!heap.isEmpty()) {
            ListNode minNode = heap.poll();
            tail.next = minNode;
            tail = tail.next;

            if (minNode.next != null) {
                heap.offer(minNode.next);
            }
        }

        return dummy.next;
    }

    class MinHeap {

        private ListNode[] heap;
        private int size;

        public MinHeap(int capacity) {
            heap = new ListNode[capacity];
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 插入元素
        public void offer(ListNode node) {
            heap[size] = node;
            siftUp(size);
            size++;
        }

        // 删除最小值
        public ListNode poll() {
            if (size == 0) return null;

            ListNode min = heap[0];
            heap[0] = heap[size - 1];
            size--;
            siftDown(0);
            return min;
        }

        // 上浮
        private void siftUp(int index) {
            while (index > 0) {
                int parent = (index - 1) / 2;

                if (heap[parent].val <= heap[index].val) break;

                swap(parent, index);
                index = parent;
            }
        }

        // 下沉
        private void siftDown(int index) {
            while (true) {
                int left = index * 2 + 1;
                int right = index * 2 + 2;
                int smallest = index;

                if (left < size && heap[left].val < heap[smallest].val)
                    smallest = left;

                if (right < size && heap[right].val < heap[smallest].val)
                    smallest = right;

                if (smallest == index) break;

                swap(index, smallest);
                index = smallest;
            }
        }

        private void swap(int i, int j) {
            ListNode temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
    }
}

 // api 解法优先队列 不推荐
class Solution3 {
    public ListNode mergeKLists(ListNode[] lists) {

        if(lists == null || lists.length == 0) return null;

        // 定义优先队列,元素值由小到大排列
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                (a,b) -> a.val - b.val
        );
        // 将每个链表头节点放入优先队列
        for(ListNode node : lists){
            if(node != null){
                pq.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        // 将优先队列的最小节点链接
        while(!pq.isEmpty()){
            ListNode minNode = pq.poll();
            tail.next = minNode;
            tail = tail.next;
            if(minNode.next != null){
                pq.offer(minNode.next);
            }
        }
        return dummy.next;
    }
}