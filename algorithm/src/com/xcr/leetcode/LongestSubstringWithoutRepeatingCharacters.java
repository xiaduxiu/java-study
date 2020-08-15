package com.xcr.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 题目：给定一个字符串，找出不含有重复字符的最长子串的长度。
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 无重复字符的最长子串是 "abc"，其长度为 3。
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1.
 * 解释: 无重复字符的最长子串是 "b"，其长度为 1。
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 无重复字符的最长子串是 "wke"，其长度为 3。
 * 请注意，答案必须是一个子串，"pwke" 是一个子序列 而不是子串。
 *
 *  思路：双指针法 ：遍历字符串，定义两个指针left和 right, 分别记录子数组的左右的边界位置，使用hashSet作为滑动窗口，
 *       将字符存储在当前窗口[i,j)（最初i=j），然后向右侧滑动索引j,如果它不在hashSet中，就继续滑动j,直到是s[j]在hashSet中，
 *       我们找到的没有重复字符的最长子字符串将会以索引 i 开头，对所有的 i这样做
 *
 *
 * @author 12037
 * @ClassName LongestSubstringWithoutRepeatingCharacters
 * @Date 2019/11/11 15:53
 * @Version 1.0
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters characters = new LongestSubstringWithoutRepeatingCharacters();
        int result = characters.lengthOfLongestSubstring("abcdbcbb");
        System.out.println(result);
    }

}
