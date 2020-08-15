package com.xcr.leetcode;

import java.util.Arrays;

/**
 * 描述：
 *  给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * 说明:
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 *
 * 思路： 已知条件有：升序有序数组 、 index1 必须小于 index2
 *      初始化左指针left指向数组起始，初始化右指针right指向数组结尾。
 *      遍历数组，
 *      如果 arr[left] + arr[right] < target, left++;
 *      如果arr[left] + arr[right] > target, right--;
 *      如果arr[left] + arr[right] = target, 返回 left+1, right+1;
 *
 * @author 12037
 * @ClassName TwoSumII
 * @Date 2019/11/11 11:31
 * @Version 1.0
 */
public class TwoSumII {

    private int[] TwoSumII(int[] arr, int target) {
        int[] result = new int[]{};
        int left = 0;
        int right = arr.length-1;
        while (left < right) {
            if (arr[left] + arr[right] > target) {
                right--;
            } else if (arr[left] + arr[right] < target) {
                left++;
            } else if (arr[left] + arr[right] == target) {
                result = new int[]{left+1, right +1};
                break;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{2, 6, 11, 15};
        TwoSumII sumII = new TwoSumII();
        int[] result = sumII.TwoSumII(arr, 17);
        System.out.println(Arrays.toString(result));
    }
}
