package solutions;

import adt.Stack;
import ds.StackImpl;
import exceptions.EmptyCollectionException;

public class ParenthesisChecker {

    public static boolean isBalanced(String expression) {
        Stack<Character> stack = new StackImpl<>();

        try {
            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);

                if (c == '(' || c == '{' || c == '[') {
                    stack.push(c);
                } 
                else if (c == ')' || c == '}' || c == ']') {
                    if (stack.isEmpty()) return false;

                    char top = stack.pop();
                    if (!matches(top, c)) return false;
                }
            }
        } catch (EmptyCollectionException e) {
            return false;
        }

        return stack.isEmpty();
    }

    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }

    public static void main(String[] args) {
        String[] tests = {"(()()()())", "({[]})", "((((", "())", "([)]"};

        for (String t : tests) {
            System.out.println(t + " -> " + (isBalanced(t) ? "Equilibrado" : "No equilibrado"));
        }
    }
}