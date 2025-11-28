package SeSAC.calculator_test_code_example_;

public enum Operator {
    PLUS("+"){
        @Override
        public double calculate(double opd1, double opd2){ return opd1 + opd2;}
    },
    MINUS("-"){
        @Override
        public double calculate(double opd1, double opd2){ return opd1 - opd2;}
    },
    MULTIPLE("*"){
        @Override
        public double calculate(double opd1, double opd2){ return opd1 * opd2;}
    },
    DIVIDE("/"){
        @Override
        public double calculate(double opd1, double opd2){
            if (opd2 == 0) throw new ArithmeticException("0으로 나눌 수 없습니다.");
            return opd1 / opd2;}
    };

    private final String operatorSymbol;
    Operator(String operatorSymbol) {
        this.operatorSymbol = operatorSymbol;
    }

    public static Operator findBySymbol(String token) {
        for (Operator operator : values()) {
            if (operator.operatorSymbol.equals(token)) {
                return operator;
            }
        }
        //아마 예외 발생은 안할 거 같지만 빨간줄 없애기 위해 예외 던지도록 작성
        throw new IllegalArgumentException("지원하지 않는 연산자입니다.");
    }

    public abstract double calculate(double opd1, double opd2);
}
