package com.casualzao.week2;


import java.util.*;
/**
 * 给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。
 * 滑动窗口
 * @author pcmd
 * @create 2022-10-23 23:42
 */
public class SubstringAllWord {

    public List<Integer> findSubstring(String s, String[] words) {
        int wordLen = words[0].length();
        int windowLength = words.length * wordLen;
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < s.length(); i++) {
            if(i+windowLength > s.length()){
                break;
            }
            String curStr = s.substring(i, i + windowLength);
            if(isCompare(curStr,words)){
                result.add(i);
            }
        }
        return result;
    }

    public boolean isCompare(String cur,String[] words){
        int length = words[0].length();
        Map<String,Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word,wordMap.getOrDefault(word,0)+1);
        }
        for (int i = 0; i < cur.length(); ) {
            String substring = cur.substring(i, i + length);
            Integer integer = wordMap.get(substring);
            if(null == integer || integer == 0){
                return false;
            }
            wordMap.put(substring,wordMap.get(substring) -1);
            i +=length;
        }
        return true;
    }

    public static void main(String[] args) {


        SubstringAllWord word = new SubstringAllWord();
        List<Integer> barfoothefoobarman = word.findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","good"});
        System.out.println(barfoothefoobarman);
    }
}
