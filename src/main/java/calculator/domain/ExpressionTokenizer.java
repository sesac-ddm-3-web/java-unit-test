package calculator.domain;

public class ExpressionTokenizer {

    private static final String EXPRESSION_REGEX = "^(-?\\d+|[+\\-*/])(?: (-?\\d+|[+\\-*/]))*$";
    private static final int MINIMUM_SEPARATOR_LENGTH = 3;
    private static final String DELIMITER = " ";

    public String[] tokenize(String expression) {
        validateExpressionBlank(expression);

        String targetExpression = expression.trim();

        validateExpressionRegex(targetExpression);
        
        String[] tokens = targetExpression.split(DELIMITER);

        validateTokens(tokens);

        return tokens;
    }

    private void validateExpressionBlank(String expression) {
        if (expression == null || expression.isBlank()) {
            throw new IllegalArgumentException("수식은 비어 있을 수 없습니다.");
        }
    }

    private void validateExpressionRegex(String expression) {
        if (!expression.matches(EXPRESSION_REGEX)) {
            throw new IllegalArgumentException("수식은 정수, 사칙연산 기호와 구분자로 공백 한 칸만을 허용합니다.");
        }
    }

    private void validateTokens(String[] tokens) {
        validateTokenLength(tokens);
        validateTokenOrder(tokens);
    }

    private void validateTokenLength(String[] tokens) {
        if (isInvalidLength(tokens)) {
            throw new IllegalArgumentException("적어도 세 개 이상의 토큰으로 된 수식을 입력해야 합니다.");
        }
    }

    private boolean isInvalidLength(String[] tokens) {
        return tokens.length < MINIMUM_SEPARATOR_LENGTH;
    }

    private void validateTokenOrder(String[] tokens) {
        for (int i = 0; i < tokens.length; i++) {
            TokenValidator tokenValidator = TokenValidator.find(i);

            tokenValidator.validate(tokens[i], i, tokens.length);
        }
    }
}
