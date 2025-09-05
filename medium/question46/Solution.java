package question46;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
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

class Solution {
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preOrder(root,list);
        TreeNode cur = root;
        for(int i = 1; i < list.size(); i++){
            cur.right = list.get(i);
            cur.left = null;
            cur = cur.right;
        }
    }

    void preOrder(TreeNode root,List<TreeNode> list){
        if(root != null){
            list.add(root);
            preOrder(root.left,list);
            preOrder(root.right,list);
        }
    }
}