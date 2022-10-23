package com.casualzao.week1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/**
 * 计算器
 *
 * @author pcmd
 * @create 2022-10-22 16:04
 */
public class Calculator {


    public int calculate(String s) {
        s += " ";
        Stack<Character> options = new Stack<>();
        List<String> tokens = new ArrayList<>();
        char[] chars = s.toCharArray();
        StringBuilder number = new StringBuilder();
        boolean needZero = true;
        for (char aChar : chars) {



            if (Character.isDigit(aChar)) {
                number.append(aChar);
                needZero = false;
                continue;
            } else {
                if (!number.toString().isEmpty()) {
                    tokens.add(number.toString());
                    number = new StringBuilder();
                }

            }

            if (aChar == ' ') {
                continue;
            }

            if(aChar == '('){
                options.push(aChar);
                needZero = true;
                continue;
            }
            if(aChar == ')'){
                while (options.peek() !='('){
                    tokens.add(options.pop().toString());
                }
                if(options.peek() == '('){
                    options.pop();
                }
                needZero = false;
                continue;
            }
            // 构建 token

            if(needZero && (aChar == '+' || aChar=='-')){
                tokens.add("0");
            }

            if (!options.isEmpty()) {
                int curRank = getRank(aChar);
                while (!options.empty() && getRank(options.peek()) >= curRank) {
                    tokens.add(options.pop().toString());
                }
            }
            options.push(aChar);
            needZero = false;
        }


        while (!options.empty()) {
            tokens.add(options.pop().toString());
        }

        Evaluate evaluate = new Evaluate();
        return evaluate.evalRPN(tokens.toArray(new String[0]));
    }

    public int getRank(char opt) {
        if (opt == '*' || opt == '/') {
            return 2;
        }
        if (opt == '+' || opt == '-') {
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        Calculator evaluate = new Calculator();
        System.out.println(evaluate.calculate("1-(     -2)"));
    }
}
