package question44;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


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
 * 230. 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 */
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> valueList = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();

        while(root != null || !stack.isEmpty()){
            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                TreeNode pop = stack.pop();
                valueList.add(pop.val);
                root = pop.right;
            }
        }

        return valueList.get(k-1);
    }
}
