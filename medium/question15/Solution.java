package question15;

import java.util.Arrays;

/**
 * 189.轮转数组
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * 示例 1:
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 */
public class Solution {
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        k = k % length;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[(i + k) % length] = nums[i];
        }
        System.arraycopy(result, 0, nums, 0, length);
        System.out.println(Arrays.toString(nums));
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
//        int[] nums = {-1,-100,3,99};
        int k = 2;
        new Solution().rotate(nums,k);
    }
}
