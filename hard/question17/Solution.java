package question17;

/**
 * 41.缺失的第一个正数
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 * 示例 1：
 * 输入：nums = [1,2,0]
 * 输出：3
 * 解释：范围 [1,2] 中的数字都在数组中。
 * 示例 2：
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * 解释：1 在数组中，但 2 没有。
 * 示例 3：
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 * 解释：最小的正数 1 没有出现。
 */
class Solution {
    public int firstMissingPositive(int[] nums) {
        /**
         思路:因为是找缺失的第一个正数,所以这个数一定会在1~n范围内,最大情况就是数组内是连续的正数,那么
         缺失数就是n.所以只需要将1~n间的数放到对应位置,比如1在索引0上,n在索引n-1上
         */

        int n = nums.length;
        for(int i = 0; i < n; i++){
            // 当这个数在1~n,并且没在它该在的位置上时.将其交换上去
            while(nums[i] > 0 && nums[i] <=n && nums[nums[i]-1] != nums[i]){
                // 注意交换的逻辑只能这样写,先转移nums[i]的话会导致索引错乱
                int temp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = temp;
            }
        }

        for(int i = 0; i < n; i++){
            if(nums[i] != i+1){
                return i+1;
            }
        }

        return n+1;
    }
}

class Solution2 {
    public int firstMissingPositive(int[] nums) {

        int n = nums.length;
        int[] result = new int[n+1];
        for(int num:nums){
            if(num>0 && num<=n){
                result[num-1] = 1;
            }
        }

        for(int i = 0; i < n; i++){
            if(result[i] == 0){
                return i+1;
            }
        }

        return n+1;
    }
}