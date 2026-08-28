package question39;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 39. 组合总和
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 * 示例 2：
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * 示例 3：
 *
 * ACM 模式：
 * 输入：第一行为候选数组长度 n 和目标值 target（空格分隔）；第二行为 n 个整数，表示候选数组。例如：
 * 4 7
 * 2 3 6 7
 * 输出：所有和为 target 的组合，例如：[[2, 2, 3], [7]]
 */
class Solution {
    // 用减法 看能不能把目标数减到零
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cur = new ArrayList<>(); // 存放当前尝试组合(路径)
        int index = 0;      // 当前递归层从哪个下标开始选数，避免重复组合。

        backTracking(result, cur, index, candidates, target);

        return result;
    }

    void backTracking(List<List<Integer>> result, List<Integer> cur, int index, int[] candidates, int target) {
        if (target == 0) {
            result.add(new ArrayList<>(cur));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] <= target) {              // 剪枝操作
                cur.add(candidates[i]);
                backTracking(result, cur, i, candidates, target - candidates[i]);
                cur.remove(cur.size() - 1);
            }
        }
        return;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 第一行：候选数组长度 n 和目标值 target
        int n = scanner.nextInt();
        int target = scanner.nextInt();
        // 第二行：n 个整数，表示候选数组
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) {
            candidates[i] = scanner.nextInt();
        }
        scanner.close();

        List<List<Integer>> result = new Solution().combinationSum(candidates, target);
        System.out.println(result);
    }
}