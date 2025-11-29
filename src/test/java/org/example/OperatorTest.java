package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
class OperatorTest {

    @ParameterizedTest
    @CsvSource(value = {
            "+:PLUS",
            "-:MINUS",
            "*:MULTIPLY",
            "/:DIVIDE"
    }, delimiter = ':')
    @DisplayName("기호에 맞게 연산자를 고른다.")
    void find_operator(String symbol, Operator expected) {
        Operator actual = Operator.of(symbol);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"&", "a", "++", " "})
    @DisplayName("올바르지 않은 기호가 들어오면 예외가 발생한다")
    void find_operator_fail(String invalidSymbol) {
        assertThatThrownBy(() -> Operator.of(invalidSymbol))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 연산자입니다");
    }

    @Test
    @DisplayName("null이 들어오면 예외가 발생한다")
    void find_operator_null() {
        assertThatThrownBy(() -> Operator.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 연산자입니다");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "+:2:3:5",
            "-:6:5:1",
            "*:3:4:12",
            "/:5:2:2"
    }, delimiter = ':')
    @DisplayName("각 연산이 정상 수행된다.")
    void goodCalculate(String symbol, int operand1, int operand2, int expected) {
        Operator operator = Operator.of(symbol);
        int result = operator.apply(operand1, operand2);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("0으로 나누면 예외가 발생해야 한다")
    void divide_by_zero() {
        // given
        Operator divide = Operator.DIVIDE;

        // when & then
        assertThatThrownBy(() -> divide.apply(10, 0))
                .isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("0으로 나눌 수 없습니다.");
    }
}