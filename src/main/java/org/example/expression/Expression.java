package org.example.expression;

import org.example.token.NumberToken;
import org.example.token.OperatorToken;
import org.example.token.Token;

import java.util.ArrayList;
import java.util.List;

public class Expression {

    private final List<Token> tokens = new ArrayList<>();

    public void addToken(Token token) {
        tokens.add(token);
    }

    public int evaluate() {
        validateToken();
        int result = ((NumberToken) tokens.get(0)).value();
        for (int i = 1; i < tokens.size(); i += 2) {
            OperatorToken operatorToken = (OperatorToken) tokens.get(i);
            NumberToken numberToken = (NumberToken) tokens.get(i + 1);
            result = operatorToken.operator().apply(result, numberToken.value());
        }
        return result;
    }

    private void validateToken() {
        if (tokens.isEmpty()) {
            throw new IllegalStateException("토큰이 없습니다.");
        }
    }
}
