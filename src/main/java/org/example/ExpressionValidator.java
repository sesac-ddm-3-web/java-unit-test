package org.example;

public class ExpressionValidator {
    public void validate(String expression) {
        validateNull(expression);
        validateBlank(expression);
        validateTokens(expression);
    }

    private void validateNull(String expression) {
        if (expression == null) {
            throw new NullPointerException("expression is null");
        }
    }

    private void validateBlank(String expression) {
        if (expression.trim().isEmpty()) {
            throw new IllegalArgumentException("expression is blank");
        }
    }

    private void validateTokens(String expression) {
        String trimmed = expression.replaceAll("\\s+", "");

        if (!trimmed.matches("[0-9+\\-*/]+")) {
            throw new IllegalArgumentException("Invalid character detected in expression");
        }

        String[] tokens = trimmed.split("(?<=[+\\-*/])|(?=[+\\-*/])");
        for (int i = 0; i < tokens.length - 1; i++) {
            if (isOperator(tokens[i]) && isOperator(tokens[i + 1])) {
                throw new IllegalArgumentException("Expression contains consecutive operators");
            }
        }
    }

    private boolean isOperator(String token) {
        return "+-*/".contains(token);
    }
}
