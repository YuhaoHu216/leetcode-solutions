package question48;

import java.util.HashMap;

/**
 * 437.路径总和III
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * 示例 1：
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * 示例 2：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


/**
 当遍历到当前节点的路径和为 currSum 时：
 我们想要找的路径和等于 targetSum；
 那么只要看看 currSum - targetSum 有没有出现在之前的前缀中；
 如果有，就说明存在一条从中间某个节点到当前节点的路径满足条件。
 */
class Solution {
    private int count = 0;
    private HashMap<Long,Integer> prefixSumMap = new HashMap<>();
    public int pathSum(TreeNode root, int targetSum) {
        // 根节点开始当前路径和为0
        prefixSumMap.put(0L,1);
        dfs(root,0L,targetSum);
        return count;
    }

    private void dfs(TreeNode node,Long currSum,int targetSum){
        // 终止条件
        if(node == null) return ;
        // 更新当前和
        currSum += node.val;
        // 看看是否存在某个前缀和，使得 currSum - prefix = targetSum
        count += prefixSumMap.getOrDefault(currSum - targetSum,0);
        // 记录当前前缀和出现的次数
        prefixSumMap.put(currSum,prefixSumMap.getOrDefault(currSum,0) + 1);

        dfs(node.left,currSum,targetSum);
        dfs(node.right,currSum,targetSum);

        // 回溯
        prefixSumMap.put(currSum,prefixSumMap.get(currSum)-1);
    }
}