package question21;

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length; // 行数
        int n = matrix[0].length; // 列数
        int row = 0;  // 行索引
        int col = n - 1;  // 列索引
        while(row < m && col >= 0 ){
            if(matrix[row][col] == target){
                return true;
            }else if(matrix[row][col] > target) {
                col--;
            }
            else row++;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        System.out.println(new Solution().searchMatrix(matrix,5));
    }
}
