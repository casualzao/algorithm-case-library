package com.casualzao.week3.heap;


import com.casualzao.ListNode;
import javafx.beans.binding.When;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.nio.channels.NonReadableChannelException;
import java.util.*;
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

    @AllArgsConstructor
    public class Node{
        int key;
        ListNode listNode;

    }

    /**
     * 一个二叉堆
     */
    @Getter
    class BinaryHeap{
        // 小根堆
        List<Node> heap;

        public BinaryHeap() {
            heap = new ArrayList<>();
        }

        public boolean isEmpty(){
            return heap.isEmpty();
        }
        // 往堆内添加数据
        public void push(Node node){
            // 插入头部
            heap.add(node);
            // 往上调整
            heapIfUp(heap.size() -1);
        }
        // 删除堆 最小 元素
        public Node pop(){

            Node ans = heap.get(0);
            // 交换末尾到头部，然后删除末尾
            Collections.swap(heap,0,heap.size()-1);
            // 向下调整
            heapIfDown(0);
            return ans;
        }



        private void heapIfUp(int p){
            while(p >0){
                int fa = (p-1)/2;
                if(heap.get(p).key <heap.get(fa).key){// 小根堆
                    Collections.swap(heap,p,fa);
                }else {
                    break;
                }
            }
        }

        private void heapIfDown(int p) {
            int child = p/2 +1;
            // child 未出界 说明 p 有合法的 child，还不是叶子
            while (child < heap.size()){
                // 先比较 2 个 child ,谁小就继续跟 p 比较
                int other = p*2 +2;
                if(other <heap.size() && heap.get(other).key < heap.get(child).key) {
                    child = other;
                }
                // 让 child 跟 p 比较
                if(heap.get(p).key >heap.get(child).key){
                    Collections.swap(heap,p,child);
                    p = child;
                    child = p*2+1;
                }else{
                    break;
                }
            }
        }


    }


    public ListNode mergeKListMy(ListNode[] lists){
        // 构造优先队列，用优先队列来进行排序，和 merge
        BinaryHeap heap = new BinaryHeap();

        for (ListNode list : lists) {
            if(null == list){
                continue;
            }
            heap.push(new Node(list.val,list));
        }

        // 创建一个保护节点
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while(!heap.isEmpty()){

            Node poll = heap.pop();
            tail.next = poll.listNode;
            tail = tail.next;
            ListNode p = poll.listNode.next;
            if(p.next != null){
                heap.push(new Node(p.val,p));
            }
        }
        return dummy.next;
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
