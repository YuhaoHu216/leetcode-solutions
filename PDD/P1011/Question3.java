package P1011;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
// 多多计划在N个城市中构建一张冷链物流网络，使得这N个城市之间能够互相进行生鲜调拨。这N个城市之间已经有M条双向干线公路相连。构建物流网络的方式有两种，可以组合使用。
// 1.开通干线运输：对于已有的每条公路，平台可以花费代价w将其开通为冷链运输干线。开通后，该公路两端的城市即可互相调拨生鲜，
// 2.建立集散中心：可以在某个城市i建立一个物流集散中心，代价为Ci，所有建立了集散中心的城市，将通过平台专属的航空冷链通道互相连通，且视为已经在物流网络中连通。
// 物流网络中心必须有一个集散中心，当N=1时，也需要建立，计算为了使这N个城市全都能互相调拨生鲜，最少需要花费多少总代价。
// 第一行输入两个整数N和M（N在1到1万之间，M在0到2万间），分别表示城市数量和已有干线公路数量。第二行输入N个整数（1到1亿之间），表示在每个城市建立集散中心的代价。
// 接下来的M行每行输入三个整数u，v，W（u>=1,v<=N,u!=v,1<=W<=10^9)，表示一条已有公路连接的端点u和v，以及将其开通为冷链干线的代价W，保证不存在重边。
public class Question3 {
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