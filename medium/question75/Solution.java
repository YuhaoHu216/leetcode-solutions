package question75;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *  347. 前 K 个高频元素
 *  给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 *  示例 1:
 *  输入: nums = [1,1,1,2,2,3], k = 2
 *  输出: [1,2]
 *  示例 2:
 *  输入: nums = [1], k = 1
 *  输出: [1]
 */
class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        // 将每个数字及其出现次数记录在map里
        Map<Integer,Integer> map = new HashMap<>();

        for(int num : nums){
            map.put(num,map.getOrDefault(num, 0) + 1);
        }

        // 创建优先队列(小顶堆),按元素value排列
        PriorityQueue<Integer> heap = new PriorityQueue<>(
                (a,b) -> map.get(a) - map.get(b)
        );

        // 将所有元素都入堆,但是堆中最多只保留K个元素
        for(int num : map.keySet()){
            heap.offer(num);
            if(heap.size() > k){
                heap.poll();
            }
        }

        // 创建返回值
        int[] result = new int[k];

        for(int i = k - 1; i >= 0 ; i--){
            result[i] = heap.poll();
        }

        return result;
    }
}