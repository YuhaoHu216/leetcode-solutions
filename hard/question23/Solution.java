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
 // 归并解法（分治：两两合并，共合并 log k 层）
 // time O(N log k)  分治树高 log k，每一层把所有节点都过一遍；比一个个顺序合并的 O(N*k) 快
 // space O(log k)  递归栈深度 log k；mergeTwoLists 只用了 dummy、tail 两个指针，不新建节点
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
// time O(N log k)  先用 k 个链表头建堆 O(k log k)，之后每个节点入堆、出堆各一次，每次 O(log k)
// space O(k)  堆数组只放 k 条链表各自当前的头节点，原节点被直接串起来不新建
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
 // time O(N log k)  同手写堆：每个节点 offer、poll 各一次，每次 O(log k)
 // space O(k)  队列里最多同时存 k 个节点
class Solution3 {
    public ListNode mergeKLists(ListNode[] lists) {

        if(lists == null || lists.length == 0) return null;

        // 定义优先队列,按节点值构造小顶堆（小顶堆就是poll最小值的堆）
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

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}