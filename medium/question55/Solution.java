package question55;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 */
class Solution {
    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();     // 当前正在构造的一种全排列
        boolean[] visited = new boolean[nums.length];   // 用来标记该数字在全排列中是否用过

        // 深度优先搜索和回溯
        dfs(result,cur,visited,nums);

        return result;
    }

    void dfs(List<List<Integer>> result,List<Integer> cur,boolean[] visited,int[] nums){

        // 终止条件
        if(cur.size() == nums.length){
            // 注意这里是new一个新的集合,不然的话后续对cur的修改都是在result里的cur的修改
            result.add(new ArrayList<>(cur));
            return;
        }

        // 以每个数字为头进行全排列
        for(int i = 0; i < nums.length; i++){
            // 如果当前数字未被用过
            if(!visited[i]){
                visited[i] = true;
                cur.add(nums[i]);
                dfs(result,cur,visited,nums);
                cur.remove(cur.size() - 1);
                visited[i] = false;
            }
        }
    }
}