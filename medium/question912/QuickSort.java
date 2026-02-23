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
    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = arr[left];
        int i = left;
        int j = right;
        while (i < j) {
            // 先从右往左查找比pivot小的数
            while (i < j && pivot <= arr[j]) {
                j--;
            }
            // 再从左往右查找比pivot大的数
            while (i < j && pivot >= arr[i]) {
                i++;
            }
            if (i < j) {
                swap(arr, i, j);
            }
        }

        swap(arr, left, i);

        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);

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



