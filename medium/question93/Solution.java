package question93;

/**
    最长回文字串
 给你一个字符串 s，找到 s 中最长的 回文 子串。
 示例 1：
 输入：s = "babad"
 输出："bab"
 解释："aba" 同样是符合题意的答案。
 示例 2：
 输入：s = "cbbd"
 输出："bb" */
// 中心扩展法
class Solution {
    public String longestPalindrome(String s) {

        int start = 0;
        int maxLen = 0;

        for(int i = 0; i < s.length(); i++){
            int len1 = expandFromCenter(s,i,i);     // 回文串长度为奇数
            int len2 = expandFromCenter(s,i,i+1);   // 回文串长度为偶数
            int len = Math.max(len1,len2);

            if(len > maxLen){
                maxLen = len;
                start = i - (maxLen-1) / 2;
            }
        }

        return s.substring(start,start+maxLen);
    }

    int expandFromCenter(String s,int left,int right){
        while(left >=0 && right <s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return right-left-1;
    }
}