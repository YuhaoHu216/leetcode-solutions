package question01;

import java.util.Arrays;
import java.util.HashMap;

/**
 *给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
 *
 * 你可以按任意顺序返回答案。
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 */
public class Solution {
    public int[] towSum(int [] nums,int target){
        HashMap<Integer ,Integer> hashMap = new HashMap<>();
        for(int i = 0;i < nums.length; i++){
            int theOtherNum = target - nums[i];
            if(hashMap.containsKey(theOtherNum)){
                return new int[] {i,hashMap.get(theOtherNum)};
            }
            hashMap.put(nums[i],i);
        }
        return new int[] {};
    }
}
class Main{
    public static void main(String[] args){
            int[] nums = {2,7,11,15};
            int target = 9;
            System.out.println(Arrays.toString(new Solution().towSum(nums, target)));
    }
}
