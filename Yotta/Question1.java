import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
// 在一个大小1000*1000的地图中，有若干物体，现在有一个相机，相机视野为100*100，移动相机坐标（x，y）（50<=x,y<=950,相机座标处在相机视野中心），会观察到不同数量的舞台，求问同时出现在相机视野里包括边缘的舞台数量最多是多少。
// 输入描述，第一行输入正整数N，表示地图的里有N个物体。然后输入N行，每行输入物体第i行的坐标Xi和Yi，Xi和Yi空格分开。
public class Question1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt();

        int[][] points = new int[n][2];

        for(int i = 0; i < n; i++){
            points[i][0] = in.nextInt();
            points[i][1] = in.nextInt();
        }
        int result = 0;

        for(int i = 50; i <= 950; i++){
            for(int j = 50; j <= 950; j++){
                int temp = 0;

                int xMin = i - 50;
                int xMax = i + 50;
                int yMin = j - 50;
                int yMax = j + 50;
                for(int[] point : points){
                    if(xMin <= point[0] && point[0] <= xMax && yMin <= point[1] && point[1] <= yMax){
                        temp++;
                    }
                }

                result = Math.max(temp,result);
            }
        }

        System.out.println(result);


    }
}

/**
    x,y = (50,50)
    (0,100)
 */