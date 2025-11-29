package org.example;

import java.util.Stack;

public class Calculator {
    private Validation validation = new Validation();

    public String calculate(String refinedFormula) {
        // 두 자리 숫자도 분리 가능
        String[] tokens = refinedFormula.split("(?<=[+\\-*/])|(?=[+\\-*/])");

        Stack<String> stack = new Stack<>();

        for (int i = tokens.length - 1; i >= 0; i--) {
            stack.push(tokens[i]);
        }

        while (stack.size() > 2) {
            String first = stack.pop();
            String operator = stack.pop();
            String second = stack.pop();

            stack.push(String.valueOf(compute(first, operator, second)));
        }

        return stack.pop();
    }

    private int compute(String first, String operator, String second) {
        switch (operator) {
            case "+":
                return add(first, second);
            case "-":
                return sub(first, second);
            case "*":
                return mul(first, second);
            case "/":
                return div(first, second);
            default:
                return 0;
        }
    }

    private int add(String first, String second) {
        int firstOperand = Integer.parseInt(first);
        int secondOperand = Integer.parseInt(second);

        return firstOperand + secondOperand;
    }

    private int sub(String first, String second) {
        int firstOperand = Integer.parseInt(first);
        int secondOperand = Integer.parseInt(second);

        return firstOperand - secondOperand;
    }

    private int mul(String first, String second) {
        int firstOperand = Integer.parseInt(first);
        int secondOperand = Integer.parseInt(second);

        return firstOperand * secondOperand;
    }

    private int div(String first, String second) {
        int firstOperand = Integer.parseInt(first);
        int secondOperand = Integer.parseInt(second);

        if (secondOperand == 0) {
            throw new ArithmeticException("0으로 나눌 수 없습니다.");
        }

        double result = (double) firstOperand / secondOperand;
        return (int) result;
        // 소수점 그냥 자름
    }
}
