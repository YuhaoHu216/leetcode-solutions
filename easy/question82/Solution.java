package question82;

import java.util.ArrayList;
import java.util.List;

/**
 * 118.杨辉三角
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * 示例 1:
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * 示例 2:
 * 输入: numRows = 1
 * 输出: [[1]]
 */
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        // i表示要构造第几层
        for(int i = 0; i < numRows; i++){
            // 每一层用一个临时集合存储
            List<Integer> row = new ArrayList<>();
            // j表示该构造该层第几个元素(注意是<=)
            for(int j = 0; j <= i; j++){
                // 首尾两数是1
                if(j == 0 || j == i ){
                    row.add(1);
                    // 其他数是上一层的两数之和
                }else{
                    int left = result.get(i-1).get(j-1);
                    int right = result.get(i-1).get(j);
                    row.add(left + right);
                }
            }
            result.add(row);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().generate(5));
    }
}
