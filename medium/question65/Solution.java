package question65;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 */
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int start = lowerBound(nums,target);
        if(start == nums.length || nums[start] != target){
            return new int[] {-1,-1};
        }
        int end = lowerBound(nums,target+1) - 1;
        return new int[] {start,end};
    }

    int lowerBound(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        // 基于闭区间的二分查找,返回的是目标元素第一次出现的下标
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] < target){
                left = mid + 1;
            }
            if(nums[mid] >= target){
                right = mid - 1;
            }
        }
        return left;
    }
}
