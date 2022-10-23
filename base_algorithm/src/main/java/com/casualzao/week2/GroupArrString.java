package com.casualzao.week2;

import java.util.*;

/**
 * @author pcmd
 * @create 2022-10-23 18:46
 */
public class GroupArrString {


    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> groupMap = new HashMap<>(strs.length);
        for (String str : strs) {
            String sort = sortStr(str);
            List<String> orDefault = groupMap.getOrDefault(sort, new ArrayList<String>());
            orDefault.add(str);
            groupMap.put(sort,orDefault);
        }
        return new ArrayList<>(groupMap.values());

    }

    public String sortStr(String str){
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }


    public static void main(String[] args) {

    }
}
