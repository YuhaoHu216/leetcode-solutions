package question543;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

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
            int left = maxDepth(node.left);  // 左子树最大深度
            int right = maxDepth(node.right); // 右子树最大深度
            result = Math.max(result,left + right); // 更新答案
            return Math.max(left,right) + 1; // 返回该节点能提供的最大深度
        }
    }


    public int diameterOfBinaryTree2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Map<TreeNode, Integer> depth = new HashMap<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<Boolean> visited = new ArrayDeque<>();

        stack.push(root);
        visited.push(false);

        int ans = 0;

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            boolean isVisited = visited.pop();

            if (node == null) {
                continue;
            }

            if (!isVisited) {
                // 第一次遇到：先处理左右子树
                stack.push(node);
                visited.push(true);

                if (node.right != null) {
                    stack.push(node.right);
                    visited.push(false);
                }

                if (node.left != null) {
                    stack.push(node.left);
                    visited.push(false);
                }
            } else {
                // 第二次遇到：左右子树已经处理完
                int left = depth.getOrDefault(node.left, 0);
                int right = depth.getOrDefault(node.right, 0);

                // 当前节点作为最低公共节点时，经过它的最长路径
                ans = Math.max(ans, left + right);

                // 当前节点向上提供的最大深度
                depth.put(node, Math.max(left, right) + 1);
            }
        }

        return ans;
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


