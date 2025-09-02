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
