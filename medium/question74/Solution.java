package question74;

/**
 * 74. 搜索二维矩阵
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 * 每行中的整数从左到右按非严格递增顺序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 *
 */
// 该解法时间复杂度为 O(m+n)
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

// 该解法时间复杂度为O(log(m * n))
// 两次二分（先第一列二分找到最后一个不大于目标值的元素，然后在该元素所在行中二分查找目标值是否存在）
class Solution2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowIndex = binarySearchFirstColumn(matrix, target);
        if (rowIndex < 0) {
            return false;
        }
        return binarySearchRow(matrix[rowIndex], target);
    }

    public int binarySearchFirstColumn(int[][] matrix, int target) {
        int low = -1, high = matrix.length - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (matrix[mid][0] <= target) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public boolean binarySearchRow(int[] row, int target) {
        int low = 0, high = row.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (row[mid] == target) {
                return true;
            } else if (row[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }
}

// 把每一行拼接成一行就可以用一次二分得到答案
class Solution3 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int low = 0, high = m * n - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            // 得到原矩阵中的值
            int x = matrix[mid / n][mid % n];
            if (x < target) {
                low = mid + 1;
            } else if (x > target) {
                high = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}

