package org.example.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Parser {
    private static final Pattern EXPRESSION_PATTERN =
            Pattern.compile("^(\\d+([+\\-*/]\\d+)*)$");
    public static List<String> parsing (String formula) {
        List<String> result = new ArrayList<>();

        return result;
    }

    private void validateNotBlank(String input) {
        if(input == null || input.isBlank()) {
            throw new RuntimeException("입력값은 비어있을 수 없습니다.");
        }
    }

    private void validateNotExpression(String input) {

    }
}
