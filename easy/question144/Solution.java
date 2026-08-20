package question144;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 144.二叉树的前序遍历
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 */
// 递归法
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preOrder(root,result);
        return result;
    }

    private void preOrder(TreeNode root, List<Integer> result){
        if(root == null) return;

        result.add(root.val);
        preOrder(root.left,result);
        preOrder(root.right,result);
    }
}

// 迭代法
// 类似于层序，只是把队列换为了栈 注意入栈节点必须是先右后左
class Solution2 {
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null) return new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode pop = stack.pop();
            result.add(pop.val);
            // 这里必须先右再左 因为 根 → 左 → 右 要让左节点先出栈就必须后入栈
            if(pop.right != null) stack.push(pop.right);
            if(pop.left != null) stack.push(pop.left);
        }

        return result;
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