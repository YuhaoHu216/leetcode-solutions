package question215;

import java.util.ArrayList;
import java.util.List;
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
            // 第 k 大元素在 big 中，递归划分
            if (k <= big.size())
                return quickSelect(big, k);
            // 第 k 大元素在 small 中，递归划分 这里条件可等价为 big.size() + equal.size() < k 元素从大到小依次在big equal small 中
            if (nums.size() - small.size() < k)
                return quickSelect(small, k - nums.size() + small.size());
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
