package question60;

/**
 * 79. 单词搜索
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 */
class Solution {
    public boolean exist(char[][] board, String word) {

        // 将字符串转化为数组方便取字母
        char[] wordArray = word.toCharArray();

        // 遍历表中每个字母
        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[0].length; c++){
                int cur = 0;
                if (dfs(board,wordArray,r,c,cur)) return true;
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
        boolean result = dfs(board,word,r+1,c,cur+1) || dfs(board,word,r-1,c,cur+1) ||
                dfs(board,word,r,c+1,cur+1) || dfs(board,word,r,c-1,cur+1);

        // 恢复被消去的字母
        board[r][c] = word[cur];
        return result;
    }
}