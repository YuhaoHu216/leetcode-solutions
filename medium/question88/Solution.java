package question88;

/**
 * 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 测试用例的答案是一个 32-位 整数。
 * 示例 1:
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
class Solution {
    public int maxProduct(int[] nums) {
        int imax = 1;
        int imin = 1;
        int max = Integer.MIN_VALUE;

        // 要同时维护最小值和最大值，因为如果有负数这两个值就会互换
        for(int i = 0; i < nums.length; i++){
            if(nums[i] < 0){
                int temp = imax;
                imax = imin;
                imin = temp;
            }

            imax = Math.max(nums[i]*imax,nums[i]);
            imin = Math.min(nums[i]*imin,nums[i]);

            max = Math.max(max,imax);
        }

        return max;
    }
}