package kr.co.calculator.domain;

public class ExpressionTokenizer {
    private static final String EXPRESSION_REGEX = "^(-?\\d+|[+\\-*/])(?: (-?\\d+|[+\\-*/]))*$";
    private static final int MINIMUM_SEPARATOR_LENGTH = 3;
    private static final String DELIMITER = " ";

    public String[] tokenizeExpression(String expression) {
        validateExpressionBlank(expression);

        String trimmedExpression = trimExpression(expression);

        validateExpressionRegex(trimmedExpression);

        String[] tokens = splitExpression(trimmedExpression);

        validateTokens(tokens);

        return tokens;
    }

    private void validateExpressionBlank(String expression) {
        if (expression == null || expression.isBlank()) {
            throw new IllegalArgumentException("계산식은 비어 있을 수 없습니다.");
        }
    }

    private String trimExpression(String expression) {
        return expression.trim();
    }

    // === trimmed ===
    private void validateExpressionRegex(String trimmedExpression) {
        if (!trimmedExpression.matches(EXPRESSION_REGEX)) {
            throw new IllegalArgumentException("계산식에는 정수, 사칙연산 기호와 공백 한 칸만을 허용합니다.");
        }
    }

    private String[] splitExpression(String trimmedExpression) {
        return trimmedExpression.split(DELIMITER);
    }

    private void validateTokens(String[] tokens) {
        if (tokens.length == 1) {
            if (!isNumber(tokens[0])) {
                throw new IllegalArgumentException("단일 항은 정수여야 합니다. token=" + tokens[0]);
            }

            return; // 숫자 하나이므로 아래 검증은 필요없음
        }

        validateTokenLength(tokens);
        validateTokenOrder(tokens);
    }

    private void validateTokenLength(String[] tokens) {
        if (tokens.length < MINIMUM_SEPARATOR_LENGTH || tokens.length % 2 == 0) {
            throw new IllegalArgumentException("수식은 피연산자와 연산자가 번갈아 나오는 형태여야 합니다.");
        }
    }

    private void validateTokenOrder(String[] tokens) {
        for (int i = 0; i < tokens.length; i++) {
            validateToken(tokens[i], i);
        }
    }

    private void validateToken(String token, int index) {
        if (index % 2 == 0) { // 짝수 = 숫자 위치
            if (!isNumber(token)) { // 숫자가 아니라면
                throw new IllegalArgumentException("피연산자 위치에는 정수가 와야 합니다. token=" + token);
            }
            
            return;
        }

        // 홀수 = 연산자 위치
        if (!isOperator(token)) { // 연산자가 아니라면
            throw new IllegalArgumentException("연산자 위치에는 사칙연산 기호만 올 수 있습니다. token=" + token);
        }
    }

    private boolean isNumber(String token) {
        return token.matches("-?\\d+");
    }

    private boolean isOperator(String token) {
        return token.matches("[+\\-*/]");
    }
}
