package question68;

/**
 * 4.寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 */
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        // 如果是奇数长度，left==right；否则取平均
        return (findKth(nums1, 0, nums2, 0, left)
                + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }

    private int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
        // 边界条件
        if (i >= nums1.length) return nums2[j + k - 1];
        if (j >= nums2.length) return nums1[i + k - 1];
        if (k == 1) return Math.min(nums1[i], nums2[j]);

        // 分别取第 k/2 个元素（若越界，用 Integer.MAX_VALUE）
        int mid1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int mid2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;

        if (mid1 < mid2) {
            // 丢弃 nums1 的前 k/2 个
            return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
        } else {
            // 丢弃 nums2 的前 k/2 个
            return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
        }
    }
}
