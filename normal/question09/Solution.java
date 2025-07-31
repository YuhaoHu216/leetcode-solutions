package question09;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 */
public class Solution {
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int[] countS = new int[26];
        int[] countP = new int[26];
        for(char c :p.toCharArray()){
            countP[c - 'a']++;
        }
        for(int right = 0;right < s.length();right++){
            countS[s.charAt(right) - 'a']++;
            int left = right - p.length() + 1;
            if(left < 0) continue;
            if(Arrays.equals(countP,countS)){
                result.add(left);
            }
            countS[s.charAt(left) - 'a']--;
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(findAnagrams(s,p));
    }
}


