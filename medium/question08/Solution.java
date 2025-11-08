package question08;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

class Solution2 {
    // HashMap O(n)解法
    public int lengthOfLongestSubstring(String s) {
        // 用一个map来存储每个字符及其上一次出现的位置
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            // 如果一个字符已经出现过就要考虑是否要把左指针移动到已经出现过的字符的右边
            if (map.containsKey(c)) {
                // 如果这个字母的上一次出现的位置左指针已经经过了,左指针就不变,要防止指针回退
                left = Math.max(left, map.get(c) + 1);
            }
            map.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}

