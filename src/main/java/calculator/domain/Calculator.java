package calculator.domain;

import java.math.BigDecimal;

public class Calculator {

    private final ExpressionTokenizer expressionTokenizer;

    public Calculator(ExpressionTokenizer expressionTokenizer) {
        this.expressionTokenizer = expressionTokenizer;
    }

    public BigDecimal calculate(String expression) {
        String[] tokens = expressionTokenizer.tokenize(expression);
        BigDecimal leftOperand = parseNumber(tokens[0]);

        for (int i = 1; i < tokens.length - 1; i += 2) {
            String operatorToken = tokens[i];
            Operator operator = Operator.find(operatorToken);
            BigDecimal rightOperand = parseNumber(tokens[i + 1]);

            leftOperand = operator.calculate(leftOperand, rightOperand);
        }

        return leftOperand;
    }

    private BigDecimal parseNumber(String number) {
        try {
            return new BigDecimal(number);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("유효한 수식이 아닙니다.", ex);
        }
    }
}
