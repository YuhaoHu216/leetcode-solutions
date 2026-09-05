package question215;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;


/**
 *  215. 数组中的第K个最大元素
 *  给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *  请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *  你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *  示例 1：
 *  输入：nums = [3,2,1,5,6,4], k = 2
 *  输出：5
 *  示例 2：
 *  输入：nums = [3,2,3,1,2,4,5,5,6], k = 4
 *  输出：4
 *
 */
// 快速选择
// 注意获取随机数的语法
class Solution {
        private int quickSelect(List<Integer> nums, int k) {
            // 随机选择基准数
            Random rand = new Random();
            int pivot = nums.get(rand.nextInt(nums.size()));
            // 将大于、小于、等于 pivot 的元素划分至 big, small, equal 中
            List<Integer> big = new ArrayList<>();
            List<Integer> equal = new ArrayList<>();
            List<Integer> small = new ArrayList<>();
            for (int num : nums) {
                if (num > pivot)
                    big.add(num);
                else if (num < pivot)
                    small.add(num);
                else
                    equal.add(num);
            }
            // 因为是第K大，所以元素从大到小整体顺序 big->equal->small
            // 第 k 大元素在 big 中，递归划分
            if (k <= big.size())
                return quickSelect(big, k);
            // 第 k 大元素在 small 中，递归划分 元素从大到小依次在big equal small 中
            if (k > big.size() + equal.size())
                return quickSelect(small, k - big.size() - equal.size());
            // 第 k 大元素在 equal 中，直接返回 pivot
            return pivot;
        }

        public int findKthLargest(int[] nums, int k) {
            List<Integer> numList = new ArrayList<>();
            for (int num : nums) {
                numList.add(num);
            }
            return quickSelect(numList, k);
        }

}

// 用小顶堆（现成数据结构）
class Solution2 {
    public int findKthLargest(int[] nums, int k) {
        // 小顶堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // 弹出最小的
            }
        }
        return minHeap.peek(); // 堆顶就是第k大
    }
}

// 手写小顶堆
class MinHeap {

    private int[] heap;
    private int size;

    public MinHeap() {
        heap = new int[16];
        size = 0;
    }

    public MinHeap(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    // 插入元素
    public void offer(int val) {
        ensureCapacity();

        heap[size] = val;
        siftUp(size);

        size++;
    }

    // 获取堆顶
    public int peek() {
        if (size == 0) {
            throw new RuntimeException("Heap is empty");
        }

        return heap[0];
    }

    // 删除并返回堆顶
    public int poll() {
        if (size == 0) {
            throw new RuntimeException("Heap is empty");
        }

        int result = heap[0];

        // 最后一个元素放到堆顶
        heap[0] = heap[size - 1];
        size--;

        // 向下调整
        siftDown(0);

        return result;
    }

    // 当前堆大小
    public int size() {
        return size;
    }

    // 是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 插入后的上浮操作
    private void siftUp(int index) {
        while (index > 0) {

            int parent = (index - 1) / 2;

            // 父节点比当前节点小，满足小顶堆
            if (heap[parent] <= heap[index]) {
                break;
            }

            swap(parent, index);

            index = parent;
        }
    }

    // 删除后的下沉操作
    private void siftDown(int index) {
        while (true) {

            int left = index * 2 + 1;
            int right = index * 2 + 2;

            int smallest = index;

            // 找三个节点中最小的
            if (left < size && heap[left] < heap[smallest]) {
                smallest = left;
            }

            if (right < size && heap[right] < heap[smallest]) {
                smallest = right;
            }

            // 当前节点已经最小，不需要调整
            if (smallest == index) {
                break;
            }

            swap(index, smallest);

            index = smallest;
        }
    }

    // 扩容
    private void ensureCapacity() {
        if (size < heap.length) {
            return;
        }

        int[] newHeap = new int[heap.length * 2];

        for (int i = 0; i < heap.length; i++) {
            newHeap[i] = heap[i];
        }

        heap = newHeap;
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
