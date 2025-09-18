package question72;

import java.util.LinkedList;

/**
 * 每日温度
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，
 * 下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 */
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {

        int days = temperatures.length;
        int[] result = new int[days];
        LinkedList<Integer> stack = new LinkedList<>();

        for(int i = 0; i < days; i++){

            while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i] ){
                int preIndex = stack.pop();
                result[preIndex] = i - preIndex;
            }

            stack.push(i);
        }

        return result;
    }
}