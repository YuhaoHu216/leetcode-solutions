package question236;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 236.二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q
 * 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * ACM 模式：
 * 输入：两行。第一行为二叉树的层序遍历序列（null 表示空节点），第二行为两个目标节点的值 p q，例如：
 * 3 5 1 6 2 0 8 null null 7 4
 * 5 1
 * 输出：最近公共祖先节点的值，例如：3
 *
 * time O(n) 每个节点最多被访问一次 最坏情况是全树遍历
 * space O(n) 递归栈最坏退化成链表
 */

class Solution {
    // 函数定义为在root为根的子树中，返回p和q的最近公共祖先；如果这棵子树中一个都没找到就返回null
    // 如果两边都找到了说明根节点就是祖先
    // 左边没有结果就在右边，右边没有结果就在左边
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == p || root == q || root == null) return root;
        TreeNode left =lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);

        if(left != null && right != null) return root;
        if(left == null) return right;
        return left;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 第一行：二叉树的层序遍历序列，null 表示空节点，例如: 3 5 1 6 2 0 8 null null 7 4
        String line = scanner.nextLine();
        // 第二行：两个目标节点的值 p q，例如: 5 1
        int pVal = scanner.nextInt();
        int qVal = scanner.nextInt();
        scanner.close();

        TreeNode root = buildTree(line);
        TreeNode p = findNode(root, pVal);
        TreeNode q = findNode(root, qVal);
        TreeNode result = new Solution().lowestCommonAncestor(root, p, q);
        System.out.println(result == null ? "null" : result.val);
    }

    // 根据层序遍历字符串构建二叉树
    private static TreeNode buildTree(String line) {
        String[] nodes = line.trim().split("[,\\s]+");
        if (nodes.length == 0 || nodes[0].isEmpty() || "null".equalsIgnoreCase(nodes[0])) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty() && i < nodes.length) {
            TreeNode node = queue.poll();
            if (i < nodes.length && !"null".equalsIgnoreCase(nodes[i])) {
                node.left = new TreeNode(Integer.parseInt(nodes[i]));
                queue.offer(node.left);
            }
            i++;
            if (i < nodes.length && !"null".equalsIgnoreCase(nodes[i])) {
                node.right = new TreeNode(Integer.parseInt(nodes[i]));
                queue.offer(node.right);
            }
            i++;
        }
        return root;
    }

    // 根据 val 在二叉树中查找目标节点
    private static TreeNode findNode(TreeNode node, int val) {
        if (node == null) return null;
        if (node.val == val) return node;
        TreeNode left = findNode(node.left, val);
        if (left != null) return left;
        return findNode(node.right, val);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}