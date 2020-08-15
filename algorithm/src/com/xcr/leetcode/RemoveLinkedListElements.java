package com.xcr.leetcode;

import java.util.LinkedList;

/**
 * 题目：删除链表中等于给定值 val 的所有节点。
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 *
 *
 * 思路： 考察基本的链表遍历和设置指针。 定义一个虚拟头节点head,遍历查看原链表，遇到与给定值相同的元素，
 * 将元素的前后两个节点连接起来，然后删除该元素。
 *
 * @author 12037
 * @ClassName RemoveLinkedListElements
 * @Date 2019/11/30 11:19
 * @Version 1.0
 */
public class RemoveLinkedListElements {

    public LinkedList<Integer> removeElements(LinkedList<Integer> list, int target){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == target) {

            }
        }
        return list;
    }


    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(6);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        RemoveLinkedListElements elements = new RemoveLinkedListElements();
        LinkedList<Integer> result = elements.removeElements(list, 6);
        System.out.println(result.toString());
    }


}
