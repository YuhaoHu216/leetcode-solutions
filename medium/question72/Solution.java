package question72;

/**
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 */
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // dp[i][j] 表示 word1 的前 i 个字符 转换成 word2 的前 j 个字符 所需要的最少操作数。
        // dp[0][j]：word1 为空，变成 word2 的前 j 个字符，需要 j 次 插入。
        // dp[i][0]：word2 为空，word1 的前 i 个字符，需要 i 次 删除。
        int[][] dp = new int[m+1][n+1];

        for(int i = 0; i <= m; i++){
            dp[i][0] = i;
        }

        for(int i = 0; i <= n; i++){
            dp[0][i] = i;
        }

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <=n; j++){
                // 如果 word1[i-1] == word2[j-1]，说明这两个字符相等，不需要操作：
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    // 如果 word1[i-1] != word2[j-1]，则需要进行一次操作（取最小值）
                    // 执行 删除 插入 替换 中步数最小的操作
                    dp[i][j] = Math.min(dp[i-1][j]+1,Math.min(dp[i][j-1]+1,dp[i-1][j-1]+1));
                }
            }
        }

        return dp[m][n];
    }
}