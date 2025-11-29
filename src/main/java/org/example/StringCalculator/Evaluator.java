package org.example.StringCalculator;

import org.example.StringCalculator.token.NumberToken;
import org.example.StringCalculator.token.OperatorToken;
import org.example.StringCalculator.token.Token;

import java.util.List;

public class Evaluator {

    public int evaluate(List<Token> tokens) {
        if (!(tokens.get(0) instanceof NumberToken first)) {
            throw new IllegalArgumentException("연산은 숫자로 시작해야 합니다.");
        }

        double answer = first.getValue();

        for (int i = 1; i < tokens.size(); i += 2) {
            if (i + 1 >= tokens.size()) {
                throw new IllegalArgumentException("연산식이 잘못되었습니다 (숫자가 부족함).");
            }

            if (!(tokens.get(i) instanceof OperatorToken operatorToken)) {
                throw new IllegalArgumentException("인덱스 " + i + "는 연산자여야 합니다.");
            }
            if (!(tokens.get(i + 1) instanceof NumberToken numberToken)) {
                throw new IllegalArgumentException("인덱스 " + (i + 1) + "는 숫자여야 합니다.");
            }

            double nextValue = numberToken.getValue();
            answer = operatorToken.getOperator().apply(answer, nextValue);
        }

        checkOverflow(answer);
        return (int) answer;
    }

    private void checkOverflow(double result) {
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            throw new ArithmeticException("연산 결과가 int 범위를 초과했습니다 (Overflow): " + result);
        }
    }
}
