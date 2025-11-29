package org.example.expression;

import org.example.token.NumberToken;
import org.example.token.Operator;
import org.example.token.OperatorToken;
import org.example.token.Token;

public class SimpleExpressionParser implements ExpressionParser {

    private static final String NORMALIZE_PATTERN = "([+\\-*/])";

    @Override
    public Expression parse(String input) {
        String normalized = input.replaceAll(NORMALIZE_PATTERN, " $1 ").trim();
        String[] pieces = normalized.split("\\s+");
        validateExpression(input, pieces);
        Token first = toNumberToken(pieces[0]);
        Expression expression = new Expression();
        expression.addToken(first);
        for (int i = 1; i < pieces.length; i += 2) {
            Token opToken = toOperatorToken(pieces[i]);
            Token numToken = toNumberToken(pieces[i + 1]);
            expression.addToken(opToken);
            expression.addToken(numToken);
        }
        return expression;
    }

    private void validateExpression(String input, String[] pieces) {
        if (pieces.length % 2 == 0) {
            throw new IllegalArgumentException("잘못된 수식 형식입니다 : " + input);
        }
    }

    private NumberToken toNumberToken(String token) {
        try {
            int value = Integer.parseInt(token);
            return new NumberToken(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닙니다 : " + token);
        }
    }

    private OperatorToken toOperatorToken(String token) {
        Operator op = Operator.fromSymbol(token);
        return new OperatorToken(op);
    }
}
