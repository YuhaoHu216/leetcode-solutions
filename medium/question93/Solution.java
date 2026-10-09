package question93;

import java.util.ArrayList;
import java.util.List;
/**
 * 93.复原IP地址
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 * 示例 1：
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 * time O(3⁴ · n) 每层最多3个分支，深度4 最多81条路径 每条答案 substring是O(n) 因为剪枝 搜索树远小于81个叶子
 * space O(1)
 */
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        backtrack(s, 0, 0, new StringBuilder(), res);
        return res;
    }

    private void backtrack(String s, int start, int segment, StringBuilder path, List<String> res) {
        int remain = s.length() - start;    // 还剩多少位没有切
        int left = 4 - segment;             // 还需要切多少段

        // 剪枝：段数与字符数互相约束。当不够切或者切不够的情况剪枝
        if (remain < left || remain > left * 3) {
            return;
        }
        if (segment == 4) {
            res.add(path.substring(0, path.length() - 1));   // 去掉末尾的 '.'
            return;
        }

        // 以 0 开头的段只有单个 "0" 合法，直接单独处理
        if (s.charAt(start) == '0') {
            int mark = path.length();
            path.append('0').append('.');
            backtrack(s, start + 1, segment + 1, path, res);
            path.setLength(mark);
            return;
        }

        int num = 0;
        for (int i = start; i < s.length() && i < start + 3; i++) {
            num = num * 10 + (s.charAt(i) - '0');
            if (num > 255) break;                            // 再取长一位只会更大

            int mark = path.length();
            path.append(num).append('.');
            backtrack(s, i + 1, segment + 1, path, res);
            path.setLength(mark);                            // 撤销这一段
        }
    }
}
