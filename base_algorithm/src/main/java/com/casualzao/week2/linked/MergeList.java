package com.casualzao.week2.linked;

import com.casualzao.week1.MergeTowList;

import javax.swing.*;

/**
 * 合并 k 个有序链表
 * @author pcmd
 * @create 2022-10-28 00:01
 */
public class MergeList {

       static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }



    public ListNode mergeKLists(ListNode[] lists) {

        if(lists.length == 0){
            return null;
        }
        return merge(lists,0, lists.length -1);
    }

    public ListNode merge(ListNode[] node,int left,int right){
           if(left == right){
                return node[left];
           }

           int mid = left +  (right -left) /2;
           ListNode leftList = merge(node, left, mid);
           ListNode rightList = merge(node, mid+1, right);
           return mergeTwoLists(leftList,rightList);
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
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
