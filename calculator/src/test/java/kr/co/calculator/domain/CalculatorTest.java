package kr.co.calculator.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    @DisplayName("단순 덧셈 수식을 계산한다")
    void calculate_simpleAddition() {
        // given
        String expression = "2 + 3";

        // when
        Integer result = calculator.calculate(expression);

        // then
        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("여러 연산이 섞인 수식을 왼쪽부터 차례대로 계산한다")
    void calculate_multipleOperations_leftToRight() {
        // given
        String expression = "2 + 3 - 1 * 3";
        // 계산 과정: ((2 + 3) - 1) * 3 = (5 - 1) * 3 = 4 * 3 = 12

        // when
        Integer result = calculator.calculate(expression);

        // then
        assertThat(result).isEqualTo(12);
    }

    @Test
    @DisplayName("정수 나눗셈을 수행한다")
    void calculate_integerDivision() {
        // given
        String expression = "10 / 3";

        // when
        Integer result = calculator.calculate(expression);

        // then
        assertThat(result).isEqualTo(3); // 정수 나눗셈
    }

    @Test
    @DisplayName("0으로 나누면 ArithmeticException을 던진다")
    void calculate_divideByZero_throwsException() {
        // given
        String expression = "5 / 0";

        // when & then
        assertThatThrownBy(() -> calculator.calculate(expression))
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("0으로 나눌 수 없습니다.");
    }

    @Test
    @DisplayName("음수 결과도 정상적으로 계산된다")
    void calculate_negativeResult() {
        // given
        String expression = "2 - 5";

        // when
        Integer result = calculator.calculate(expression);

        // then
        assertThat(result).isEqualTo(-3);
    }
}
