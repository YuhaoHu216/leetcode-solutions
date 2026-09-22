package question03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 3.无重复字符的最长子串
 *
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * tags:哈希表,滑动窗口
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


//滑动窗口 + HashMap 记录每个字符最后出现的位置，右指针不断扩张，遇到重复字符时让左指针跳到「该字符上次出现位置 + 1」（并用 Math.max 防止左指针回退），窗口长度取最大值。
//
//  补充两个关键点：
//  - map 存的是「字符 → 上一次出现的下标」，所以查重和定位一步到位，O(n) 时间。
//  - Math.max(left, map.get(c) + 1) 是必需的回退保护：像 abba 这种，处理第二个 b 时左指针到 2，之后遇到末尾 a 时若直接赋值为 map.get('a')+1 = 1
//  就会倒退，导致把已排除的字符重新算进窗口。
//  时间复杂度：O(n)
//  空间复杂度：O(1) 因为字符不会重复
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
                // 因为其他重复字符导致被跳过
                // 如果这个字母的上一次出现的位置左指针已经经过了(因为其他重复字母 例如 abba),左指针就不变,要防止指针回退
                left = Math.max(left, map.get(c) + 1);
            }
            map.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}

