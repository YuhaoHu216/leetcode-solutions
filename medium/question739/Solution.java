package question739;

import java.util.LinkedList;

/**
 * 739. 每日温度
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，
 * 下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 */
class Solution {
    // 当前温度比栈顶那天更高 → 当前天就是栈顶天的"下一个更高温度日"（一次性结算前面所有能被当前天解决的下标）
    public int[] dailyTemperatures(int[] temperatures) {

        int days = temperatures.length;
        int[] result = new int[days];
        LinkedList<Integer> stack = new LinkedList<>(); // 单调栈，存"还没找到更高温度"的天下标，栈内温度从底到顶递减

        for(int i = 0; i < days; i++){

            while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i] ){
                int preIndex = stack.pop();          // 弹出：它已经找到答案，出现了温度更高的天
                result[preIndex] = i - preIndex;     // 答案 = 当前下标 - 被结算下标，即相差几天
            }

            stack.push(i);  // 当前天入栈，等未来更高的温度来"认领"它
        }

        return result;  // 栈里剩下的下标说明之后没有更高温度，默认值 0 即答案
    }
}