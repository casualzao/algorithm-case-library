package com.casualzao.week3.graph;

import jdk.nashorn.internal.IntDeque;

import java.util.*;

/**
 * @author pcmd
 * @create 2022-11-02 00:32
 */
public class GraphBfs {

    private Map<Integer, List<Integer>> to;
    private int[] inDeg ;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        to = new HashMap<>();
        inDeg = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int x = prerequisite[0];
            int y = prerequisite[1];
//            List<Integer> xArr = to.getOrDefault(x,new ArrayList<>());
//            xArr.add(y);
//            to.put(x,xArr);

            List<Integer> yArr = to.getOrDefault(y,new ArrayList<>());
            yArr.add(x);
            to.put(y,yArr);
            inDeg[x] ++;
        }
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if(inDeg[i] == 0){
                queue.add(i);
            }
        }

        List<Integer> lessions = new ArrayList<>();
        while (!queue.isEmpty()){
            Integer poll = queue.poll();
            lessions.add(poll);

            for (Integer y : to.getOrDefault(poll,new ArrayList<>())) {
                inDeg[y] --;
                if(inDeg[y] == 0){
                    queue.add(y);
                }
            }
        }
        return lessions.size() == numCourses;
    }

    public static void main(String[] args) {
        GraphBfs graphBfs = new GraphBfs();
        boolean b = graphBfs.canFinish(2, new int[][]{{1, 0},{0,1}});
        System.out.println(b);
    }
}
