package org.example.calculator;

import java.util.List;

public class Calculator {

    private final List<String> expression;

    public Calculator(List<String> expression) {
        this.expression = expression;
    }

    public int calculate() {
        int result = Integer.parseInt(expression.get(0));

        for (int i = 1; i < expression.size(); i += 2) {
            Operator operator = Operator.fromSymbol(expression.get(i));
            int nextNumber = Integer.parseInt(expression.get(i + 1));
            result = operator.calculate(result, nextNumber);
        }

        return result;
    }
}
