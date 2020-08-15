package com.xcr.leetcode;

/**
 * 题目：
 *编写一个函数，其作用是将输入的字符串反转过来。
 * 示例 1:
 *
 * 输入: "hello"
 * 输出: "olleh"
 * 示例 2:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: "amanaP :lanac a ,nalp a ,nam A"
 *
 * 思考：
 *      直接从两边往中间走
 *
 *
 * @author 12037
 * @ClassName ReverseString
 * @Date 2019/11/26 15:06
 * @Version 1.0
 */
public class ReverseString {
    public static void main(String[] args) {
        // 第一种： 暴力遍历法  时间复杂度0（n） 空间复杂度O（n）
        StringBuilder sb1 = new StringBuilder();
        sb1.append("A man, a plan, a canal: Panama");
        StringBuilder sb2 = new StringBuilder();
        for (int i = sb1.length()-1; i >= 0; i--) {
            sb2.append(sb1.charAt(i));
        }
    }
}

