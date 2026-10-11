import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
// 冰柜最多放c单位冰块，现有h单位冰块。操作单上按顺序写着n条指令，第i条指令用整数a_i表示，a_i>0表示放入a_i单位冰块，<0表示取出，=0表示不操作。执行一条指令后，冰柜中的冰量会加上该指令的数值，安全的过程是指执行指令后冰量在[0,c]内。对着操作单操作时可能会把任意一处相邻指令看反，要考虑每一种可能的看反位置。具体说对于一个下标i，需要交换第i条和第i+1条指令，然后按交换后的顺序执行全部指令。请计算有多少个下标i能使交换后的整个执行过程仍然安全。注意：一定会交换一对相邻指令，保持原顺序不算一种情况，即使a_i=a_{i+1},下标i仍然是一个可能看法的位置，并在交换后安全时计入答案。当n=1时不存在相邻指令，答案为0
// 1<=n<=2*10^5;0<=h<=c<=10^9;-10^9<=a_i<=10^9,a_i可以为0
public class Question1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt();   // 指令数
        int c = in.nextInt();   // 冰柜容量
        int h = in.nextInt();   // 初始冰量
        // 每条指令 大于0表示放入 等于表示检查（不操作） 小于0表示取出
        int[] a = new int[n];

        for(int i = 0; i < n; i++){
            a[i] = in.nextInt();
        }

        if(n == 1) System.out.println(0);

        // 输出满足条件的交换下标数量
        int[][] dp = new int[n][2];  // dp[i][0] 表示到第i个数满足条件交换下标数量且i不交换，为1则交换

        int result = 0;
        int sum = h;
        for(int i = 0; i < n - 1; i++){

            swap(a,i,i+1);
            if(getResult(a,h)){
                result++;
            }else{
                System.out.println(result);
                return;
            }
            swap(a,i,i+1); 
            

            // if((h+a[i] < c && h+a[i+1] < c) || (h + a[i] > c && h + a[i+1] < c)){
            //     result++;
            // }
            // if(h + a[i] < 0 && h + a[i+1] > 0){
            //     result++;
            // }
            // if((h + a[i] < 0 && h + a[i+1] < 0) || (h + a[i] > c && h + a[i+1] > c)){
            //     System.out.println(result);
            //     return;
            // }
        }

        System.out.println(result);

    }

    private static void swap(int[] a,int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    private static boolean getResult(int[] a,int h){
        int sum = 0;
        for(int num : a){
            sum = sum + num;
            if(sum + h < 0){
                return false;
            }
        }
        return true;
    }
}