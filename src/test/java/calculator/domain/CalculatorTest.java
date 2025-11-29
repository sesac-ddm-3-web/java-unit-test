package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CalculatorTest {

    private static Stream<Arguments> calculateSuccessTestArguments() {
        return Stream.of(
                Arguments.of("2 + 3 * 4 / 2", BigDecimal.valueOf(10.0d)),
                Arguments.of("5 + 7 + 2 - 1 - 7 + 9 / 2 * 5 / 7 + 5", BigDecimal.valueOf(10.357d)),
                Arguments.of("5 + -2", BigDecimal.valueOf(3.0d)),
                Arguments.of("-2 + 3 * 4", BigDecimal.valueOf(4.0d))
        );
    }

    @ParameterizedTest(name = "수식이 {0} 일 때 {1}로 계산한다")
    @MethodSource("calculateSuccessTestArguments")
    void 유효한_수식이_주어졌을_때_사칙연산_우선_순위와_무관하게_입력_순서에_맞춰_계산한다(String expression, BigDecimal expected) {
        // given
        ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer();
        Calculator calculator = new Calculator(expressionTokenizer);

        // when
        BigDecimal actual = calculator.calculate(expression);

        // then
        assertThat(actual).isEqualByComparingTo(expected);
    }

    private static Stream<Arguments> calculateBigNumberTestArguments() {
        return Stream.of(
                Arguments.of("2147483647 + 2147483647", new BigDecimal(4_294_967_294L)),
                Arguments.of("-2147483648 + -2147483648", new BigDecimal(-4_294_967_296L)),
                Arguments.of("-2147483648 * -2147483648", new BigDecimal(4_611_686_018_427_387_904L)),
                Arguments.of("2147483647 * 2147483647", new BigDecimal(4_611_686_014_132_420_609L))
        );
    }

    @ParameterizedTest(name = "수식이 {0} 일 때 {1}을 계산한다")
    @MethodSource("calculateBigNumberTestArguments")
    void int_범위_이상의_수식을_계산한다(String expression, BigDecimal expected) {
        // given
        ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer();
        Calculator calculator = new Calculator(expressionTokenizer);

        // when
        BigDecimal actual = calculator.calculate(expression);

        // then
        assertThat(actual).isEqualByComparingTo(expected);
    }
}
