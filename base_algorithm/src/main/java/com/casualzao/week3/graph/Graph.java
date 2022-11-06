package com.casualzao.week3.graph;

import java.util.*;

/**
 * @author pcmd
 * @create 2022-11-01 23:46
 */
public class Graph {


    /** 邻接表 用来存储图，图的存储*/
    private Map<Integer,List<Integer>> to;
    /** 是否访问过的数组*/
    private boolean[] visited;
    /** 是否成环*/
    private boolean hasCycle;

    /**
     * 查找无向图的的最后一条边
     * @param edges
     * @return
     */

    public int[] findRedundantConnection(int [][] edges){
        int n =0;
        // 构造 n 个边，其实就长度，用来构建
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            n = Math.max(n,Math.max(x,y));
        }
        to = new HashMap<>(n+1);
        visited = new boolean[n+1];
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            // 出边数组加边的方式
            List<Integer> xArr = to.getOrDefault(x, new ArrayList<>());
            xArr.add(y);
            to.put(x,xArr);
            List<Integer> yArr = to.getOrDefault(y, new ArrayList<>());
            yArr.add(x);
            to.put(y,yArr);
            hasCycle = false;
            dfs(x,0);
            if(hasCycle){
                return edge;
            }
        }


        return new int[0];
    }


    /**
     * 图的深度优先遍历判断环 模版
     * @param x
     * @param father
     */
    void dfs(int x,int father){
        visited[x] = true;
        // 出边数组访问 x 能到的周为点的方法
        for (Integer y : to.get(x)) {
            if(y == father){
                continue;
            }
            if(!visited[y]){
                dfs(y,x);
            }else{
                hasCycle = true;
            }
        }
        visited[x] = false;
    }


    public static void main(String[] args) {
        Graph graph = new Graph();
        int[][] param = new int[][]{{1,2},{1,3},{2,3}};
        int[] redundantConnection = graph.findRedundantConnection(param);
        System.out.println(Arrays.toString(redundantConnection));
    }
}
