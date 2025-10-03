package question11;

/**
 * 239.滑动窗口的最大值
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回 滑动窗口中的最大值 。
 * 示例 1：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * 示例 2：
 * 输入：nums = [1], k = 1
 * 输出：[1]
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n-k+1];
        // 单调队列,存储窗口中元素的下标(按对应元素大小降序)
        int[] deque =  new int[n];
        // 方便获取和更新窗口中元素及其最大值
        int head = 0;
        int tail = -1;

        for(int i = 0; i < n; i++){
            // 窗口滑动,判断当前最大值(队首元素)是否还在窗口里
            if(head <= tail && deque[head] <= i-k) head++;
            // 判断要入队的元素大小,把比这个元素小的全部去除(做不了最大值了)
            while(head <= tail && nums[deque[tail]] <= nums[i]) tail--;
            // 将元素入队
            deque[++tail] = i;
            // 当形成第一个完整窗口时开始记录
            if(i >= k-1){
                result[i-k+1] = nums[deque[head]];
            }
        }

        return result;
    }
}