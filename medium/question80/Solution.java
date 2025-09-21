package question80;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 划分字母区间
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 * 返回一个表示每个字符串片段的长度的列表。
 * 示例 1：
 * 输入：s = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。
 */
class Solution {
    public List<Integer> partitionLabels(String s) {

        List<Integer> result = new ArrayList<>();
        Map<Character,Integer> map = new HashMap<>();

        // 用map来存储字母最后出现的位置
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            map.put(c,i);
        }

        int start = 0;
        int idx = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            // 动态维护当前分区必须到达的最远边界，确保分区内的字符不会出现在后续的分区里。
            idx = Math.max(map.get(c),idx);
            if(i == idx){
                result.add(idx - start + 1);
                start = idx + 1;
            }
        }
        return result;
    }
}