package question56;

import java.util.ArrayList;
import java.util.List;

/**
 * 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 */
class Solution {
    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<Integer>());    // 添加空集

        for(int i = 0; i < nums.length; i++){
            int size = result.size();  // 当前子集数
            // 将原有的子集加上一个当前数再添加到结果中
            for(int j = 0; j < size; j++){
                List<Integer> copyList = new ArrayList<>(result.get(j));
                copyList.add(nums[i]);
                result.add(copyList);
            }
        }

        return result;
    }
}

class Solution2 {
    private List<List<Integer>> res;
    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        backTracking(nums, 0, new ArrayList<>());
        return res;
    }

    public void backTracking(int[] nums,int index,List<Integer> list) {
        //任何一个能进入递归的都一定是一个正确的集合，因为源数组每个元素都不相同，并且1只能和2或3组成集合,2只能和3组成集合,3只能和自己，所以进入的一定是唯一的
        res.add(new ArrayList<>(list));
        //按层遍历
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            backTracking(nums,i+1,list);//递归
            list.remove(list.size()-1);//回溯
        }
    }
}