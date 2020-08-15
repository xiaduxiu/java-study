package com.xcr.leetcode;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。
 * 如果不存在符合条件的连续子数组，返回 0。
 * 示例:
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组
 *
 * 思路：连续子数组，可以使用滑动窗体.定义两个指针left和right ,分别表示子数组的左右两端。
 *      （1）right指针向右移动，和 sum ≥ s或者right达到数组末尾
 *      （2）更新最短距离，将left像右移一位,sum减去移去的值
 *       (3) 重复 1、 2 步骤，直到right到达末尾
 *
 *
 * @author 12037
 * @ClassName MinimumSizeSubarraySum
 * @Date 2019/11/13 11:05
 * @Version 1.0
 */
public class MinimumSizeSubarraySum {

    public int minimumSizeSubarraySum(int[] arr, int target) {
        int sum = 0, left = 0, right =0, length = arr.length +1;
        while (left < arr.length) {
            if (right < arr.length && sum < target) {
                sum += arr[right++];
            } else {
                // right到头或者 sum>=s
                sum -= arr[left++];
            }
            if (sum >= target) {
                length = Math.min(length, right-left);
            }
        }
        if (length == arr.length +1) {
            return 0;
        }
        return length;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{2,3,1,2,4,3};
        MinimumSizeSubarraySum subarraySum = new MinimumSizeSubarraySum();
        int result = subarraySum.minimumSizeSubarraySum(arr, 7);
        System.out.println(result);
    }

}
