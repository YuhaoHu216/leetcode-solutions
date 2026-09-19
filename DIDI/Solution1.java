import java.util.*;

/**
 * 紧急制冷
 *
 * 有 n 台机器，第 i 台机器当前温度为 height[i]。
 * 每进行一次紧急制冷操作，需要选择一台机器作为重点制冷机器：
 * 被选中的机器温度下降 a 度；
 * 其他 n-1 台机器温度各下降 b 度。
 * 每次操作只能选择一台机器。
 * 求：至少需要进行多少次操作，才能使所有机器的温度都小于等于 0。
 *
 * a了70%多超时 k*nlog(n)
 * 可以用二分法优化 nlogk
 *
 */
class Solution1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String firstLine = sc.nextLine();
        String[] param = firstLine.split(" ");
        int n = Integer.parseInt(param[0]);
        int a = Integer.parseInt(param[1]);
        int b = Integer.parseInt(param[2]);

        int[] height = new int[n];

        for(int i = 0; i < n; i++){
            height[i] = sc.nextInt();
        }

        int result = 0;
        // 查找数组中的最大数，让其减去a，让其他数减去b
        // 重复该操作 直到所有数小于0
        while(!isRight(height)){
            Arrays.sort(height);
            for(int i = 0; i < n - 1; i++){
                height[i] = height[i] - b;
            }
            height[n-1] = height[n-1] - a;
            result++;
        }

        System.out.println(result);


    }
    private static boolean isRight(int[] nums){
        for(int num : nums){
            if(num > 0) return false;
        }
        return true;
    }
}

class Solution1_2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long a = sc.nextLong();
        long b = sc.nextLong();

        long[] height = new long[n];

        for (int i = 0; i < n; i++) {
            height[i] = sc.nextLong();
        }

        // a <= b 的情况单独处理
        if (a <= b) {
            long max = 0;

            for (long h : height) {
                max = Math.max(max, h);
            }

            System.out.println((max + b - 1) / b);
            return;
        }

        long left = 0;
        long right = 1;

        // 找一个可行的右边界
        while (!check(height, a, b, right)) {
            right *= 2;
        }

        // 二分答案
        while (left < right) {
            long mid = left + (right - left) / 2;

            if (check(height, a, b, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }

    private static boolean check(long[] height, long a, long b, long k) {

        long extra = a - b;
        long need = 0;

        for (long h : height) {

            long remain = h - k * b;

            if (remain > 0) {
                // 需要额外重点制冷的次数
                long x = (remain + extra - 1) / extra;

                need += x;

                // 提前结束，防止 need 溢出，也减少计算
                if (need > k) {
                    return false;
                }
            }
        }

        return need <= k;
    }
}

