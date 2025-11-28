package kr.co.calculator.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    // default delimiter 는 ','인 것 같다
    @ParameterizedTest
    @DisplayName("단순 덧셈 수식을 계산한다")
    @CsvSource({
            "'2 + 3', 5",
            "'1 + 4', 5",
            "'10 + 0', 10"
    })
    void calculate_simpleAddition(String expression, int expected) {
        // when
        Integer result = calculator.calculate(expression);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("여러 연산이 섞인 수식을 왼쪽부터 차례대로 계산한다")
    @CsvSource({
            // ((2 + 3) - 1) * 3 = 12
            "'2 + 3 - 1 * 3', 12",
            // ((10 - 2) * 3) = 24
            "'10 - 2 * 3', 24",
            // ((5 * 2) - 3) = 7
            "'5 * 2 - 3', 7"
    })
    void calculate_multipleOperations_leftToRight(String expression, int expected) {
        // when
        Integer result = calculator.calculate(expression);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("정수 나눗셈을 수행한다 (버림)")
    @CsvSource({
            "'10 / 3', 3",
            "'9 / 2', 4",
            "'8 / 4', 2"
    })
    void calculate_integerDivision(String expression, int expected) {
        // when
        Integer result = calculator.calculate(expression);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("0으로 나누면 ArithmeticException을 던진다")
    @CsvSource({
            "'5 / 0'",
            "'10 / 0'",
            "'1 + 2 / 0'"     // ((1 + 2) / 0) 형태
    })
    void calculate_divideByZero_throwsException(String expression) {
        // when & then
        assertThatThrownBy(() -> calculator.calculate(expression))
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("0으로 나눌 수 없습니다.");
    }

    @ParameterizedTest
    @DisplayName("음수 결과도 정상적으로 계산된다")
    @CsvSource({
            "'2 - 5', -3",
            "'0 - 3', -3",
            "'1 - 10', -9"
    })
    void calculate_negativeResult(String expression, int expected) {
        // when
        Integer result = calculator.calculate(expression);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
