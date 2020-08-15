package com.xcr.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 示例 1:
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 * 示例 2:
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 * 说明:
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 *
 *
 * 思路：
 *     1 使用set将nums1进行保存
 *     2. 遍历nums2 进行比较，如果包含则取出
 *
 * @author 12037
 * @ClassName IntersectionofTwoArrays
 * @Date 2019/11/14 15:06
 * @Version 1.0
 */
public class IntersectionOfTwoArrays {

    public static void main(String[] args) {
        int[] nums1 = new int[]{4,9,5};
        int[] nums2 = new int[]{9,4,9,8,4};
        Set<Integer> resultSet = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        for (int i:nums1) {
            set.add(i);
        }
        for (int j:nums2) {
            if (set.contains(j)) {
                resultSet.add(j);
            }
        }
        System.out.println(Arrays.toString(resultSet.toArray()));
    }
}
