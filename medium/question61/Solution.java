package question61;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. 分割回文串
 * 给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * 示例 1：
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 2：
 * 输入：s = "a"
 * 输出：[["a"]]
 */
class Solution {
    public List<List<String>> partition(String s) {

        List<List<String>> result = new ArrayList<>();
        int cur = 0;
        List<String> temp = new ArrayList<>();
        backTracking(result,temp,cur,s);
        return result;
    }

    // 回溯
    void backTracking(List<List<String>> result,List<String> temp, int cur, String s){

        // s分割完毕
        if(cur == s.length()){
            result.add(new ArrayList<>(temp));
            return;
        }

        // 枚举字串结束的位置
        for(int i = cur; i < s.length(); i++){
            if(isPalindrome(s,cur,i)){
                temp.add(s.substring(cur,i+1));     // 选择将s[i~j]作为一个字串
                backTracking(result,temp,i+1,s);    // 递归:继续分割s[j+1~n-1]
                temp.remove(temp.size() -1 );
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
}