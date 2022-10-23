package com.casualzao.week1;

import java.util.Stack;

/**
 * @author pcmd
 * @create 2022-10-18 22:38
 */
public class NextNumFind {

    public static void main(String[] args) {
         isValid("");
    }

    private static boolean  isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()){
            if(ch == '('){
                stack.push(')');
            }else if(ch == '['){
                stack.push(']');
            }else if(ch == '{') {
                stack.push('}');
            }else if(!stack.isEmpty() & ch != stack.pop()){
                return false;
            }
        }
        return true;
    }
}
