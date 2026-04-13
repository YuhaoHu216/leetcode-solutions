package P0421;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 在k个数中找n个数使这些数间的距离最小
 * 输入:
 * 1 表示测试数据的个数
 * 5 4 表示5个位置 4个要占
 * 1 2 8 12 17 表示位置的坐标
 * 输出:
 * 7 放在1 8 17三个位置
 */
public class question02 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        while (t-- > 0) {
            int k = in.nextInt(); // 位置数
            int n = in.nextInt(); // 偶像数

            int[] position = new int[k];
            for (int i = 0; i < k; i++) {
                position[i] = in.nextInt();
            }

            Arrays.sort(position);

            int left = 0;
            int right = position[k - 1] - position[0];
            int ans = 0;

            while (left <= right) {
                int mid = (left + right) / 2;

                if (canPlace(position, n, mid)) {
                    ans = mid;
                    left = mid + 1; // 尝试更大
                } else {
                    right = mid - 1;
                }
            }

            System.out.println(ans);
        }
    }

    // 判断最小距离为dist是否可行
    public static boolean canPlace(int[] pos, int n, int dist) {
        int count = 1; // 第一个已经放了
        int last = pos[0];

        for (int i = 1; i < pos.length; i++) {
            if (pos[i] - last >= dist) {
                count++;
                last = pos[i];
            }
            if (count >= n) return true;
        }

        return false;
    }
}
