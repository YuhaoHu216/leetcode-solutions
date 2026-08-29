package question131;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 131. 分割回文串
 * 给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * 示例 1：
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 2：
 * 输入：s = "a"
 * 输出：[["a"]]
 *
 * ACM 模式：标准输入每行一个字符串 s（支持多组），输出所有分割方案，格式如 [["a","a","b"],["aa","b"]]
 */
public class Solution {
    // 从 index 开始枚举下一个回文字串的结束位置；每选一个回文字串，就递归处理剩余部分
    public List<List<String>> partition(String s) {

        List<List<String>> result = new ArrayList<>();
        int index = 0;  // 当前还没有被分割处理的字符串的起始位置
        List<String> cur = new ArrayList<>();   // 表示当前正在构造的一个分割方案
        backTracking(result,cur,index,s);   // 每一层递归决定当前这一段切多长
        return result;
    }

    // 回溯
    void backTracking(List<List<String>> result,List<String> cur, int index, String s){

        // s分割完毕
        if(index == s.length()){
            result.add(new ArrayList<>(cur));
            return;
        }

        // 枚举子串结束的位置
        for(int i = index; i < s.length(); i++){
            if(isPalindrome(s,index,i)){
                cur.add(s.substring(index,i+1));     // 选择将s[i~j]作为一个字串
                backTracking(result,cur,i+1,s);    // 递归:继续分割s[j+1~n-1]
                cur.remove(cur.size() -1 );
            }
        }
    }

    // 判断一定范围内的字符串是否回文
    boolean isPalindrome(String s,int left,int right){
        while(left < right){
            if(s.charAt(left++) != s.charAt(right--)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Solution solution = new Solution();
        // ACM 模式：每行一组输入，输出所有分割方案
        while (sc.hasNext()) {
            String s = sc.next();
            List<List<String>> result = solution.partition(s);
            System.out.println(format(result));
        }
        sc.close();
    }

    // 输出格式 [["a","a","b"],["aa","b"]]
    static String format(List<List<String>> result) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < result.size(); i++) {
            if (i > 0) sb.append(",");
            sb.append("[");
            List<String> list = result.get(i);
            for (int j = 0; j < list.size(); j++) {
                if (j > 0) sb.append(",");
                sb.append("\"").append(list.get(j)).append("\"");
            }
            sb.append("]");
        }
        return sb.append("]").toString();
    }
}