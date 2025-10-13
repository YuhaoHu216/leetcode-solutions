package question47;

import java.util.HashMap;

/**
 * 47.从前序和中序遍历序列构造二叉树
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历，
 * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * 示例 1:
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 * 示例 2:
 * 输入: preorder = [-1], inorder = [-1]
 * 输出: [-1]
 *
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
 * 105. 从前序与中序遍历序列构造二叉树
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        // 将中序遍历的结果放入哈希表中方便获取根节点
        HashMap<Integer,Integer> inMap = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
            inMap.put(inorder[i],i);
        }

        return build(preorder,0,preorder.length-1,inorder,0,inorder.length-1,inMap);
    }

    private TreeNode build(int[] preorder,int preStart,int preEnd,
                           int[] inorder,int inStart,int inEnd,HashMap<Integer,Integer> inMap){

        // 终止条件
        if(preStart > preEnd || inStart > inEnd) return null;

        // 获取根节点及其中序遍历索引
        int rootValue = preorder[preStart];
        int rootIndex = inMap.get(rootValue);
        TreeNode root = new TreeNode(rootValue);

        // 根据索引求出左子树节点数
        int leftSize = rootIndex - inStart;


        // 中序遍历特点:根节点左边为左子树节点,右边为右子树节点
        // 前序遍历特点:根节点后面紧跟左子树节点,然后是右子树节点

        // 构造左子树,主要参数有左子树节点在前,中序遍历的索引范围
        root.left = build(preorder,preStart+1,preStart+leftSize,
                inorder,inStart,rootIndex-1,inMap);

        // 构造右子树,主要参数有右子树节点在前,中序遍历的索引范围
        root.right = build(preorder,preStart+leftSize+1,preEnd,
                inorder,rootIndex+1,inEnd,inMap);

        return root;

    }
}