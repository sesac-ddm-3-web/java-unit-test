package kr.co.calculator.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class OperatorTest {

    @DisplayName("연산자 심볼과 피연산자를 받아 올바른 계산을 수행한다")
    @ParameterizedTest(name = "{0} {1} {2} = {3}")
    @CsvSource({
            "PLUS,     1, 2,   3",
            "MINUS,    5, 3,   2",
            "MULTIPLY, 4, 3,  12",
            "DIVIDE,   8, 4,   2"
    })
    void apply_기본_사칙연산(Operator operator, int left, int right, int expected) {
        int result = operator.apply(left, right);

        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("심볼 문자열에서 올바른 Operator를 조회할 수 있다")
    @ParameterizedTest
    @CsvSource({
            "'+', PLUS",
            "'-', MINUS",
            "'*', MULTIPLY",
            "'/', DIVIDE"
    })
    void from_심볼로_연산자_조회(String symbol, Operator expected) {
        Operator operator = Operator.from(symbol);

        assertThat(operator).isEqualTo(expected);
    }

    @DisplayName("지원하지 않는 연산자 심볼이면 예외가 발생한다")
    @Test
    void from_지원하지_않는_연산자면_예외() {
        assertThatThrownBy(() -> Operator.from("%"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("지원하지 않는 연산자입니다");
    }

    @DisplayName("0으로 나누면 ArithmeticException이 발생한다")
    @Test
    void divide_0으로_나눌_수_없다() {
        assertThatThrownBy(() -> Operator.DIVIDE.apply(4, 0))
                .isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("0으로 나눌 수 없습니다");
    }
}
