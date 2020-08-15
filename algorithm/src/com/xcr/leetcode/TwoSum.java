package com.xcr.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
 * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 *
 * 思路：
 *      使用哈希map降低时间复杂度。
 *      遍历nums数组，i为索引值，每个值都判断是否有key等于target-nums[i]
 *      如果有则找到两个值，如果不存在则将i存入表中，继续遍历，直到找到。
 *
 *
 * @author 12037
 * @ClassName TwoSum
 * @Date 2019/11/15 15:23
 * @Version 1.0
 */
public class TwoSum {

    public int[] TwoSum(int[] nums, int target){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i])) {
                return new int[]{map.get(target-nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 13;
        TwoSum twoSum = new TwoSum();
        int[] result = twoSum.TwoSum(nums, target);
        System.out.println(Arrays.toString(result));
    }
}
