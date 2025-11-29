package org.example.StringCalculator;

public class Expression {
    private final String value;

    public Expression(String value) {
        validate(value);
        this.value = value.replaceAll("\\s+", "");
    }

    private void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("수식은 비어있을 수 없습니다.");
        }
    }

    public String getValue() {
        return value;
    }
}
