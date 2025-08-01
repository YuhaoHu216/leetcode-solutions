package question14;
/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public int[][] merge(int[][] intervals) {
//        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));  // 更正规的排序写法
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        ArrayList<int[]> result = new ArrayList<>();
        for(int[] i : intervals){
            int size = result.size();
            if(result.isEmpty() || result.get(size-1)[1] < i[0]){
                result.add(i);
            }else{
                result.get(size -1)[1] = Math.max(result.get(size-1)[1],i[1]);
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}} ;
        System.out.println(Arrays.deepToString(new Solution().merge(intervals)));
    }
}
