package com.xcr.meituan;


import java.util.Stack;

/**
 * 小团的旅游路线
 *
 * 输入： 第一行包含一个正整数n, 表示购票记录数量（1<n <10000）
 * 接下来n行，每行是两个长度不超过10的仅有小写字母组成的字符串 S_a S_b,表示购买一张从S_a到S_b的车票
 *
 * 输出：一个整数，表示旅游次数
 * 样例输入：
 * 6
 * beijing nanjing
 * nanjing guangzhou
 * guangzhou shanghai
 * shanghai beijing
 * fuzhou beijing
 * beijing fuzhou
 * 样例输出：
 * 2
 *
 */
public class Practice01 {

    /**
     * 切割字符串，每个地点入栈，如果入栈地点相同则出栈，
     */
    private int travelCount(int num, String[] strings) {
        int result = 0;
        Stack<String> stack = new Stack<>();
        stack.push(strings[0]);
        stack.push(strings[1]);
        for (int i = 2; i < num; i+=2) {
            if (stack.empty()) {
                stack.push(strings[i]);
                stack.push(strings[i+1]);
                continue;
            }
            if (strings[i].equals(stack.peek())) {
                stack.pop();
            } else {
                stack.push(strings[i]);
            }
            if (strings[i + 1].equals(stack.peek())) {
                stack.pop();
                result += 1;
            } else {
                stack.push(strings[i + 1]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int count = 8;
        String[] strings = new String[]{"1","2","3","1","4","5","5","3"};
        Practice01 practice01 = new Practice01();
        int i = practice01.travelCount(8, strings);
        System.out.println("旅游次数为： " + i);
    }

}
