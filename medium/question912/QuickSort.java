package question912;

import javax.naming.PartialResultException;
import java.util.Arrays;
import java.util.Random;

/**
 * 912.排序数组 nlog(n)
 * 生成随机数快速排序
 */
public class QuickSort {

    public static void main(String[] args) {

        Random r = new Random();
        int[] arr = new int[10];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(100);
        }

        System.out.println("排序前: " + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后: " + Arrays.toString(arr));

    }

    // 快速排序
    // 选一个基准值（pivot），用双指针把数组分成「比它小」和「比它大」两部分，再把基准放到分界点上，然后对左右两段递归重复这个过程。
    // time 平均 (nlogn) 最坏 O(n^2)
    // space 平均 (logn) 最坏 O(n)
    private  static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        // 随机选择 pivot
        Random r = new Random();
        int random = r.nextInt(right - left + 1) + left;
        int pivot = arr[random];

        // 三路快排
        int lt = left;      // < pivot 区域的右边界
        int i = left;       // 当前遍历位置
        int gt = right;     // > pivot 区域的左边界

        while (i <= gt) {
            if (arr[i] < pivot) {
                swap(arr, lt, i);
                lt++;
                i++;
            } else if (arr[i] > pivot) {
                swap(arr, i, gt);
                gt--;
            } else {
                // arr[i] == pivot
                i++;
            }
        }

        // 此时：
        // [left, lt - 1] < pivot
        // [lt, gt]       == pivot
        // [gt + 1, right] > pivot

        quickSort(arr, left, lt - 1);
        quickSort(arr, gt + 1, right);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 归并排序
    private int[] mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return new int[]{nums[left]};
        }
        int mid = (left + right) / 2;
        int[] leftArr = mergeSort(nums, left, mid);
        int[] rightArr = mergeSort(nums, mid + 1, right);
        return merge(leftArr, rightArr);
    }

    private int[] merge(int[] leftArr, int[] rightArr) {
        int[] res = new int[leftArr.length + rightArr.length];
        int i = 0, j = 0, k = 0;
        while (i < leftArr.length && j < rightArr.length) {
            res[k++] = leftArr[i] < rightArr[j] ? leftArr[i++] : rightArr[j++];
        }
        while (i < leftArr.length) {
            res[k++] = leftArr[i++];
        }
        while (j < rightArr.length) {
            res[k++] = rightArr[j++];
        }
        return res;
    }
}



