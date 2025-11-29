package org.example.calculator;

import org.example.expression.Expression;
import org.example.expression.ExpressionParser;

public class CalculatorImpl implements Calculator{

    private final ExpressionParser parser;

    public CalculatorImpl(ExpressionParser parser) {
        this.parser = parser;
    }

    @Override
    public int calculate(String input) {
        validateInput(input);
        Expression expression = parser.parse(input);
        return expression.evaluate();
    }

    private void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력이 비어 있습니다.");
        }
    }
}
