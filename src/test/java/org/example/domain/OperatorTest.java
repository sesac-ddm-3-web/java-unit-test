package org.example.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OperatorTest {

    @DisplayName("[실패] 0으로 나누는 경우 예외가 발생한다.")
    @Test
    public void fail_when_divisor_equals_zero() {
        // when, then
        assertThatThrownBy(() -> {
            Operator.DIVIDE.operate(1, 0);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("나누는 수가 0일 수 없습니다.");
    }

    @DisplayName("[실패] 연산자가 존재하지 않는 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {
        "%",
        "+="
    })
    public void fail_when_divisor_equals_zero(String symbol) {
        // when, then
        assertThatThrownBy(() -> {
            Operator.getBySymbol(symbol);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("존재하지 않는 연산자 입니다.");
    }
}