package com.xcr.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 示例 1:
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * 示例 2:
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * 说明：
 *  输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 *  我们可以不考虑输出结果的顺序。
 *
 *  思路：
 *      1. 使用map遍历nums1 ，记录数值和出现的频率
 *      2. 遍历nums2,查看是否有与记录的数值相等的数字，如果有，加入到resultMap，频率减一
 *
 *
 * @author 12037
 * @ClassName IntersectionOfTwoArraysII
 * @Date 2019/11/14 15:22
 * @Version 1.0
 */
public class IntersectionOfTwoArraysII {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,2,3,1};
        int[] nums2 = new int[]{2, 2, 3};
        Map<Integer, Integer> resultMap = new HashMap<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i:nums1) {
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                map.put(i, map.get(i) + 1);
            }
        }
        for (int j:nums2) {
            if (map.containsKey(j)) {
                if (!resultMap.containsKey(j)) {
                    resultMap.put(j, 1);
                } else {
                    resultMap.put(j, resultMap.get(j) + 1);
                }
                map.put(j, map.get(j) - 1);
            }
        }
        System.out.println(resultMap.keySet());
    }

}
