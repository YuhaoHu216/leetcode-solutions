package question13;

/**
 * 53.最大子数组和
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组是数组中的一个连续部分。
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 */
class Solution {
    public int maxSubArray(int[] nums) {
        /**
         currentMaxSum：以当前元素 nums[i] 结尾的最大子数组和
         max：遍历到目前为止的全局最大子数组和
         */
        int currentMaxSum = nums[0];
        int max = nums[0];
        for(int i = 1; i < nums.length; i++){
            /**
             对于每一个元素 nums[i]，有两种情况：
             单独成为新的子数组的开始（前面的和是负数，不如重新开始）
             延续前一个子数组（之前的和是正的，可以让整体更大）
             */
            currentMaxSum = Math.max(nums[i],currentMaxSum + nums[i]);
            max = Math.max(max,currentMaxSum);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(new Solution().maxSubArray(nums));
    }
}


