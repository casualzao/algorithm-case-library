package com.casualzao.week3.tree;

import javafx.util.Pair;

import javax.management.OperationsException;
import java.util.*;

/**
 * 树的遍历
 *
 * @author pcmd
 * @create 2022-10-29 10:32
 */
public class ForEach {


    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }

    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        bfs(root,result);
        return result;
    }

    private void bfs(Node root,List<List<Integer>> result){
        if(null == root){
            return;
        }

        Queue<Pair<Node, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root,0));

        while (!queue.isEmpty()){

            Pair<Node, Integer> poll = queue.poll();
            Node node = poll.getKey();
            Integer depth = poll.getValue();
            if(depth >= result.size()){
                result.add(new ArrayList<>());
            }
            result.get(depth).add(node.val);

            for (Node child : node.children) {
                queue.add(new Pair<>(child,depth+1));
            }
        }
    }



    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        dfs(root,result);
        return result;
    }

    /**
     * 递归法的前序遍历
     * @param node
     * @param result
     */
    private  void dfsR(Node node ,List<Integer> result){
        if(null == node){
            return;
        }
        result.add(node.val);
        for (Node child : node.children) {
            dfsR(child,result);
        }
    }

    /**
     * 迭代法 前序遍历，深度优先遍历
     * @param root
     * @param result
     */
    private void dfs(Node root,List<Integer> result){
        if(null == root){
            return ;
        }

        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()){
            Node peek = stack.pop();
            if(peek == null){
                continue;
            }
            result.add(peek.val);
            // 从后往前添加
            for (int i = peek.children.size()-1; i >=0; i--) {
                stack.add(peek.children.get(i));
            }
        }
    }



}
