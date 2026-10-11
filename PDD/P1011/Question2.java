package P1011;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
// 团子每天都会称重，所有数值以电子秤的最小刻度作为一个单位，用整数表示。秤不够精准，第i天的记录是一个闭区间[l_i,r_i]，表示根据这一天的度数，团子体重可能是该区间内的某个整数，这样的记录一共有n条，并按日期先后顺序排列。团子想找出体重可能保持不变的最长一段连续日子，需要选择这一段非空的连续日子，其下标为[L,R]。这段日子中可以把至多一天的记录视为电子秤发生故障并忽略，也可以不忽略任何记录。忽略后必须至少保留一天的记录，并且存在一个整数x，使所有保留记录对应的区间都包含x，也就是说在所有没有被忽略的日志里，团子的体重都有可能使同一个整数x，这段连续的日子长度定义为R-L+1，即使其中一天的记录被忽略，这一天仍然计入所选连续段的长度。请输出满足条件的连续日子的最大长度。n在1到2万之间，1<=l_i<=r_i<=10^9)
public class Question2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
    }
}