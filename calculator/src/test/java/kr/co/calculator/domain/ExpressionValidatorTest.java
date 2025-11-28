package kr.co.calculator.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ExpressionValidatorTest {

    private final ExpressionValidator validator = new ExpressionValidator();

    @Test
    @DisplayName("정상적인 수식은 normalized 문자열을 반환한다")
    void checkNumberAndOper_validExpression_returnsNormalized() {
        // given
        String expression = "2   +   3  - 1   *   3";

        // when
        String normalized = validator.checkNumberAndOper(expression);

        // then
        assertThat(normalized).isEqualTo("2 + 3 - 1 * 3");
    }

    // TODO 1: 입력 값이 null이거나 빈 공백 문자일 경우 exception
    @Test
    @DisplayName("입력 값이 null이면 예외가 발생한다")
    void checkNumberAndOper_null_throwsException() {
        // given
        String expression = null;

        // when & then
        assertThatThrownBy(() -> validator.checkNumberAndOper(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력 계산식은 null이 될 수 없습니다.");
    }

    @Test
    @DisplayName("입력 값이 공백뿐이면 예외가 발생한다")
    void checkNumberAndOper_blank_throwsException() {
        // given
        String expression = "    ";

        // when & then
        assertThatThrownBy(() -> validator.checkNumberAndOper(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력 계산식이 비어 있습니다.");
    }

    @Test
    @DisplayName("입력 값이 공백으로 시작하면 예외가 발생한다")
    void checkNumberAndOper_leadingWhitespace_throwsException() {
        // given
        String expression = " 2 + 3";

        // when & then
        assertThatThrownBy(() -> validator.checkNumberAndOper(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력 계산식이 잘못되었습니다.(공백이 먼저 왔습니다.)");
    }

    // TODO 2: 사칙연산 기호가 아닌 경우 exception
    @Test
    @DisplayName("연산부호가 먼저 올 수 없다")
    void checkNumberAndOper_operatorFirst_throwsException() {
        // given
        String expression = "+ 3";

        // when & then
        assertThatThrownBy(() -> validator.checkNumberAndOper(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("연산부호가 먼저 올 수 없습니다.");
    }

    @Test
    @DisplayName("수식은 연산자로 끝날 수 없다")
    void checkNumberAndOper_operatorLast_throwsException() {
        // given
        String expression = "2 +";

        // when & then
        assertThatThrownBy(() -> validator.checkNumberAndOper(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("연산자와 피연산자의 개수가 맞지 않습니다.");
        // 주의: "2 +" 는 tokens가 ["2","+"]라 길이 2(짝수)라서
        // ensureTokenCountIsOdd에서 먼저 걸린다.
    }

    @Test
    @DisplayName("연산자와 피연산자의 개수가 맞지 않으면 예외")
    void checkNumberAndOper_tokenCountEven_throwsException() {
        // given
        String expression = "2 + 3 +";

        // when & then
        assertThatThrownBy(() -> validator.checkNumberAndOper(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("연산자와 피연산자의 개수가 맞지 않습니다.");
    }

    @Test
    @DisplayName("피연산자 위치에 숫자가 아니면 예외")
    void checkNumberAndOper_nonNumberOperand_throwsException() {
        // given
        String expression = "2 + a";

        // when & then
        assertThatThrownBy(() -> validator.checkNumberAndOper(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("정수만 사용할 수 있습니다: a");
    }

    @Test
    @DisplayName("연산자 위치에 잘못된 토큰이 있으면 예외")
    void checkNumberAndOper_invalidOperatorToken_throwsException() {
        // given
        String expression = "2 ++ 3";

        // when & then
        assertThatThrownBy(() -> validator.checkNumberAndOper(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("연산자 위치에 잘못된 토큰이 있습니다: ++");
    }

    @Test
    @DisplayName("수식은 정수로 시작해야 한다")
    void checkNumberAndOper_firstTokenNotNumber_throwsException() {
        // given
        String expression = "a + 3";

        // when & then
        assertThatThrownBy(() -> validator.checkNumberAndOper(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수식은 정수로 시작해야 합니다: a");
    }
}
