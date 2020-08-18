package com.xcr.alibaba;

import java.util.Arrays;

/**
 * 现在有 n 个点 (1<=n<=1000)，每个点都有一个值称为点权 ai(ai 为偶数，
 * 1<=ai<=1000)，现在可以将任意两个点相连，连起来以后这条边也有一个值称为边
 * 权，这个边的边权为这两个点的点权之和的一半。现在需要你添加 n-1 条边，问将
 * 这 n 个点连通以后 ( 连通是指任意两个点都能互相到达 ) 的最大的边权和是多少 ?
 * 输入点的数量 n；和 n 个数，表示点权的值
 * 输出最大的边权和
 * 示例 1
 * 输入：
 * 5
 * [2,4,6,8,10]
 * 输出：
 * 30
 */
public class Practice03 {

    public int maxWegiht(int n, int[] nums) {
        int result = 0;
        if (n == 1) {
            return nums[0]/2;
        }
        Arrays.sort(nums);
        for (int i = 0; i < n-1; i++) {
            result += (nums[i] + nums[n-1])/2;
        }
        return result;
    }


    public static void main(String[] args) {

        System.out.println(new Practice03().maxWegiht(1, new int[]{4}));

    }











}
