package com.xcr.alibaba;

/**
 *有一个阵营，里面有 n 个小队 (1<=n<=100)，每个小队都有他们的能力值
 * ai(0<=i
 * 现在有一个紧急情况，需要从这些小队中选出连续的几个小队，组成一个最强的
 * 团队。最强的团队的定义为这个团队的所有小队的平均能力值最高。如果有多个最强
 * 团队，则选包含小队最多的一个。
 * 现在请你写个程序，输出这个最强的团队包含的小队的个数。
 * 输入小队的数量 n，和 n 个数，分别代表各小队的能力值 ai
 * 输出一个数表示这个最强团队包含的小队的个数。
 * 示例 1
 * 输入：
 * 6
 * [1,2,3,3,2,1]
 * 输出：
 * 2
 *
 * @author xia
 */

public class Practice04 {

    /**
     * 即解决这道题需要找出数组中连续最大值的个数，若有多个连续最大值，选择个数最多的。
     * 具体实现时，可以先找出数组中最大的能力值是多少，然后设置一个标记 tag。
     * 接着遍历数组，比较每个数组元素和最大值，数组元素等于最大的值的时候，将 tag
     * 标记为 1，数组元素不等于最大值时，将 tag 置为 0。
     * 在 tag 等于 1 时统计连续最大值的数量，若统计到多个最大值，则记录最大的
     * 那个。
     * @param n
     * @param nums
     * @return
     */
    public int solution(int n, int[] nums) {
        // 求出当前最大值的位置
        int maxNum = nums[0];
        int result = 0;
        for (int num:nums) {
            maxNum = Math.max(maxNum, num);
        }
        // 从最大值开始，左右比较
        for (int i = 0; i < n; i++) {
            if (nums[i] == maxNum) {

            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Practice04().solution(6, new int[]{1,2,3,3,2,1}));

    }

}
