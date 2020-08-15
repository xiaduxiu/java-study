package com.xcr.leetcode;


import java.util.HashSet;
import java.util.Set;

/**
 * 题目：给出⼀个整形数组nums和⼀个整数k，是否存在索引i和j，使得nums[i] == nums[j] 且i和j之间的差不超过k
 * Example 1:
 * Input: nums = [1,2,3,1], k = 3
 * Output: true.
 * Example 2:
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 * Example 3:
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 *
 * 思路：
 *      用滑动窗口和查找表来做，
 *      1. 维持一个长度为k的set, 用来保存每次遍历的数组变量。
 *      2. 遍历数组，每次从set中查询是否有相同的元素，有的话就返回true,
 *         没有的话就插入元素，然后查看是否set长度为K+1.如果是的话，则删除set中的元素,该元素的值为nums[i-k]
 *      3. 直到遍历完数组，都没有结果，那么就返回false
 *
 *
 * @author 12037
 * @ClassName ContainsDuplicateII
 * @Date 2019/11/26 14:26
 * @Version 1.0
 */
public class ContainsDuplicateII {

    public boolean containsDuplicateII(int[] nums, int k) {
        if (nums.length <= 1 || k <=0) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() == k+1) {
                set.remove(nums[i-k]);
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,1,3,4,1};
        int[] nums2 = new int[]{1,0,5,1,1};
        int[] nums3 = new int[]{1,2,3,1,2,3};
        ContainsDuplicateII duplicate = new ContainsDuplicateII();
        System.out.println(duplicate.containsDuplicateII(nums1, 3));
        System.out.println(duplicate.containsDuplicateII(nums2, 2));
        System.out.println(duplicate.containsDuplicateII(nums3, 2));
    }




}
