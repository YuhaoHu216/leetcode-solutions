/**
 * 题目描述
 * 给定一个包含 n 个整数的数组 nums，以及一个目标值 m。
 * 你可以从数组中选择 0 到 n 个数字，但每个数字最多只能被选择一次。
 * 对于选中的数字，你可以选择：
 * 直接使用该数字；
 * 将该数字除以 2 并向下取整后使用。
 * 其中，除以 2 的操作最多只能使用一次，也可以完全不使用。
 * 问：是否存在一种选择方式，使得所有选中的数字经过上述操作后，其总和恰好等于 m。
 */
class Solution {
    public boolean canReach(int[] nums, int m) {
        boolean[][] dp = new boolean[m + 1][2];

        // 什么数字都不选，可以凑出 0
        // 并且此时还没有使用减半操作
        dp[0][0] = true;

        for (int x : nums) {
            boolean[][] next = new boolean[m + 1][2];

            for (int sum = 0; sum <= m; sum++) {
                // 当前数字不选
                if (dp[sum][0]) {
                    next[sum][0] = true;
                }

                if (dp[sum][1]) {
                    next[sum][1] = true;
                }

                // 当前数字正常使用
                if (sum + x <= m) {
                    if (dp[sum][0]) {
                        next[sum + x][0] = true;
                    }

                    if (dp[sum][1]) {
                        next[sum + x][1] = true;
                    }
                }

                // 当前数字减半后使用
                int half = x / 2;

                if (sum + half <= m && dp[sum][0]) {
                    next[sum + half][1] = true;
                }
            }

            dp = next;
        }

        // 不使用减半也可以，或者使用了一次也可以
        return dp[m][0] || dp[m][1];
    }
}
