package kr.co.calculator.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class ExpressionTokenizerTest {
    private final ExpressionTokenizer tokenizer = new ExpressionTokenizer();

    @DisplayName("단일 정수 문자열은 하나의 토큰으로 반환된다")
    @ParameterizedTest
    @ValueSource(strings = {"0", "2", "-3", "100"})
    void tokenize_단일_정수(String expression) {
        String[] tokens = tokenizer.tokenizeExpression(expression);

        assertThat(tokens)
                .hasSize(1)
                .containsExactly(expression);
    }

    @DisplayName("유효한 계산식은 공백을 기준으로 토큰화된다")
    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("validExpressionProvider")
    void tokenize_유효한_계산식(String expression, String[] expectedTokens) {
        String[] tokens = tokenizer.tokenizeExpression(expression);

        assertThat(tokens).containsExactly(expectedTokens);
    }
    
    static Stream<Arguments> validExpressionProvider() {
        return Stream.of(
                Arguments.of("1 + 2", new String[]{"1", "+", "2"}),
                Arguments.of("-1 + 2", new String[]{"-1", "+", "2"}),
                Arguments.of("1 + 2 * 3", new String[]{"1", "+", "2", "*", "3"}),
                Arguments.of("1 + 2 * -3", new String[]{"1", "+", "2", "*", "-3"})
        );
    }

    @DisplayName("null 이나 공백만 있는 계산식은 예외가 발생한다")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   "})
    void tokenize_비어있는_계산식이면_예외(String expression) {
        assertThatThrownBy(() -> tokenizer.tokenizeExpression(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("계산식은 비어 있을 수 없습니다.");
    }

    @DisplayName("정수, 연산자, 공백 한 칸 이외의 문자가 포함되면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {
            "1  +  2", // 공백 두 칸
            "1 + a", // 알파벳
            "1+2", // 공백 없음
            "1 + 2\t+ 3" // 탭 포함
    })
    void tokenize_허용되지_않는_형식이면_예외(String expression) {
        assertThatThrownBy(() -> tokenizer.tokenizeExpression(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("계산식에는 정수, 사칙연산 기호와 공백 한 칸만을 허용합니다.");
    }

    @DisplayName("토큰 개수가 규칙에 맞지 않으면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {
            "1 +", // 2개
            "1 + 2 + 3 +" // 짝수 개
    })
    void tokenize_토큰_개수가_잘못되면_예외(String expression) {
        assertThatThrownBy(() -> tokenizer.tokenizeExpression(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("계산식은 피연산자와 연산자가 번갈아 나오는 형태여야 합니다.");
    }

    @DisplayName("숫자/연산자 순서가 잘못되면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {
            "1 2 3",       // 숫자 숫자 숫자
            "+ 1 +",       // 연산자 숫자 연산자
            "1 + + 2",     // 숫자 연산자 연산자 숫자
            "* 1 2",       // 연산자 숫자 숫자
    })
    void tokenize_토큰_순서가_잘못되면_예외(String expression) {
        assertThatThrownBy(() -> tokenizer.tokenizeExpression(expression))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("단일 토큰이 연산자이면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"+", "-", "*", "/"})
    void tokenize_단일_연산자는_허용되지_않는다(String expression) {
        assertThatThrownBy(() -> tokenizer.tokenizeExpression(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("단일 항은 정수여야 합니다");
    }
}
