package org.example.StringCalculator;

import org.example.StringCalculator.token.NumberToken;
import org.example.StringCalculator.token.Token;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    public List<Token> tokenize(Expression expression) {
        ExpressionReader reader = new ExpressionReader(expression.getValue());
        List<Token> tokens = new ArrayList<>();

        while (reader.hasMoreTokens()) {
            char current = reader.peek();

            if (Character.isDigit(current)) {
                tokens.add(parseNumber(reader));
                continue;
            }

            if (Operator.isOperator(String.valueOf(current))) {
                tokens.add(parseOperator(reader));
                continue;
            }

            throw new IllegalArgumentException("유효하지 않은 문자입니다: '" + current + "' 위치: " + reader.currentIndex());
        }

        return tokens;
    }

    private Token parseNumber(ExpressionReader reader) {
        StringBuilder sb = new StringBuilder();

        while (reader.hasMoreTokens() && Character.isDigit(reader.peek())) {
            sb.append(reader.peek());
            reader.next();
        }

        try {
            int value = Integer.parseInt(sb.toString());
            return new NumberToken(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 범위가 int을 초과했습니다: " + sb.toString());
        }
    }

    private Token parseOperator(ExpressionReader reader) {
        char symbol = reader.peek();
        reader.next();
        return Operator.from(String.valueOf(symbol)).getToken();
    }

    private static class ExpressionReader {
        private final String expression;
        private int idx;

        public ExpressionReader(String expression) {
            this.expression = expression;
            this.idx = 0;
        }

        public boolean hasMoreTokens() {
            return idx < expression.length();
        }

        public char peek() {
            if (!hasMoreTokens()) {
                throw new IllegalStateException("더 이상 읽을 수 있는 문자가 없습니다.");
            }
            return expression.charAt(idx);
        }

        public void next() {
            if (hasMoreTokens()) {
                idx++;
            }
        }

        public int currentIndex() {
            return idx;
        }
    }
}
