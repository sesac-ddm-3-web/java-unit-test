package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ExpressionTokenizerTest {

    @Test
    void 유효한_수식을_토큰화한다() {
        // given
        ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer();

        // when
        String[] actual = expressionTokenizer.tokenize("11 + -22 + 3");

        assertThat(actual).containsExactly("11", "+", "-22", "+", "3");
    }

    @Test
    void 음수로_시작하는_수식을_토큰화한다() {
        // given
        ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer();

        // when
        String[] actual = expressionTokenizer.tokenize("-2 + 3");

        // then
        assertThat(actual).containsExactly("-2", "+", "3");
    }

    @ParameterizedTest(name = "수식이 {0} 일 때 토큰화할 수 없다")
    @NullAndEmptySource
    void 수식이_비어_있으면_토큰화_할_수_없다(String expression) {
        // given
        ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer();

        // when & then
        assertThatThrownBy(() -> expressionTokenizer.tokenize(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수식은 비어 있을 수 없습니다.");
    }

    @ParameterizedTest(name = "수식이 {0} 일 때 토큰화할 수 없다")
    @ValueSource(
            strings = {
                    "+ 3 + 4",
                    " + 3 + 4 + 5"
            }
    )
    void 연산자_왼쪽의_피연산자가_없다면_토큰화할_수_없다(String expression) {
        // given
        ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer();

        // when & then
        assertThatThrownBy(() -> expressionTokenizer.tokenize(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("첫 문자는 정수여야 합니다.");
    }

    @ParameterizedTest(name = "수식이 {0} 일 때 토큰화할 수 없다")
    @ValueSource(
            strings = {
                    "2 + 3 +",
                    "2 + 3 + 4 + "
            }
    )
    void 연산자_오른쪽의_피연산자가_없다면_토큰화할_수_없다(String expression) {
        // given
        ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer();

        // when & then
        assertThatThrownBy(() -> expressionTokenizer.tokenize(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("마지막 문자는 정수여야 합니다.");
    }

    @ParameterizedTest(name = "수식이 {0} 일 때 토큰화할 수 없다")
    @ValueSource(
            strings = {
                    "3:+:1",
                    "3  +  1"
            }
    )
    void 주어진_수식의_구분자가_한_칸의_공백이_아니라면_토큰화할_수_없다(String expression) {
        // given
        ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer();

        // when & then
        assertThatThrownBy(() -> expressionTokenizer.tokenize(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수식은 정수, 사칙연산 기호와 구분자로 공백 한 칸만을 허용합니다.");
    }

    @ParameterizedTest(name = "수식이 {0} 일 때 토큰화할 수 없다")
    @ValueSource(
            strings = {
                    "123",
                    "123 +"
            }
    )
    void 주어진_수식의_문자가_두_개_이하라면_토큰화할_수_없다(String expression) {
        // given
        ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer();

        // when & then
        assertThatThrownBy(() -> expressionTokenizer.tokenize(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("적어도 세 개 이상의 토큰으로 된 수식을 입력해야 합니다.");
    }

    @Test
    void 주어진_수식이_정수가_아니라면_토큰화할_수_없다() {
        // given
        ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer();

        // when & then
        assertThatThrownBy(() -> expressionTokenizer.tokenize("1.2 + 3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수식은 정수, 사칙연산 기호와 구분자로 공백 한 칸만을 허용합니다.");
    }

    @ParameterizedTest(name = "수식이 {0} 일 때 토큰화할 수 없다")
    @ValueSource(
            strings = {
                    "1 + 2 3",
                    "1 2 + 3"
            }
    )
    void 피연산자가_연속되면_토큰화할_수_없다(String expression) {
        // given
        ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer();

        // when & then
        assertThatThrownBy(() -> expressionTokenizer.tokenize(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수식은 피연산자와 연산자가 번갈아 나와야 합니다.");
    }

    @ParameterizedTest(name = "수식이 {0} 일 때 토큰화할 수 없다")
    @ValueSource(
            strings = {
                    "1 + * 3",
                    "1 2 + + + 3"
            }
    )
    void 연산자가_연속되면_토큰화할_수_없다(String expression) {
        // given
        ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer();

        // when & then
        assertThatThrownBy(() -> expressionTokenizer.tokenize(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수식은 피연산자와 연산자가 번갈아 나와야 합니다.");
    }
}
