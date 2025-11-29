package org.example.token;

public enum Operator {
    PLUS("+") {
        @Override
        public int apply(int left, int right) {
            return left + right;
        }
    },
    MINUS("-") {
        @Override
        public int apply(int left, int right) {
            return left - right;
        }
    },
    MULTIPLY("*") {
        @Override
        public int apply(int left, int right) {
            return left * right;
        }
    },
    DIVIDE("/") {
        @Override
        public int apply(int left, int right) {
            if (right == 0) {
                throw new ArithmeticException("0으로 나눌 수 없습니다.");
            }
            return left / right;
        }
    };

    private final String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    public abstract int apply(int left, int right);

    public static Operator fromSymbol(String symbol) {
        for (Operator op : values()) {
            if (op.symbol.equals(symbol)) {
                return op;
            }
        }
        throw new IllegalArgumentException("지원하지 않는 연산자 : " + symbol);
    }
}