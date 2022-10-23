package com.casualzao.week1;

import java.util.Objects;
import java.util.Stack;

/**
 * @author pcmd
 * @create 2022-10-22 13:51
 */
public class Evaluate {

    public int evalRPN(String[] tokens) {
        Stack<Integer> number = new Stack();
        for (String s : tokens) {
            if (Objects.equals(s, "+") || Objects.equals(s, "-") || Objects.equals(s, "*") || Objects.equals(s, "/")) {
                int num2 = number.pop();
                int num1 = number.pop();
                int calcul = calculate(s, num1, num2);
                number.push(calcul);
            } else {
                number.push(Integer.valueOf(s));
            }
        }
        return number.pop();
    }


    public int calculate(String op, Integer num1, Integer num2) {
        if (op.equals("+")) {
            return num1 + num2;
        } else if (op.equals("-")) {
            return num1 - num2;
        } else if (op.equals("*")) {
            return num1 * num2;
        } else if (op.equals("/")) {
            return num1 / num2;
        } else {
            return 0;
        }

    }


    public int calculator(String s) {
        Stack<Integer> numbers = new Stack<>();
        Stack<Character> options = new Stack<>();
        boolean isNum = true;
        for (char ch : s.toCharArray()) {
            if(ch == ' '){
                continue;
            }
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                if (!options.isEmpty()) {
                    // 计算权重
                    int wight = caculWight(ch);
                    // 上一个的权重
                    // 如果当前的 <= 上一个，则意味着 上一个计算表达式可以计算了
                    while ( !options.isEmpty() && caculWight(options.peek()) >= wight) {
                        caculeMid(numbers, options);
                    }
                }
                options.push(ch);
                isNum = false;
            } else {
                int curNum = Integer.parseInt(String.valueOf(ch));
                if (!numbers.isEmpty() && isNum)  {
                    Integer pop = numbers.pop();

                    int finalNum = pop * 10 + curNum;
                    numbers.push(finalNum);
                }else{
                    numbers.push(curNum);
                }
                isNum = true;

            }
        }


        while (!options.isEmpty()) {
            caculeMid(numbers, options);
        }


        return numbers.pop();
    }

    private void caculeMid(Stack<Integer> numbers, Stack<Character> options) {
        Integer num2 = numbers.pop();
        Integer num1 = numbers.pop();
        int cal = calculate1(options.pop(), num1, num2);
        numbers.push(cal);

    }


    public int caculWight(char op) {
        if (op == '*' || op == '/') {
            return 3;
        } else if(op == '-') {
            return 2;
        }else{
            return 1;
        }
    }

    public int calculate1(Character op, int num1, int num2) {
        if (op == '+') {
            return num1 + num2;
        } else if (op == '-') {
            return num1 - num2;
        } else if (op == '*') {
            return num1 * num2;
        } else if (op == '/') {
            return num1 / num2;
        } else {
            return 0;
        }

    }

    public static void main(String[] args) {
        Evaluate evaluate = new Evaluate();
        System.out.println(evaluate.calculator("1*2-3/4+5*6-7*8+9/10"));
    }
}
