package org.example.calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    @Test
    void 단일_숫자가_들어온다면_그대로_반환한다 () {
        List<String> exrpession = List.of("5");
        int expected = 5;

        Calculator calculator = new Calculator(exrpession);
        int result = calculator.calculate();

        assertThat(result).isEqualTo(expected);

    }

    @ParameterizedTest
    @MethodSource("provideExpressionsAndResults")
    void 올바른_수식에는_올바른_결과를_반환한다(List<String> expression, int expected) {
        Calculator calculator = new Calculator(expression);

        int result = calculator.calculate();

        assertThat(result).isEqualTo(expected);
    }


    static Stream<Arguments> provideExpressionsAndResults() {
        return Stream.of(
                Arguments.of(List.of("3"), 3),
                Arguments.of(List.of("3", "+", "4"), 7),
                Arguments.of(List.of("10", "-", "3"), 7),
                Arguments.of(List.of("4", "*", "5"), 20),
                Arguments.of(List.of("20", "/", "4"), 5),
                Arguments.of(List.of("3", "+", "4", "*", "5"), 35),
                Arguments.of(List.of("10", "/", "2", "+", "3"), 8),
                Arguments.of(List.of("5", "*", "3", "-", "2", "+", "1"), 14),
                Arguments.of(List.of("7","-","22","+","30","*","7","/","7"),15)
        );
    }



}