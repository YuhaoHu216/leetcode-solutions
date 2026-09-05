package question394;

import java.util.LinkedList;

/**
 * 394. 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * 测试用例保证输出的长度不会超过 105。
 * 示例 1：
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 */
class Solution {
    // 双栈解法：kStack 存每一层括号的重复次数 k，prevStack 存每一层括号之前的字符串
    public String decodeString(String s) {

        int curK = 0;                                     // 当前这一层正在累积的重复次数
        LinkedList<Integer> kStack = new LinkedList<>();  // 各层【重复次数 k】的栈
        LinkedList<String> prevStack = new LinkedList<>();// 各层【已拼好的字符串】的栈
        StringBuilder curStr = new StringBuilder();       // 当前这一层正在拼接的字符串

        for (char c : s.toCharArray()) {
            // 遇到 '['：进入新的一层，把当前层状态入栈保存，然后重置
            if (c == '[') {
                kStack.push(curK);
                prevStack.push(curStr.toString());
                // 重置乘数和要乘的字符串
                curK = 0;
                curStr = new StringBuilder();
            }
            // 遇到 ']'：结束一层，把本层字符串重复 k 次后拼回上一层
            else if (c == ']') {
                int k = kStack.pop();
                String prev = prevStack.pop();
                StringBuilder segment = new StringBuilder();
                for (int i = 0; i < k; i++) segment.append(curStr);
                curStr = new StringBuilder(prev).append(segment);
            }
            // 是数字：累积成 k（注意连续数字表示多位数）
            else if (c >= '0' && c <= '9') {
                curK = curK * 10 + Integer.parseInt(c + "");
            }
            // 是字母：直接加到当前层
            else {
                curStr.append(c);
            }
        }
        return curStr.toString();
    }
}