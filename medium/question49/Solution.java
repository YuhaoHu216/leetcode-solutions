package question49;

import java.util.*;

/**
 * 49.字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 示例 1:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 解释：
 * 在 strs 中没有字符串可以通过重新排列来形成 "bat"。
 * 字符串 "nat" 和 "tan" 是字母异位词，因为它们可以重新排列以形成彼此。
 * 字符串 "ate" ，"eat" 和 "tea" 是字母异位词，因为它们可以重新排列以形成彼此。
 * tags:哈希表
 */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 注意字符串和字符数组的转化 ，hashmap取值变集合的语法；核心思路在于排序后的异位词结果相同
        Map<String,List<String>> map = new HashMap();

        for(String str : strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);
            // ******************************
            // if(!map.containsKey(sortedStr)){
            //     map.put(sortedStr,new ArrayList<>());
            // }
            // ******************************

            // ******************************
            List list = map.get(sortedStr);
            if(list == null){
                map.put(sortedStr,new ArrayList<>());
            }
            // ******************************
            map.get(sortedStr).add(str);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args){
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(new Solution().groupAnagrams(strs));
    }
}



