package question114;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * 114. 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * <p>
 * ACM 模式：
 * 输入：二叉树的层序遍历序列，null 表示空节点，例如：1 2 5 3 4 null 6
 * 输出：展开后的链表节点值（先序遍历顺序），例如：[1, 2, 3, 4, 5, 6]
 */
public class Solution {
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preOrder(root, list);
        TreeNode cur = root;
        for (int i = 1; i < list.size(); i++) {
            cur.right = list.get(i);
            cur.left = null;
            cur = cur.right;
        }
    }

    void preOrder(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preOrder(root.left, list);
            preOrder(root.right, list);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 输入二叉树的层序遍历序列，null 表示空节点，例如：1 2 5 3 4 null 6
        String line = scanner.nextLine();
        scanner.close();

        TreeNode root = buildTree(line);
        new Solution().flatten(root);

        // 展开后沿 right 指针遍历，即为先序遍历序列
        List<Integer> result = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            result.add(cur.val);
            cur = cur.right;
        }
        System.out.println(result);
    }

    // 根据层序遍历字符串构建二叉树
    private static TreeNode buildTree(String line) {
        line = line.trim();
        // 兼容 LeetCode 带方括号的输入格式：[1,2,5,3,4,null,6]
        if (line.startsWith("[")) {
            line = line.substring(1);
        }
        if (line.endsWith("]")) {
            line = line.substring(0, line.length() - 1);
        }
        String[] nodes = line.split("[,\\s]+");
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
