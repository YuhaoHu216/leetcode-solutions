package question04;


import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * 示例 1:
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * tags:双指针
 */
class Solution {
    public void moveZeroes(int[] nums) {
        int slow = 0; // 用于指定非零数的位置
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                int temp = nums[i];
                nums[i] = nums[slow];
                nums[slow] = temp;
                slow++;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

}

class Main{
    public static void main(String[] args) {
        new Solution().moveZeroes(new int[]{0,1,0,3,12});
    }
}
