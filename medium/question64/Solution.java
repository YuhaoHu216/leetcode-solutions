package question64;

/**
 * 74. 搜索二维矩阵
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 * 每行中的整数从左到右按非严格递增顺序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 */
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        // 从左下角开始
        int i = matrix.length - 1;
        int j = 0;

        while(i >= 0 && j < matrix[0].length){

            if(matrix[i][j] == target) return true;
            if(matrix[i][j] > target) {
                i--;
            }else{
                j++;
            }
        }

        return false;
    }
}