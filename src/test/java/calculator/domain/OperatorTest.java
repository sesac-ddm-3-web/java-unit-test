package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OperatorTest {

    private static Stream<Arguments> findMethodTestArguments() {
        return Stream.of(
                Arguments.of("+", Operator.PLUS),
                Arguments.of("-", Operator.MINUS),
                Arguments.of("*", Operator.MULTIPLE),
                Arguments.of("/", Operator.DIVIDE)
        );
    }

    @ParameterizedTest(name = "기호가 {0} 일 때 {1}을 찾는다")
    @MethodSource("findMethodTestArguments")
    void 유효한_사칙연산_기호_문자열에_해당하는_Operator를_조회한다(String name, Operator expected) {
        // when
        Operator actual = Operator.find(name);

        // then
        assertThat(actual).isSameAs(expected);
    }

    @Test
    void 유효한_사칙연산_기호가_아니라면_Operator를_조회할_수_없다() {
        // when & then
        assertThatThrownBy(() -> Operator.find("1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("연산자를 찾을 수 없습니다.");
    }

    @Test
    void 두_수를_더한다() {
        // given
        Operator plusOperator = Operator.PLUS;

        // when
        BigDecimal actual = plusOperator.calculate(BigDecimal.ONE, BigDecimal.valueOf(2));

        // then
        assertThat(actual).isEqualByComparingTo(BigDecimal.valueOf(3));
    }

    @Test
    void 두_수를_뺀다() {
        // given
        Operator minusOperator = Operator.MINUS;

        // when
        BigDecimal actual = minusOperator.calculate(BigDecimal.ONE, BigDecimal.valueOf(2));

        // then
        assertThat(actual).isEqualByComparingTo(BigDecimal.valueOf(-1));
    }

    @Test
    void 두_수를_곱한다() {
        // given
        Operator multipleOperator = Operator.MULTIPLE;

        // when
        BigDecimal actual = multipleOperator.calculate(BigDecimal.ONE, BigDecimal.valueOf(2));

        // then
        assertThat(actual).isEqualByComparingTo(BigDecimal.valueOf(2));
    }

    @Test
    void 계산_결과가_정수가_나오는_두_수를_나눈다() {
        // given
        Operator divideOperator = Operator.DIVIDE;

        // when
        BigDecimal actual = divideOperator.calculate(BigDecimal.valueOf(4), BigDecimal.valueOf(2));

        // then
        assertThat(actual).isEqualByComparingTo(BigDecimal.valueOf(2));
    }

    @Test
    void 계산_결과가_실수가_나오는_두_수를_나눈다() {
        // given
        Operator divideOperator = Operator.DIVIDE;

        // when
        BigDecimal actual = divideOperator.calculate(BigDecimal.valueOf(5), BigDecimal.valueOf(2));

        // then
        assertThat(actual).isEqualByComparingTo(BigDecimal.valueOf(2.5));
    }

    private static Stream<Arguments> calculateRoundTestArguments() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(7), BigDecimal.valueOf(16), BigDecimal.valueOf(0.438)), // 7 ÷ 16 = 0.4375
                Arguments.of(BigDecimal.valueOf(1), BigDecimal.valueOf(3), BigDecimal.valueOf(0.333)) // 1 ÷ 3 = 0.3333...
        );
    }

    @ParameterizedTest(name = "수식이 {0}일 때 {1}을 계산한다")
    @MethodSource("calculateRoundTestArguments")
    void 계산_결과가_네_자리_이상의_소수점이_나오는_경우_소수점_네_자릿수에서_반올림을_수행한다(BigDecimal a, BigDecimal b, BigDecimal expected) {
        // given
        Operator divideOperator = Operator.DIVIDE;

        // when
        BigDecimal actual = divideOperator.calculate(a, b);

        // then
        assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    void 나눗셈_연산_시_0으로_나눌_수_없다() {
        // given
        Operator divideOperator = Operator.DIVIDE;

        // when & then
        assertThatThrownBy(() -> divideOperator.calculate(BigDecimal.valueOf(7), BigDecimal.ZERO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("0으로 나눌 수 없습니다.");
    }
}
