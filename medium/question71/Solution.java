package question71;

import java.util.LinkedList;

/**
 * 字符串解码
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
    public String decodeString(String s) {

        int multi = 0;
        LinkedList<Integer> stack_multi = new LinkedList<>();
        LinkedList<String> stack_string = new LinkedList<>();
        StringBuilder result = new StringBuilder();

        for(char c : s.toCharArray()){
            // 左括号进行入栈(乘数和已排好的字符串),并且准备下一次入栈
            if(c == '['){
                stack_multi.push(multi);
                stack_string.push(result.toString());
                multi = 0;
                result = new StringBuilder();
            }
            // 右括号进行出栈,将已有字符串重复并且和最新入栈的字符串拼接
            else if(c == ']'){
                int current_multi = stack_multi.pop();
                StringBuilder temp = new StringBuilder();
                String pre_string = stack_string.pop();
                for(int i = 0; i < current_multi ; i++) temp.append(result);
                result = new StringBuilder(pre_string + temp.toString());

            }
            // 如果是数字就记录成乘数,注意有连续数字的情况
            else if(c >= '0' && c <= '9'){
                multi = multi * 10 + Integer.parseInt(c + "");
            }
            // 字母就直接添加到最终结果里
            else{
                result.append(c);
            }

        }
        return result.toString();
    }
}