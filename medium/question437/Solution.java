package question437;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 437.路径总和III
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * 示例 1：
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * 示例 2：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 *
 * ACM 模式：
 * 输入：两行。第一行为二叉树的层序遍历序列（null 表示空节点），第二行为 targetSum，例如：
 * 10 5 -3 3 2 null 11 3 -2 null 1
 * 8
 * 输出：满足条件的路径数量，例如：3
 */

/**
 当遍历到当前节点的路径和为 currSum 时：
 我们想要找的路径和等于 targetSum；
 那么只要看看 currSum - targetSum 有没有出现在之前的前缀中；
 如果有，就说明存在一条从中间某个节点到当前节点的路径满足条件。
 */
public class Solution {
    // 满足 targetSum 的路径数量
    private int result = 0;
    // map用来存储从根节点到当前节点路径上的前缀和出现的次数
    private HashMap<Long,Integer> prefixSumMap = new HashMap<>();
    public int pathSum(TreeNode root, int targetSum) {
        // 初始化 根节点开始当前路径和为0
        prefixSumMap.put(0L,1);
        dfs(root,0L,targetSum);
        return result;
    }

    private void dfs(TreeNode node,Long currSum,int targetSum){
        // 终止条件
        if(node == null) return ;
        // 更新当前和 拿到当前前缀和的看有法去更新结果和map没
        currSum += node.val;
        // 看看是否存在某个前缀和，使得 currSum - prefix = targetSum
        result += prefixSumMap.getOrDefault(currSum - targetSum,0);
        // 记录当前前缀和出现的次数
        prefixSumMap.put(currSum,prefixSumMap.getOrDefault(currSum,0) + 1);

        dfs(node.left,currSum,targetSum);
        dfs(node.right,currSum,targetSum);

        // 回溯
        prefixSumMap.put(currSum,prefixSumMap.get(currSum)-1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 第一行：二叉树的层序遍历序列，null 表示空节点，例如: 10 5 -3 3 2 null 11 3 -2 null 1
        String line = scanner.nextLine();
        // 第二行：targetSum
        int targetSum = Integer.parseInt(scanner.nextLine().trim());
        scanner.close();

        TreeNode root = buildTree(line);
        int result = new Solution().pathSum(root, targetSum);
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