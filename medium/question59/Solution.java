package question59;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 */
class Solution {
    public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();
        char[] temp =new char[n * 2];               // 用来存放构造中的组合,char[]效率大于String
        int left = 0;                               // 一个组合中已经填入的左括号数
        int right = 0;                              // 一个组合中已经填入的右括号数

        dfs(result,temp,left,right,n);

        return result;
    }

    void dfs(List<String> result,char[] temp,int left,int right,int n){

        // 左(右)括号在一个组合中已经填完
        if(right == n){
            result.add(new String(temp));
            return;
        }

        // 还能填入左括号
        if(left < n){
            temp[left + right] = '(';
            dfs(result,temp,left+1,right,n);
        }

        // 还能填入右括号
        if(right < left){
            temp[left + right] = ')';
            dfs(result,temp,left,right+1,n);
        }
    }
}