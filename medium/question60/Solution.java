package question60;

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