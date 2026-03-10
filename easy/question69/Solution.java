package question69;

/**
 * 69. x的平方根
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 */
class Solution {
    public int mySqrt(int x) {
        // 二分查找法
        if (x == 0) return 0;
        // 初始化左边在1
        int left = 1, right = x, ans = 0;

        while (left <= right) {
            // 防止left+right溢出
            int mid = left + (right - left) / 2;
            // 防止mid*mid溢出 怕mid为0所以前面作了处理
            if (mid <= x / mid) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }
}
