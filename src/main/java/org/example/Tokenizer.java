package org.example;

import java.util.Arrays;
import java.util.List;

public class Tokenizer {
    private static final String NUMBER_PATTERN = "-?\\d+";

    public List<String> tokenize(String expression) {
        if (expression == null || expression.isBlank()) {
            throw new IllegalArgumentException("입력값이 null 또는 비어 있음");
        }

        List<String> tokens = Arrays.stream(expression.trim().split("\\s+")).toList();

        validate(tokens);

        return tokens;
    }

    private void validate(List<String> tokens){
        if (tokens.isEmpty() || tokens.size() % 2 == 0 ||  tokens.size() < 2) {
            throw new IllegalArgumentException("수식의 개수가 맞지 않습니다.");
        }

        for (int i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);

            //정확한 수식이면 홀수 자리에 연산자가 존재해야함. Operator의 메소드로 검증
            if (i % 2 == 1) {
                Operator.of(token);
            }
            else {
                //정확한 수식이면 짝수 자리에 숫자가 존재해야함.
                if (!token.matches(NUMBER_PATTERN)) {
                    throw new IllegalArgumentException("유효하지 않은 숫자입니다: " + token);
                }
            }
        }
    }
}
