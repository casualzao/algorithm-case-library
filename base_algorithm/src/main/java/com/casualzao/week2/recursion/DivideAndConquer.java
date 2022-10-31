package com.casualzao.week2.recursion;

import java.util.*;

/**
 * @author pcmd
 * @create 2022-10-27 22:46
 */
public class DivideAndConquer {


    private Map<Integer,List<String>> exitMap = new HashMap<>();
    /**
     * 分支方法实现
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {

        if(n == 0){
            ArrayList<String> list = new ArrayList<>();
            list.add("");
            return list ;

        }
        if(exitMap.containsKey(n)){
            return exitMap.get(n);
        }
        List<String> ansList = new ArrayList<>();
        for (int k = 1; k <= n; k++) {
            List<String> aList = generateParenthesis(k - 1);
            List<String> bList = generateParenthesis(n - k);
            for (String a : aList) {
                for (String b : bList) {
                    String ans = "("  + a + ")" + b;
                    ansList.add(ans);
                }
            }

        }
        exitMap.put(n,ansList);
        return  ansList;
    }

    public static void main(String[] args) {
        DivideAndConquer  conquer = new DivideAndConquer();
        List<String> strings = conquer.generateParenthesis(3);
        System.out.println(strings);

    }
}
