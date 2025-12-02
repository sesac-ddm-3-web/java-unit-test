package kr.co.calculator.domain;

public class Calculator {
    private final ExpressionTokenizer expressionTokenizer;

    public Calculator(ExpressionTokenizer expressionTokenizer) {
        this.expressionTokenizer = expressionTokenizer;
    }

    public Integer calculate(String expression) {
        // 문자열 계산식을 검증된 토큰들로 토큰화
        String[] tokenized = expressionTokenizer.tokenizeExpression(expression);

        // === 계산 ===
        if (tokenized.length == 1) {
            return Integer.parseInt(tokenized[0]);
        }

        int result = Integer.parseInt(tokenized[0]);

        for (int i = 1; i < tokenized.length; i += 2) {
            String operatorSymbol = tokenized[i];
            int operand = Integer.parseInt(tokenized[i + 1]);

            Operator operator = Operator.from(operatorSymbol);
            result = operator.apply(result, operand);
        }

        return result;
    }
}
