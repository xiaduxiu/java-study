package com.xcr.alibaba;

/**
 * 给出一个长度为 n 的数组，和一个正整数 d。
 * 你每次可以选择其中任意一个元素 a[i] 将其变为 a[i] + d 或 a[i] - d，这算作一
 * 次操作。
 * 你需要将所有的元素全部变成相等元素，如果有解，请输出最小操作次数，如果
 * 无解请输出 -1。
 * 输入数字 n、数字 d，和一个长度为 n 的数组 a。1 <= n <= 100000，1 <= d
 * <= 100, 1 <= a[i] <= 100000。
 * 输出一个数字，表示最小的操作次数，如果无解输出 -1。
 * 示例 1
 * 输入：
 * 5
 * 2
 * [3,5,7,1,9]
 * 输出：
 * 6
 * 注意
 * 最优解为全部变为 5，共 1 + 0 + 1 + 2 + 2 = 6 次。
 *
 * @author xia
 */
public class Practice01 {

    private int minResult(int n, int d, int[] a) {
        int r = a[0] % d;
        int result = 0;
        for (int i = 1; i < a.length; i++) {
            if (r != a[i] % d) {
                return -1;
            }
        }
        int t = n-r;
        for (int num : a) {
            int abs = num-r;
            if (num < n) {
                result += ((t/d) - (abs/d));
            } else if(num > n) {
                result += ((abs/d) - (t/d));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 4, 6, 8};
        System.out.println(new Practice01().minResult(4, 2, a));
    }

}
