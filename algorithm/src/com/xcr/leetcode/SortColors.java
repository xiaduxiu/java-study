package com.xcr.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，
 * 并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * 示例:
 * 输入: [0,1,2,1,2,0]
 * 输出: [0,0,1,1,2,2]
 *
 * 思路：首先不能使用已有的排序函数，其次进行原地排序， 说明需要进行元素的位置交换
 * 最后的结果要有序，参考三路快排partition的思想。
 *
 * 先熟悉快排思想： 快排是基于分治算法思想，从要排序的数组中选取一个基准k，把数组划分为<k、>=k，
 *                     然后再从中选取一个基准v把数组划分，一直递归下去直到排好序。
 *  代码实现：请看 simplePartition方法
 *
 * 普通快排如果每一次划分都有一边只产生一个数，那么时间复杂度为O（n^2），
 * 如果每一次划分都是对半分，那么时间复杂度降低为O（nlogn）。
 * 由此可以看出 快排的性能取决于划分的对称性，也就是选择的基准。 所以我们想要优化的话，可以进行随机选择基准。
 *
 * 思考： 如果一个数组有很多的重复数据，那么=k的情况会导致重复数据划分到>k或者<k的那部分，那么相当于退化为时间复杂度为O（n^2）
 *   所以使用 “ 指针对撞 ” 的方式把重复数据分散到数组的两边，以减少递归的深度，就是双路快排的思想。
 *
 *  TODO 代码实现：请看 doublePartition方法
 *
 * 三路快排就是<k, =k, >k，如何分成三路：
 *  遍历数组， index表示遍历到的位置，找两个分界位置，lt 和 rt 表示 =k 和 >k 的最小位置
 *  如果遇到小于k的，与lt交换，同时lt++, 等于k,则不需要操作。大于k的将lt-- ，然后交换lt。
 *  然后递归<k 和 >k的两段。
 *
 * @author 12037
 */
public class SortColors {

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     *
     * 快排例子
     */
    private static void simplePartition(int[] arr, int left, int right) {
        if (left > right) {
            return ;
        }
        // 选取一个基准
        int key = arr[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (arr[j] >= key && i<j) {
                j--;
            }
            while (arr[i] <= key && i<j) {
                i++;
            }
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[left] = arr[i];
        arr[j] = key;
        simplePartition(arr, left, j-1);
        simplePartition(arr, j+1, right);
    }

    @Test
    public void testSimplePartition() {
        int[] arrSimple = new int[]{2,1,4,7,1,6};
        simplePartition(arrSimple, 0, arrSimple.length-1);
        System.out.println(Arrays.toString(arrSimple));
    }

    /**
     * TODO 双路快排例子
     */
    private static void doublePartition(int[] arr, int left, int right) { }
    @Test
    public void testDoublePartition() {
        int[] arrSimple = new int[]{5,3,5,5,1,7,5,1,5,5,6,0,5,3,1,0,5};
        simplePartition(arrSimple, 0, arrSimple.length-1);
        System.out.println(Arrays.toString(arrSimple));
    }

    public int[] SortColors(int[] arr) {
        int length = arr.length;
        // =k的第一个
        int lt = 0;
        // >k的第一个
        int rt=length;
        int index =0;
        int k = 1;
        while (index < length && index< rt) {
            if (arr[index] > k) {
                rt--;
                swap(arr, index, rt);
            } else if (arr[index] == k) {
                index ++;
            } else if (arr[index] <k) {
                swap(arr, lt, index);
                lt++;
                index++;
            }
        }
        return arr;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{0,1,0,2,1,0};
        SortColors sortColors  = new SortColors();
        int[] result = sortColors.SortColors(arr);
        System.out.println(Arrays.toString(result));
    }
}
