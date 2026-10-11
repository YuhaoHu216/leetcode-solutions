import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
// 现在给定一系列方块坐标，依次搭建，要求判定这个搭建序列是否合法
// 要求初始状态只有地面，即z=0；所有方块必须由至少一个面与地面或者其他方块邻接；方块搭建时不行从外部可达（意思是说这个方块一定能从无穷远处通过三方向轴的移动进入搭建位置，也就是说封闭空间无法搭建）；为了方便计算，x，y，z皆为位于[0,100]区间的整数，N<100000
// 输入描述：第一行为一个正整数表示测试样例数量，对于每一个样例，第一行表示正整数M表明该序列有多少方块，接下来M行每行为一个方块的坐标X Y Z，按空格分割；对于每个样例输出YES或NO
public class Question3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        
        
    }
}