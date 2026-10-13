package Y1013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 【题目名称】工作任务
 *
 * 【题目内容】
 * 策划小 x 又开始了一天的工作！
 * 今天小 x 可以处理 n 个任务，每个任务有一个重要度分数 w。如果小 x 此时体力值为 p，
 * 那他就会为自己评估一个质量分，分数为 w * p。
 * 小 x 一早的体力值为 1，他有一个衰变常数 c（0 < c < 1），每处理完一个任务 p = p * c。
 * 小 x 是强迫症，他必须要按顺序处理这些任务，但他可以选择跳过一些任务
 * （不必担心，这些任务可以明天完成！）你能帮小 x 计算一下今天他最多能拿到多少总质量分吗？
 *
 * 【输入格式】
 * 第一行，两个用空格隔开的数：整数 n 和实数 c
 * 第二行，n 个整数，表示任务分值
 *
 * 【输出格式】
 * 一个实数，表示 ans。（答案保留小数点后两位）
 *
 * 【数据范围与约定】
 * n <= 100000
 *
 * 【样例输入】
 * 3 0.5
 * 1 2 3
 *
 * 【样例输出】
 * 3.50
 *
 * 【样例解释】
 * 选做第 2、3 题，ans = 2 * 1 + 3 * 0.5 = 3.5
 * */
public class Question1 {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        double c = in.nextDouble();

        //「假定当前位置的体力系数为 1 时，从该位置往右的后缀最多还能追加多少分（相对倍数）」。从右往左扫，每个任务只有两种可能：
        // - 跳过：体力不衰减，后缀仍以系数 1 计 → 增量 V
        // - 选它：先拿 w[i]*1，体力变成 c，后缀里所有任务系数统一乘 c → 增量 w[i] + c*V
        double v = 0.0; // 后缀（相对系数 1）能追加的最大质量分
        double[] w = new double[n];
        for (int i = 0; i < n; i++) {
            w[i] = in.nextDouble();
        }

        // 从后往前遍历，遍历过得到的值只用乘以系数在加上当前值得到的就是从当前值开始的答案
        for (int i = n - 1; i >= 0; i--) {
            v = Math.max(v, w[i] + c * v);
        }

        System.out.printf("%.2f%n", v);
    }
}