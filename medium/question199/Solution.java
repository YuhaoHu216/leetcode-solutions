package question199;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * 199. 二叉树的右视图
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 */
public class Solution {
    // 层序遍历 将每层的最后一个节点加入结果集合
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        if(root != null){
            queue.offer(root);
        }
        while(!queue.isEmpty()){
            LinkedList<Integer> level = new LinkedList<>();
            int n = queue.size();
            for(int i = 0; i < n; i++){
                TreeNode poll = queue.poll();
                level.push(poll.val);
                if(poll.left != null){
                    queue.offer(poll.left);
                }
                if(poll.right != null){
                    queue.offer(poll.right);
                }
            }
            result.add(level.pop());

        }
        return result;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 输入二叉树的层序遍历序列，null 表示空节点，例如: 1 2 3 null 5 null 4
        String line = scanner.nextLine();
        scanner.close();

        TreeNode root = buildTree(line);
        List<Integer> result = new Solution().rightSideView(root);
        System.out.println(result);
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
