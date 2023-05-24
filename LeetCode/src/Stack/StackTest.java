package Stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;

public class StackTest {

    /**
     * 20. 有效的括号
     *
     * @return boolean
     */
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char a : chars) {
            if (a == '(' || a == '{' || a == '[') {
                stack.push(a);
            }
            if (a == ')') {
                if (stack.empty()) {
                    return false;
                } else {
                    if (stack.peek() == '(') {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
            if (a == '}') {
                if (stack.empty()) {
                    return false;
                } else {
                    if (stack.peek() == '{') {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
            if (a == ']') {
                if (stack.empty()) {
                    return false;
                } else {
                    if (stack.peek() == '[') {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
        }
        return stack.empty();
    }

    public boolean isValid2(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char a : chars) {
            if (a == '(' || a == '{' || a == '[') {
                stack.push(a);
            } else {
                if (stack.empty()) {
                    return false;
                }
                Character pop = stack.pop();
                if (pop == '(' && a != ')') {
                    return false;
                }
                if (pop == '{' && a != '}') {
                    return false;
                }
                if (pop == '[' && a != ']') {
                    return false;
                }
            }
        }
        return stack.empty();
    }

    /**
     * 856. 括号的分数
     *
     * @return int
     */
    public static int scoreOfParentheses(String s) {
        char[] array = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        for (char c : array) {
            if (c == '(') {
                stack.push(0);
            } else {
                int v = stack.pop();
                int top = stack.pop() + Math.max(2 * v, 1);
                stack.push(top);
            }
        }
        return stack.peek();
    }

    public static int scoreOfParentheses2(String s) {
        s = s.replace("()", "1");
        char[] array = s.toCharArray();
        int sum = 1, res = 0;
        for (char c : array) {
            if (c == '(') {
                sum *= 2;
            } else if (c == ')') {
                sum /= 2;
            } else if (c == '1') {
                res = res + sum;
            }
        }
        return res;
    }

    /**
     * 224. 基本计算器
     *
     * @return int
     */
    public static int calculate(String s) {
        Deque<Integer> ops = new LinkedList<Integer>();
        ops.push(1);
        int sign = 1;

        int ret = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            } else {
                long num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ret += sign * num;
            }
        }
        return ret;
    }

    /**
     * 150. 逆波兰表达式求值
     *
     * @param tokens 令牌
     * @return int
     */
    public static int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            if (!Objects.equals(token, "+") && !Objects.equals(token, "-") && !Objects.equals(token, "*") && !Objects.equals(token, "/")) {
                stack.push(token);
            } else {
                Integer pop = Integer.parseInt(stack.pop());
                Integer pop1 = Integer.parseInt(stack.pop());
                if (token.equals("+")) {
                    stack.push(String.valueOf(pop1 + pop));
                } else if (token.equals("-")) {
                    stack.push(String.valueOf(pop1 - pop));
                } else if (token.equals("*")) {
                    stack.push(String.valueOf(pop1 * pop));
                } else if (token.equals("/")) {
                    stack.push(String.valueOf(pop1 / pop));
                }
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static void main(String[] args) {
//        String[] tokens = new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
//        System.out.println(evalRPN(tokens));
//          String s = "(()(()))";
//          System.out.println(scoreOfParentheses2(s));
        String s = "-2+ 1";
        System.out.println(calculate(s));
    }
}
