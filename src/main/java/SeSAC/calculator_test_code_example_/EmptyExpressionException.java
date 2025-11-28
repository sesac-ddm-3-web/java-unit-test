package SeSAC.calculator_test_code_example_;

public class EmptyExpressionException extends RuntimeException {
    public EmptyExpressionException() {
        super("입력된 식이 비어있습니다.");
    }
}
