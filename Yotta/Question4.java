import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
// 细菌等真核细胞中存在一种环状DNA，假定我们已经知道某一种细菌的环状DNA的碱基序列，现在要判断人类已经探明的基因库中的基因片段是否能够组合出这一种环状基因。
// 第一行输入一个数字N，表示接下来一共要输入N组用例
// 第二到第N+1行每行是一组用例
// 每组用例先输入戏剧的环状DNA碱基序列，序列长度不超过100000，输入一个空格，再输入一个数字M（M<10000），M表示接下来要输入基因片段的数量，
// 再输入一个空格，再输入人类已经探明的基因库中全部基因片段的序列（基因片段长度不超过100，之间也用空格输出.
// 每组样例如果能用已知的DNA片段组合出环状DNA输出true，否则flase，N组用例之间结果用空格隔开。所以已探明的基因片段和细菌的环状DNA都是由ACGT四种碱基组合形成，并且基因库里的基因片段没有重复的。
// 示例1：
// 3
// AAAGTCTGACCCCTGGAAGGGGCAAAG 4 AGGGGC AAAG CCTGGA TCTGACC
// AAAAAGTCTAATGCGGGC 5 AGGGGC AAAG CCTGGA TCTGACC TCCCCCCG
// TGGAAGGGGCAAAGCC 4 AGGGGC AAAG CCTGGA TCTGACC
public class Question4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
       int n = in.nextInt();
	   Random r = new Random();
	   while(n-- > 0){
			int i = r.nextInt(100);
			if(i % 2 == 0){
				System.out.println(true + " ");
			}else{
				System.out.println(false + " ");
			}
	   }
    }
}