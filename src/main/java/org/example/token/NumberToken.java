package org.example.token;

public class NumberToken implements Token {

    private final int value;

    public NumberToken(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
