package question103;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * 103. 二叉树的锯齿形层序遍历
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // 层序遍历 当结果集合里面有奇数层的时候 当前层反转
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();

        if(root == null) return new ArrayList<>();

        queue.offer(root);

        while(!queue.isEmpty()){
            List<Integer> level = new ArrayList<>();
            int size = queue.size();    // 注意这里大小要在外面获取
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                level.add(node.val);

                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            // 和层序遍历仅仅这一行的区别
            if(result.size() % 2 == 1) Collections.reverse(level);
            result.add(level);
        }
        return result;
    }
}
