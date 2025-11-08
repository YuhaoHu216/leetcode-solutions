package question08;

import java.util.HashSet;
import java.util.Set;

/**
 * 3.无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * tags:哈希表,滑动窗口
 *
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0;
        int right = 0;
        int maxLength = 0;
        char[] chars = s.toCharArray();
        for(char c: chars){

            while (set.contains(c)) {
                set.remove(chars[left]);
                left++;
            }
            set.add(c);
            maxLength = Math.max(maxLength, right - left +1);
            right++;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("pwwkew"));
    }
}

