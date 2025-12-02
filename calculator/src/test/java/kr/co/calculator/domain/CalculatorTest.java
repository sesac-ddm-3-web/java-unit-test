package kr.co.calculator.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class CalculatorTest {
    private final Calculator calculator = new Calculator(new ExpressionTokenizer());

    @DisplayName("단일 숫자 계산식은 그대로 반환된다")
    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "2, 2",
            "-3, -3",
            "100, 100"
    })
    void calculate_단일_숫자(String expression, int expected) {
        Integer result = calculator.calculate(expression);

        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("여러 연산이 포함된 계산식을 왼쪽에서 오른쪽 순서대로 계산한다 (우선순위 무시)")
    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource({
            "'1 + 2',                3",
            "'4 - 2',                2",
            "'2 * 3',                6",
            "'8 / 4',                2",
            "'2 + 3 * 4',           20",   // ((2 + 3) * 4)
            "'2 + 3 * -4',         -20",   // ((2 + 3) * -4)
            "'2 + 3 * -4 / 2',     -10"    // (((2 + 3) * -4) / 2)
    })
    void calculate_사칙연산_순차_계산(String expression, int expected) {
        Integer result = calculator.calculate(expression);

        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("0으로 나누는 계산식은 예외가 발생한다")
    @Test
    void calculate_0으로_나누면_예외() {
        assertThatThrownBy(() -> calculator.calculate("1 / 0"))
                .isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("0으로 나눌 수 없습니다");
    }

    @DisplayName("형식이 잘못된 계산식은 토크나이저에서 예외가 발생한다")
    @ParameterizedTest
    @CsvSource({
            "'1 +'",       // 토큰 개수 부족
            "'1 2 3'",     // 숫자 숫자 숫자
            "'1 + a'",     // 알파벳 포함
            "'+'",         // 단일 연산자
            "'1  +  2'"    // 공백 두 칸
    })
    void calculate_잘못된_계산식은_예외(String expression) {
        assertThatThrownBy(() -> calculator.calculate(expression))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
