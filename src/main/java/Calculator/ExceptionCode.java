package Calculator;

public enum ExceptionCode {
    INVALID_INPUT("입력값이 비어있거나 null입니다."),
    INVALID_OPERATOR("잘못된 사칙연산 기호입니다."),
    DIVIDE_BY_ZERO("0으로 나눌 수 없습니다.");

    private final String message;

    ExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
