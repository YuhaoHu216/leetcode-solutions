package question105;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * 105.从前序与中序遍历序列构造二叉树
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历，
 * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * 示例 1:
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 * 示例 2:
 * 输入: preorder = [-1], inorder = [-1]
 * 输出: [-1]
 *
 * ACM 模式：
 * 输入：两行整数数组，第一行为前序遍历序列，第二行为中序遍历序列，例如：
 * [3,9,20,15,7]
 * [9,3,15,20,7]
 * 输出：构造的二叉树的层序遍历序列，例如：[3, 9, 20, null, null, 15, 7]
 */

// 前序决定“谁是根”，中序决定“左右范围”。左子树的长度帮助我们在前序数组中正确切分出左右部分

class Solution2 {
    // 全局指针+哨兵
    int in; // 当前处理到中序数组的下标
    int pre; // 当前处理到前序数组的下标
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder,inorder,Integer.MAX_VALUE);
    }

    // end 表示整棵树的结束条件
    TreeNode build(int[]preorder,int[]inorder,int end){
        // 中止条件1：前序遍历数组用完说明树构建完成
        if(pre>=preorder.length){
            return null;
        }
        // 中止条件2：当前子树的中序部分已经构造完毕到达了哨兵边界
        // 当构建左子树时，end被设为当前节点的值；当中序遍历遇到end（当前子树左部分结束就返回上层）
        if(inorder[in]==end){
            in++;   // 表示中序遍历指针前进一位，跳过这个根节点
            return null;
        }
        // 构造当前节点
        TreeNode node = new TreeNode(preorder[pre]);
        pre++;
        // 构造左子树
        node.left = build(preorder,inorder,node.val);
        // 构造右子树
        node.right = build(preorder,inorder,end);

        return node;
    }
}

public class Solution {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 第一行：前序遍历序列，第二行：中序遍历序列，例如 [3,9,20,15,7]
        String preorderLine = scanner.nextLine();
        String inorderLine = scanner.nextLine();
        scanner.close();

        int[] preorder = parseArray(preorderLine);
        int[] inorder = parseArray(inorderLine);

        TreeNode root = new Solution().buildTree(preorder, inorder);
        List<Integer> result = levelOrderWithNull(root);
        System.out.println(result);
    }

    // 解析整数数组，兼容 [1,2,3] / 1,2,3 / 1 2 3 三种分隔格式
    private static int[] parseArray(String line) {
        line = line.trim();
        if (line.startsWith("[")) {
            line = line.substring(1);
        }
        if (line.endsWith("]")) {
            line = line.substring(0, line.length() - 1);
        }
        String[] parts = line.split("[,\\s]+");
        if (parts.length == 0 || parts[0].isEmpty()) {
            return new int[0];
        }
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i].trim());
        }
        return arr;
    }

    // 层序遍历输出（含 null 空节点，末尾多余的 null 不输出，与 LeetCode 展示格式一致）
    private static List<Integer> levelOrderWithNull(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                result.add(null);
                continue;
            }
            result.add(node.val);
            queue.offer(node.left);
            queue.offer(node.right);
        }
        // 去掉末尾的 null
        int end = result.size();
        while (end > 0 && result.get(end - 1) == null) {
            end--;
        }
        return result.subList(0, end);
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