package org.example.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CalculatorTest {

    private Calculator calculator = new Calculator();

    @DisplayName("[성공] 올바른 수식을 넣은 경우 계산에 성공한다.")
    @ParameterizedTest
    @CsvSource(
        value = {
            "2 + 3 * 4 / 2=10",
            "10 * 2 - 5=15"
        }, delimiter = '=')
    public void success_when_correct_expression(String expression, int result) {
        // given
        // when
        double actual = calculator.calculate(expression);

        // then
        assertThat(actual).isEqualTo(result);
    }

    @DisplayName("[실패] 수식이 null이거나 비어있는 경우 예외가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    public void fail_when_expression_is_not_blank_or_null(String expression) {
        // when, then
        assertThatThrownBy(() -> {
            calculator.calculate(expression);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("수식이 비어있습니다.");
    }


    @DisplayName("[실패] 유효하지 않은 수식인 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {
        "1 + 2 -"
    })
    public void fail_when_expression_is_not_valid(String expression) {
        // when, then
        assertThatThrownBy(() -> {
            calculator.calculate(expression);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("유효하지 않은 수식입니다.");
    }

    @DisplayName("[실패] 피연산자가 숫자가 아닌 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {
        "a + b",
        "# + &"
    })
    public void fail_when_operand_is_not_valid(String expression) {
        // when, then
        assertThatThrownBy(() -> {
            calculator.calculate(expression);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("피연산자가 숫자가 아닙니다.");
    }
}