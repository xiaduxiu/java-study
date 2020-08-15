package com.xcr.leetcode;

import java.util.Arrays;

/**
 * 题目：
 *  给定一个数组arr，写一个函数，将数组中所有的0挪到数组的末尾，⽽维持其他所有非0元素的相对位置。
 *  举例: arr = [0, 1, 0, 5, 9, 0]，函数运⾏后结果为[1, 5, 9, 0, 0, 0]
 *
 *
 * 思路： 原地排序思想，维护一个K的值，表示从0到k之间的数值是非零的，
 *        循环遍历数组，如果找到一个非零的数arr[i], 则arr[i]与arr[k]交换，同时k+1
 * 分析：空间复杂度O（1） 时间复杂度O（n）
 *
 * @author 12037
 *
 */
public class MoveZeros {

    private int[] moveZeros(int[] arr) {
        int k = 0;
        if(arr.length <=0) {
            return arr;
        }
        for (int i = 0; i< arr.length;i++) {
            if (arr[i] != 0) {
                int temp = arr[k];
                arr[k] = arr[i];
                arr[i] = temp;
                k++;
            }
        }
        return arr;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 0, 5, 9, 0};
        MoveZeros moveZeros = new MoveZeros();
        int[] result = moveZeros.moveZeros(arr);
        System.out.println(Arrays.toString(result));
    }
}
