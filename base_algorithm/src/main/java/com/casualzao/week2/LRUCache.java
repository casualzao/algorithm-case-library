package com.casualzao.week2;

import java.util.HashMap;
import java.util.Map;

/**
 * lru cache
 * 实现一个 lru 缓存，则需要一个 map 用来存储 key-value
 * 还需要一个数据结构用来存 顺序和要淘汰的 入 key 最久未使用的
 * 这个数据结构可以用 2 种模式：
 * 1. 双向链表
 * 2. 有序集合 ，类似于 redis 的 zset 可按照时间排序
 *
 * @author pcmd
 * @create 2022-10-24 22:00
 */
public class LRUCache {

    Node head;
    Node tail;
    int capacity;
    Map<Integer, Node> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
        cache = new HashMap<>(capacity * 2);
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (null == node) {
            return -1;
        }

        node.remove(node);
        node.insert(head, node);
        return node.value;

    }

    public void put(int key, int value) {
        Node node = new Node(value, key);
        if (cache.containsKey(key)) {
            node.remove(cache.get(key));
        } else if (cache.size() >= capacity) {
            int key1 = tail.pre.key;
            cache.remove(key1);
            node.remove(tail.pre);
        }
        node.insert(head, node);
        cache.put(key, node);
    }


    static class Node {

        int key = 0;
        int value = 0;
        Node pre;
        Node next;

        public Node(int value, int key) {
            this.value = value;
            this.key = key;
        }

        public Node() {
        }

        void remove(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        void insert(Node head, Node node) {
            head.next.pre = node;
            node.next = head.next;
            head.next = node;
            node.pre = head;
        }

    }


    public static void main(String[] args) {
        String[] command = new String[]{"LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"};
        String[] value = new String[]{"2", "1,1", "2,2", "1", "3,3", "2", "4,4", "1", "3", "4"};
        LRUCache cache1 = new LRUCache(Integer.parseInt(value[0]));
        ;
        for (int i = 0; i < command.length; i++) {
            switch (command[i]) {
                case "LRUCache":
                    break;
                case "put":
                    String[] split = value[i].split(",");
                    cache1.put(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                    break;
                case "get":
                    cache1.get(Integer.parseInt(value[i]));
                    break;
                default:
                    break;
            }
        }
    }

}
