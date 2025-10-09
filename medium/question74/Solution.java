package question74;

import java.util.PriorityQueue;

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
 */
class Solution {
    public int findKthLargest(int[] nums, int k) {

        // 构造小顶堆，元素由底向上递减
        PriorityQueue<Integer> minMeap = new PriorityQueue<>();
        // 将所有元素都加入堆,但是堆里最多保留K个元素
        for(int num:nums){
            minMeap.offer(num);
            if(minMeap.size() > k){
                minMeap.poll();
            }
        }

        return minMeap.peek();
    }
}
