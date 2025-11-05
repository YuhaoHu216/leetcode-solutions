package question79;

/**
 * 45. 跳跃游戏II
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置在下标 0。
 * 每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在索引 i 处，你可以跳转到任意 (i + j) 处：
 * 0 <= j <= nums[i] 且
 * i + j < n
 * 返回到达 n - 1 的最小跳跃次数。测试用例保证可以到达 n - 1。
 */
class Solution {
    public int jump(int[] nums) {

        int start = 0;
        int rightmost = 1;
        int result = 0;

        while(rightmost < nums.length){
            int nextmost = 0;
            for(int i = start; i < rightmost; i++){
                nextmost = Math.max(nextmost,nums[i] + i);
            }
            start = rightmost;
            rightmost = nextmost+1;
            result++;
        }

        return result;
    }
}
class Solution2 {
    public int jump(int[] nums) {
        // 给每一个段设置一个终点,维护到右边的最大值
        int end = 0;
        int rightmost = 0;
        int result = 0;
        for(int i = 0; i < nums.length - 1; i++){
            // 对每个数都跳一下取出最大范围
            rightmost = Math.max(rightmost,nums[i] + i);
            // 当i到了上一跳范围的末尾时,更新末尾值,并开始新的一步
            if(i == end){
                end = rightmost;
                result++;
            }
        }
        return result;
    }
}

