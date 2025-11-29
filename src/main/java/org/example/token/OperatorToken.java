package org.example.token;

public class OperatorToken implements Token {

    private final Operator operator;

    public OperatorToken(Operator operator) {
        this.operator = operator;
    }

    public Operator operator() {
        return operator;
    }
}
