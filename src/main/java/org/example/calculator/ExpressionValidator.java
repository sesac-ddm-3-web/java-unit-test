package org.example.calculator;

import java.util.regex.Pattern;

public class ExpressionValidator implements Validator{
    private static final Pattern EXPRESSION_PATTERN =
            Pattern.compile("^\\d+\\s*([+\\-*/]\\s*\\d+\\s*)*$");

    @Override
    public void validate(String input) {
        validateNotBlank(input);
        validateExpressionPattern(input);
    }

    private void validateNotBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("비어있는 값은 허용되지 않습니다.");
        }
    }

    private void validateExpressionPattern(String input) {
        if(!EXPRESSION_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("유효하지 않은 수식 형식입니다.");
        }
    }
}
