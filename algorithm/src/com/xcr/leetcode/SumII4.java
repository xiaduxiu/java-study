package com.xcr.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
 * 例如:
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * 输出:
 * 2
 * 解释:
 * 两个元组如下:
 * (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 *
 * 思路：
 *      A+B+C+D = 0  ==> (A+B) = -(C+D)
 *      使用哈希表，把A和B的两两之和都求出来，在哈希表中建立两数之和与其出现次数之间的映射
 *      遍历C和D中任意两个数之和，看哈希表中存不存在两数之和的相反数。
 *
 *
 *
 * @author 12037
 * @ClassName SumII4
 * @Date 2019/11/15 16:14
 * @Version 1.0
 */
public class SumII4 {

    public static void main(String[] args) {
        int[] A = new int[]{-1, -1};
        int[] B = new int[]{-1,1};
        int[] C = new int[]{-1, 1};
        int[] D = new int[]{1, -1};
        Map<Integer, Integer> table  = new HashMap<>();
        for (Integer i:A) {
            for (Integer j:B) {
                if (table.containsKey(i+j)) {
                    table.put(i+j, table.get(i+j)+1);
                    continue;
                }
                table.put(i+j, 1);
            }
        }
        int res = 0;
        for (Integer k:C) {
            for (Integer l:D) {
                if (table.containsKey(-k-l)) {
                    res += table.get(-k-l);
                }
            }
        }
        System.out.println(res);
    }


}
