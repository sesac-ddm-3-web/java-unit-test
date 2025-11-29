package org.example;

import java.util.List;

public class StringCalculator {

    private final Tokenizer tokenizer;

    public StringCalculator(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public int calculate(String expression) {
        List<String> tokens = tokenizer.tokenize(expression);
        int result = Integer.parseInt(tokens.get(0));

        for (int i = 1; i < tokens.size(); i += 2) {

            String operatorToken = tokens.get(i);     // 연산자 자리
            String nextNumberToken = tokens.get(i+1); // 숫자 자리

            Operator operator = Operator.of(operatorToken);
            int next = Integer.parseInt(nextNumberToken);

            result = operator.apply(result, next);
        }
        return result;
    }
}
