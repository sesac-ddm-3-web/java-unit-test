package org.example.calculator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


class OperatorTest {
    @ParameterizedTest
    @CsvSource(value = {
            "1, 3, 4",
            "3, 3, 6",
            "1, 0, 1"
    })
    void 덧셈_연산_테스트(int a, int b, int expected) {
        assertThat(Operator.PLUS.calculate(a, b)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "3, 2, 1",
        "1, 0, 1",
        "8, 3, 5"
    })
    void 뺼셈_연산_테스트(int a, int b, int expected) {
        assertThat(Operator.MINUS.calculate(a, b)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "3, 2, 6",
            "1, 0, 0",
            "8, 3, 24"
    })
    void 곱셈_연산_테스트(int a, int b, int expected) {
        assertThat(Operator.MULTIPLY.calculate(a, b)).isEqualTo(expected);
    }


    @ParameterizedTest
    @CsvSource(value = {
            "5, 1, 5",
            "12, 3, 4",
            "22, 11, 2",
            "0, 3, 0"
    })
    void 나눗셈_연산_테스트(int a, int b, int expected) {
        assertThat(Operator.DIVIDE.calculate(a, b)).isEqualTo(expected);
    }


    @ParameterizedTest
    @ValueSource(ints = {1,3,5,7,11,1234})
    void 분모가_0일_경우_예외를_던진다(int value) {
        int zero = 0;

        assertThatThrownBy(() -> Operator.DIVIDE.calculate(value, zero))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("0으로 나눌 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "+, PLUS",
            "-, MINUS",
            "*, MULTIPLY",
            "/, DIVIDE"
    })
    void 문자열로_연산자를_찾는다(String symbol, Operator expected) {
        assertThat(Operator.fromSymbol(symbol)).isEqualTo(expected);
    }

    @Test
    void 잘못된_연산자_기호는_예외를_던진다() {
        assertThatThrownBy(() -> Operator.fromSymbol("&"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 형식");
    }
}
