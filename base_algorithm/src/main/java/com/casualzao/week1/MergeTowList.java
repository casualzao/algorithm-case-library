package com.casualzao.week1;

import java.util.List;

/**
 * 合并 2 个有序链表
 * @author pcmd
 * @create 2022-10-18 13:27
 */
public class MergeTowList {


    public static class ListNode {
        int val;

        ListNode current;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        public void add(int date) {

            current.next = new ListNode(date);
            current = current.next;

        }

        public int getVal() {
            return val;
        }

        public ListNode getCurrent() {
            return current;
        }

        public ListNode getNext() {
            return next;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (null == list1) {
            return list2;
        }

        if (null == list2) {
            return list1;
        }

        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }


    }


}
