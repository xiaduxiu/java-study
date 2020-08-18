package com.xcr.alibaba;

import java.util.Arrays;

/**
 *现在有 3 只怪兽，他们的都有自己的血量 a,b,c(1<=a,b,c<=100)，当 Tom 打
 * 死第一怪兽的时候花费的代价为 0，其余的怪兽的代价为当前的怪兽的血量减去上一
 * 个怪兽的血量的绝对值。问 Tom 打死这些怪兽所需要的最小代价 ?
 * 分别输入三只怪兽的血量；
 * 输出打死三只怪兽的最小代价。
 * 示例 1
 * 输入：
 * 2
 * 5
 * 8
 * 输出：
 * 6
 *
 * @author xia
 */
public class Practice02 {

    private int minResult(int num1, int num2, int num3) {
        int[] nums = new int[]{num1, num2, num3};
        int result = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-1; i++) {
            result += Math.abs(nums[i + 1] - nums[i]);
        }
        return result;

    }

    public static void main(String[] args) {
        System.out.println(new Practice02().minResult(5, 2, 8));
    }

}
