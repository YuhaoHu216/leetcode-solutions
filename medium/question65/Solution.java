package question65;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 *
 */
class Solution {
    public int[] searchRange(int[] nums, int target) {
        // 利用二分查找找到目标数(该)出现的位置和比目标数大1数(该)出现的位置
        int start = lowerBound(nums,target);
        // 由于查找的是该出现的位置(可能不出现所以要做判断)
        if(start == nums.length || nums[start] != target){
            return new int[] {-1,-1};
        }
        int end = lowerBound(nums,target+1) - 1;
        return new int[] {start,end};
    }

    private int lowerBound(int[] nums,int target){
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            // 核心实在让left在第一个大于等于target的数上
            if(nums[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return left;
    }
}