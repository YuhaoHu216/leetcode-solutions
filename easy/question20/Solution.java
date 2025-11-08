package question20;

import java.util.LinkedList;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 *
 */
class Solution {
    public boolean isValid(String s) {
        char[] str = s.toCharArray();
        LinkedList<Character> stack = new LinkedList<>();
        if(str.length <= 1) return false;

        for(int i = 0; i < str.length; i++){
            if(str[i] == '(' || str[i] == '{' || str[i] == '['){
                stack.push(str[i]);
            }else{
                if(stack.isEmpty()) return false;
                char left = stack.pop();
                if(str[i] == ')' && left != '(') return false;
                if(str[i] == ']' && left != '[') return false;
                if(str[i] == '}' && left != '{') return false;
            }
        }
        if(!stack.isEmpty()) return false;
        return true;
    }
}
