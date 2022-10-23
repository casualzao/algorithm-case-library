package com.casualzao.week1;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 最小堆
 *
 * @author pcmd
 * @create 2022-10-22 13:21
 */
public class MinStack {

    Deque<Integer> minStack;
    Deque<Integer> numberStack;

    public MinStack() {
        minStack = new LinkedList<>();
        numberStack = new LinkedList<>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        numberStack.push(val);
        minStack.push(Math.min(val,minStack.peek()));
    }

    public void pop() {
        numberStack.pop();
        minStack.pop();
    }

    public int top() {
      return   numberStack.peek();
    }

    public int getMin() {
        return  minStack.peek();
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(2);
        stack.push(10);
        stack.push(1);
        stack.push(3);
        System.out.println(stack.getMin());
        System.out.println(stack.top());
        stack.pop();
        System.out.println(stack.getMin());
        System.out.println(stack.top());
    }
}
