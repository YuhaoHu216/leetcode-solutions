package quesiton10;

import java.util.HashMap;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 *
 * 子数组是数组中元素的连续非空序列。
 * 示例 1：
 *
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 */
public class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        HashMap<Integer,Integer> preSum = new HashMap<>();
        preSum.put(0,1);
        for(int num : nums){
            sum += num;
            count += preSum.getOrDefault(sum-k,0);
            preSum.put(sum,preSum.getOrDefault(sum,0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1};
        System.out.println(new Solution().subarraySum(nums,2));
    }
}
