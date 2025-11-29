package org.example.domain;

import java.util.Set;

public class Calculator {

    private final Set<Operator> availableOperators = Set.of(
        Operator.PLUS,
        Operator.MINUS,
        Operator.MULTIPLY,
        Operator.DIVIDE
    );

    public double calculate(String expression) {
        String[] ops = parseExpression(expression);
        double result = parseOperand(ops[0]);

        for (int i = 1; i < ops.length; i += 2) {
            Operator operator = parseOperator(ops[i]);
            double operand = parseOperand(ops[i + 1]);

            result = operator.operate(result, operand);
        }

        return result;
    }

    private String[] parseExpression(String expression) {
        validateExpression(expression);
        return expression.split(" ");
    }

    private double parseOperand(String token) {
        validateOperand(token);
        return Double.parseDouble(token);
    }

    private Operator parseOperator(String token) {
        Operator operator = Operator.getBySymbol(token);
        validateOperatorSupported(operator);
        return operator;
    }

    private void validateExpression(String expression) {
        if (expression == null || expression.isBlank()) {
            throw new IllegalArgumentException("수식이 비어있습니다.");
        }

        String[] ops = expression.split(" ");
        if (ops.length % 2 == 0) {
            throw new IllegalArgumentException("유효하지 않은 수식입니다.");
        }
    }

    private void validateOperand(String operand) {
        try {
            Double.parseDouble(operand);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("피연산자가 숫자가 아닙니다.");
        }
    }

    private void validateOperatorSupported(Operator operator) {
        if (!availableOperators.contains(operator)) {
            throw new UnsupportedOperationException("지원하지 않는 연산자 입니다.");
        }
    }
}
