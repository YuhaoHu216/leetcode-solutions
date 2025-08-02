package question19;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 */
public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length; // 行数
        int n = matrix[0].length; // 列数
        List<Integer> result = new ArrayList<>();
        int top = 0;
        int left = 0;
        int right = n - 1;
        int bottom = m - 1;
        while(top <= bottom && left <= right){
            // 从左到右
            for(int i = left; i <= right; i++){
                result.add(matrix[top][i]);
            }
            top++;

            // 从上到下
            for(int i = top; i <= bottom; i++){
                result.add(matrix[i][right]);
            };
            right--;

            // 从右到左
            if(top <= bottom){
                for(int i = right; i >= left; i--){
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }

            if(left <= right){
                // 从下到上
                for(int i = bottom; i >= top; i--){
                    result.add(matrix[i][left]);
                }
                left++;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(new Solution().spiralOrder(matrix));
    }
}
