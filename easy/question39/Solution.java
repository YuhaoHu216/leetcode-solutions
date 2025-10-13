package question39;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 101. 对称二叉树
 */

public class Solution {
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode rootLeft = root.left;
        TreeNode rootRight = root.right;
        queue.offer(root);
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode leftNode = queue.poll();
            TreeNode rightNode = queue.poll();
            if(leftNode == null && rightNode == null){
                continue;
            }
            if(leftNode == null || rightNode == null || rightNode.val != leftNode.val){
                return false;
            }

            queue.offer(leftNode.left);
            queue.offer(rightNode.right);

            queue.offer(rightNode.right);
            queue.offer(leftNode.left);
        }
        return true;
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
