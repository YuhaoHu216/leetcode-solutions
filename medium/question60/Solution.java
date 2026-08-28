package question60;

import java.util.Scanner;

/**
 * 79. 单词搜索
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * ACM 模式：
 * 输入：第一行两个整数 m 和 n，表示网格的行数和列数。接下来 m 行，每行一个长度为 n 的字符串，表示网格。最后一行一个字符串 word。例如：
 * 3 4
 * ABCE
 * SFCS
 * ADEE
 * ABCCED
 * 输出：true / false，例如：true
 */
class Solution {
    public boolean exist(char[][] board, String word) {

        // 将字符串转化为数组方便取字母
        char[] wordArray = word.toCharArray();

        // 遍历表中每个字母
        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[0].length; c++){
                int cur = 0;    // 表示当前正在匹配 word[cur]
                if (dfs(board,wordArray,r,c,cur)) return true;  // 这里必须返回，否则后面的值会覆盖
            }
        }

        return false;
    }

    // 深度优先遍历
    boolean dfs(char[][] board,char[] word,int r,int c,int cur){
        // 终止条件(边界和当前表中字母和数组中的字母相等)
        if(r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word[cur]) return false;
        // 如果字母数组遍历完了就找到了返回true
        if(cur == word.length-1) return true;

        // 把已经匹配的字母消掉防止重复使用
        board[r][c] = '\0';
        boolean result = dfs(board,word,r+1,c,cur+1) ||
                dfs(board,word,r-1,c,cur+1) ||
                dfs(board,word,r,c+1,cur+1) ||
                dfs(board,word,r,c-1,cur+1);

        // 恢复被消去的字母 为什么可以这么恢复是因为前面 board[r][c] != word[cur] 已经return了
        board[r][c] = word[cur];
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 第一行：两个整数 m 和 n，表示网格的行数和列数
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        scanner.nextLine();     // 吃掉第一行末尾的换行符

        char[][] board = new char[m][n];
        for (int i = 0; i < m; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        // 最后一行：单词 word
        String word = scanner.nextLine();
        scanner.close();

        boolean result = new Solution().exist(board, word);
        System.out.println(result);
    }
}