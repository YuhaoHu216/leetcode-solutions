package question03;

import java.util.*;

/**
 * 128.最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * tags:哈希表
 */
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(Integer i : nums){
            set.add(i);
        }

        int maxLength = 0;
        int currentNum = 0;
        int currentLength = 0;
        for(Integer i : set){
            if(!set.contains(i-1)){
                currentNum = i;
                currentLength = 1;
                while(set.contains(currentNum+1)){
                    currentLength++;
                    currentNum++;
                }
                if(currentLength > maxLength){
                    maxLength = currentLength;
                }
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {100,4,200,1,3,2};
        System.out.println(new Solution().longestConsecutive(nums));
    }
}


