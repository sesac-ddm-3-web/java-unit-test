package org.example.StringCalculator.token;

import org.example.StringCalculator.Operator;

public class OperatorToken implements Token {

    private Operator operator;

    public OperatorToken(Operator operator) {
        this.operator = operator;
    }

    public Operator getOperator() {
        return operator;
    }
}
