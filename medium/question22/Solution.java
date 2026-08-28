package question22;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 *
 * ACM 模式：
 * 输入：一行一个整数 n。例如：
 * 3
 * 输出：所有有效的括号组合，例如：[((())), (()()), (())(), ()(()), ()()()]
 */
class Solution {
    // 注意字符数组转字符串语法
    public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();
        char[] cur =new char[n * 2];               // 用来存放构造中的组合,char[]效率大于String
        int left = 0;                               // 一个组合中已经填入的左括号数
        int right = 0;                              // 一个组合中已经填入的右括号数

        dfs(result,cur,left,right,n);

        return result;
    }

    void dfs(List<String> result,char[] cur,int left,int right,int n){

        // 左(右)括号在一个组合中已经填完 一定要是 right == n
        if(right == n){
            result.add(new String(cur));
            return;
        }

        // 右括号不能单独存在 必须先有左括号才是右括号
        // 还能填入左括号
        if(left < n){
            // 因为当前组合已经有括号了，left+right表示下一个放括号的index
            cur[left + right] = '(';
            dfs(result,cur,left+1,right,n);
        }

        // 还能填入右括号
        if(right < left){
            cur[left + right] = ')';
            dfs(result,cur,left,right+1,n);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 一行一个整数 n
        int n = scanner.nextInt();
        scanner.close();

        List<String> result = new Solution().generateParenthesis(n);
        System.out.println(result);
    }
}