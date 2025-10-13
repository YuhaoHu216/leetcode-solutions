package question40;

/**
 * 543. 二叉树的直径
 * 给你一棵二叉树的根节点，返回该树的 直径 。
 * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
 * 两节点之间路径的 长度 由它们之间边数表示。
 */
class Solution {
    int result = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        /**
         这个最长路径可能：
         经过根节点（此时直径 = 左子树深度 + 右子树深度）
         完全在左子树中
         完全在右子树中
         */
        maxDepth(root);
        return result;
    }


    int maxDepth(TreeNode node){
        if(node == null){
            return 0;
        }else{
            int left = maxDepth(node.left);
            int right = maxDepth(node.right);
            result = Math.max(result,left + right);
            return Math.max(left,right) + 1;
        }
    }
}



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


