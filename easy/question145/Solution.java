package question145;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 145.二叉树的后序遍历
 * 给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
 */

// 递归法
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postOrder(root,result);
        return result;
    }

    private void postOrder(TreeNode root, List<Integer> list){
        if(root == null) return;
        postOrder(root.left,list);
        postOrder(root.right,list);
        list.add(root.val);
    }
}

// 迭代法
//一路向左压栈
//↓
//压完后取出节点
//↓
//如果没有右子树，或者右子树已经访问过
//    → 访问当前节点
// 否则
//    → 当前节点重新入栈
//    → 转向右子树
class Solution2 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pre = null;

        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }

            TreeNode peek = stack.peek();

            // 如果没有右子树或右子树已访问过
            if(peek.right == null || peek.right == pre){
                result.add(peek.val);
                pre = stack.pop();  // 记录刚处理完哪个节点
                root = null;        // 表示当前节点已处理完，该回溯处理父节点了
            }else{
                root = peek.right;
            }
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

