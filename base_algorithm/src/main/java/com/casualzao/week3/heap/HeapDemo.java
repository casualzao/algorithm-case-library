package com.casualzao.week3.heap;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.ToIntFunction;

/**
 * @author pcmd
 * @create 2022-11-05 21:48
 */
public class HeapDemo {


    public class ListNode {

        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }


    /**
     * 合并 k 个有序链表
     * @param lists
     * @return
     */
    public ListNode mergeKList(ListNode[] lists){
        // 构造优先队列，用优先队列来进行排序，和 merge
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(lists.length,Comparator.comparingInt(value -> value.val));

        for (ListNode list : lists) {
            if(null == list){
                continue;
            }
            priorityQueue.add(list);
        }

        // 创建一个保护节点
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;

        while(!priorityQueue.isEmpty()){

            ListNode poll = priorityQueue.poll();
            p.next = poll;
            if(poll.next != null){
                priorityQueue.add(poll.next);
            }
            p = p.next;

        }
        return dummy.next;
    }
}
