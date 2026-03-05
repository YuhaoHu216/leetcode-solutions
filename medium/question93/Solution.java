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
 */
class Solution {

    List<String> result = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        backtrack(s, 0, 0, new StringBuilder());
        return result;
    }

    private void backtrack(String s, int start, int segment, StringBuilder path){
        // 剩余字符
        int remain = s.length() - start;
        // 剩余段数
        int segmentsLeft = 4 - segment;

        // 剪枝 字符太多 剩余字符不够剩余段数分
        if(remain > segmentsLeft * 3 || remain < segmentsLeft){
            return;
        }

        // 找到4段
        if(segment == 4){
            result.add(path.substring(0, path.length()-1));
            return;
        }
        // 当前段数字
        int num = 0;

        for(int i = start; i < s.length() && i < start + 3; i++){

            num = num * 10 + (s.charAt(i) - '0');

            if(num > 255) break;

            path.append(num).append('.');

            backtrack(s, i + 1, segment + 1, path);

            path.delete(path.length() - (i - start + 1) - 1, path.length());

            // 前导0处理
            if(num == 0) break;
        }
    }
}
