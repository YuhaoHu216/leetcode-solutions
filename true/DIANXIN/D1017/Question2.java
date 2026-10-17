package D1017;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Question2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt();
        int k = in.nextInt();

        long[] a = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
        }

        int fast = 0;
        int slow = 0;
        long cur = 0;
        long count = 0;
        long max = 0;

        for(int i = 0; i < k; i++){
            cur = cur + a[fast];
            fast++;
        }

        max = cur;
        count++;
        fast--;
        while (fast <= n - 2) {
            fast++;

            cur = cur - a[slow] + a[fast];

            slow++;
            if(cur > max){
                max = cur;
                count = 1;
            }else if(cur == max){
                count++;
            }
        }

        System.out.println(max + " " + count);
    }
}