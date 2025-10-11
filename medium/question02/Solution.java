package question02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs){
        HashMap<String,List<String>> hashMap = new HashMap<>();

        for(String s: strs){
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String key = new String(c);
            if(!hashMap.containsKey(key)){
                hashMap.put(key,new ArrayList<>());
            }
            hashMap.get(key).add(s);
        }

        return new ArrayList<>(hashMap.values());
    }

    public static void main(String[] args){
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(new Solution().groupAnagrams(strs));
    }
}
