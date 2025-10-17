package question14;
/**
 * 56.合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int[][] merge(int[][] intervals) {
        // 表示已经合并好的区间
        List<int[]> result = new ArrayList<>();
        // 排序数组,方便看有没有重叠(注意函数用法)
        Arrays.sort(intervals,(a,b) -> a[0] - b[0]);
        // 表示正在处理的区间
        int[] temp = intervals[0];
        // res.add(current) 先添加第一个区间，是为了保证结果列表至少有一个区间可供后续合并修改。
        // 后续合并时会直接更新 current 的右边界，结果列表同步更新。
        result.add(temp);
        for(int[] interval : intervals){
            if(interval[0] <= temp[1]){
                temp[1] = Math.max(interval[1],temp[1]);
            }else{
                temp = interval;
                result.add(temp);
            }
        }
        // 将集合转换为数组(注意函数用法)
        return result.toArray(new int[result.size()][]);

    }


    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}} ;
        System.out.println(Arrays.deepToString(new Solution().merge(intervals)));
    }
}
