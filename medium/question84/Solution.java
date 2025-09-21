package question84;

import java.util.Arrays;

/**
 * 完全平方数
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * 示例 1：
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * 示例 2：
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 */
class Solution {
    public int numSquares(int n) {

        // dp[i]表示平方和为i的最少完全平方数的个数
        int dp[] = new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);

        dp[0] = 0;
        for(int i = 1; i <= n; i++){
            /**
             假设我们最后枚举了一个平方数j*j,那么剩下i-j*j,
             这个剩下的数是子问题已经有解了,再加上最后枚举的平方数(+1),
             随着j的变化那么最小值也会发生变化
             */
            for(int j = 1; j * j <= i; j++){
                dp[i] = Math.min(dp[i],dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }
}
