package question76;

import java.util.PriorityQueue;

/**
 * 295.数据流的中位数
 * 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
 * 例如 arr = [2,3,4] 的中位数是 3 。
 * 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
 * 实现 MedianFinder 类:
 * MedianFinder() 初始化 MedianFinder 对象。
 * void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
 * double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。
 * 示例 1：
 * 输入
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * 输出
 * [null, null, null, 1.5, null, 2.0]
 * 解释
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 */
class MedianFinder {
    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;

    public MedianFinder() {
        // 左堆为大顶堆
        left = new PriorityQueue<Integer>( (a,b) -> (b-a) );
        // 右堆默为小顶堆(默认)
        right = new PriorityQueue<Integer>();

    }

    public void addNum(int num) {
        // 先将数据加入左堆
        left.offer(num);
        // 再将左堆最大值(堆顶)加入右堆
        right.offer(left.poll());
        // 调整使左堆元素始终>=右堆元素
        if(left.size() < right.size()){
            left.offer(right.poll());
        }
    }

    public double findMedian() {
        // 如果两个堆大小相等,中位数就是两堆顶元素和的1/2
        if(left.size() == right.size()){
            return (left.peek() + right.peek()) / 2.0;   // 注意是peek方法而且除数是2.0浮点数
        }
        // 不是中位数就是左堆堆顶元素
        return left.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */