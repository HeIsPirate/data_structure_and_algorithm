package org.my_test.leetcode.basic;

import java.util.Map;
import java.util.Stack;

/**
 * <a href="https://leetcode.cn/problems/valid-parentheses/">有效的括号</a>
 */
public class ValidParentheses {

    public static void main(String[] args) {
        System.out.println(isValid("{[]}"));
        System.out.println(isValid("([)]"));
        System.out.println(isValid("()[]{}"));
    }

    public static boolean isValid(String s) {
        byte[] chars = s.getBytes();
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = Map.of(')', '(', ']', '[', '}', '{');

        for (byte aChar : chars) {
            char c = (char) aChar;
            if (map.containsKey(c)) {
                if (stack.isEmpty()) {
                    return false;
                }
                Character pop = stack.pop();
                if (!pop.equals(map.get(c))) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }

        return stack.empty();
    }
}
