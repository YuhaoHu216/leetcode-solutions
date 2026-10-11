package P1011;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
// 多多计划在一座城市里完成一次环线巡游，城市中有n个景点（编号1到n）和m条道路，道路按编号1到m给出，第i条道路连接景点ui和vi，通行需要支付wi的过路费。保证整座城市是连通的，即任意两个景点互相可达。注意道路中可能存在自环和重边。多多初始在景点1，可以进行以下两类操作任意多次：
// 步行：沿一条连接当前景点x与某相邻景点y的道路行走，对该道路完成巡检记录，并到达y，花费恰为该道路的过路费。每条道路至少要被巡检一次。
// 快线：从当前景点x乘坐快线转移到任意其他景点z!=x，可自选一条从x到z的路线（可以重复经过景点和道路），票价仅为该路线上编号最大的那条道路的过路费。请计算多多从景点1触发，把每条道路都至少巡检一次，并最终回到景点1的最小总花费。
// 1<=n<=2*10^5,0<=m<=2*10^5 ；接下来m行，第i行包含三个整数ui,vi,wi(1<=ui,vi<=n,1<=wi<=10^9)表示第i条道路连接景点ui和vi，过路费为wi。保证每组数据描述的图是连通的，图可能包含自环和重边，保证所有测试数据中n的总和与m的总和均不超过5*10^5
public class Question4 {
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