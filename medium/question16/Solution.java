package question16;

import java.util.Arrays;

/**
 * 238.除自身以外数组的乘积
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * 示例 1:
 *
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 */
public class Solution {
    public int[] productExceptSelf(int[] nums) {
        // 思路:计算每个元素左边的所有数乘积和右边所有数乘积
        int n = nums.length;
        int[] result = new int[n];
        // 计算每一个数左边的乘积
        result[0] = 1;
        for(int i = 1; i < n; i++){
            result[i] = result[i-1] * nums[i-1];
        }
        // 计算每个数右边的乘积并且相乘得到答案
        int right = 1;
        for(int i = n - 1; i >=0; i--){
            result[i] *= right;
            right *= nums[i];   // 更新right的值
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(new Solution().productExceptSelf(nums)));
    }

}
