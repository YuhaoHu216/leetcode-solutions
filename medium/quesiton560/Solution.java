package quesiton560;

import java.util.HashMap;

/**
 * 560.和为K的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * 子数组是数组中元素的连续非空序列。
 * 示例 1：
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 */
public class Solution {
    public int subarraySum(int[] nums, int k) {
        // 前缀和解法 一个前缀和减去另外一个前缀和差为K表示这两个前缀不重合的数的和为K
        // 使用map记录某个前缀和出现的次数 注意getOrDefault()的用法
        int count = 0;
        int sum = 0;
        HashMap<Integer,Integer> preSum = new HashMap<>();
        preSum.put(0,1);
        for(int num : nums){
            sum += num; // 计算前缀和
            count += preSum.getOrDefault(sum-k,0);  // 计算当前前缀和与之前的前缀和间和为K的字串有几个
            preSum.put(sum,preSum.getOrDefault(sum,0) + 1); // 把当前前缀和入map
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1};
        System.out.println(new Solution().subarraySum(nums,2));
    }
}
