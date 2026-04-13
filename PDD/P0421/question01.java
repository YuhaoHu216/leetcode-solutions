package P0421;

import java.util.Scanner;

/**
 * 题目描述
 * 输入n和m n表示有多少条赛道 m表示每条赛道多长
 * 接下来会输入m组数字 表示从第li条赛道到第lr赛道有一段水泥路 每段水泥路都是1米
 * 在水泥路段上行驶速度为1m/s  其他路段上行驶为0.5m/s
 * 求选择那条赛道用时最短 如果存在多条这样的赛道时，输出最短的赛道编号
 * 样例:
 * 输入:
 * 3 2  // 3条赛道 每条赛道都是2m
 * 1 2  // 第1条赛道和第二条赛道有一段水泥路
 * 2 3  // 第2条赛道和第三条赛道有一段水泥路
 * 输出:
 * 2 2  // 2号赛道覆盖水泥路最多所以最快 用时2s
 */
public class question01 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] nm = in.nextLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        // 用一个数组记录每条赛道的水泥路段数会超时 使用差分降时间复杂度
        int[] diff = new int[n + 2]; // 差分数组

        // 处理区间
        for (int i = 0; i < m; i++) {
            String[] temp = in.nextLine().split(" ");
            int li = Integer.parseInt(temp[0]);
            int lr = Integer.parseInt(temp[1]);
            // 这里使用差分数组 不差分直接遍历+1会超时
            diff[li] += 1;
            diff[lr + 1] -= 1;
        }

        int max = 0;
        int index = 0;
        int cur = 0;

        // 前缀和 + 找最大值
        for (int i = 1; i <= n; i++) {
            cur += diff[i];
            if (cur > max) {
                max = cur;
                index = i;
            }
        }

        int fast = max;
        int slow = m - max;
        int result = fast + 2 * slow;

        System.out.println(result + " " + index);
    }
}
