package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TokenValidatorTest {

    @Test
    void 유효한_토큰_인덱스가_아니라면_검증기를_반환할_수_없다() {
        // when & then
        assertThatThrownBy(() -> TokenValidator.findValidator(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 인덱스가 아닙니다.");
    }

    @Test
    void 첫_인덱스에서는_정수_검증기를_반환한다() {
        // when
        TokenValidator actual = TokenValidator.findValidator(0);

        // then
        assertThat(actual).isSameAs(TokenValidator.NUMBER);
    }

    @Test
    void 마지막_인덱스에서는_정수_검증기를_반환한다() {
        // when
        TokenValidator actual = TokenValidator.findValidator(4);

        // then
        assertThat(actual).isSameAs(TokenValidator.NUMBER);
    }

    @Test
    void 연산자_인덱스에서는_사칙연산_검증기를_반환한다() {
        // when
        TokenValidator actual = TokenValidator.findValidator(1);

        // then
        assertThat(actual).isSameAs(TokenValidator.OPERATOR);
    }

    @Test
    void 첫_토큰이_정수가_아니라면_유효한_수식이_아니다() {
        // given
        TokenValidator numberValidator = TokenValidator.findValidator(0);

        // when & then
        assertThatThrownBy(() -> numberValidator.validate("+", 0, 3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("첫 문자는 정수여야 합니다.");
    }

    @Test
    void 마지막_토큰이_정수가_아니라면_유효한_수식이_아니다() {
        // given
        TokenValidator numberValidator = TokenValidator.findValidator(2);

        // when & then
        assertThatThrownBy(() -> numberValidator.validate("+", 2, 3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("마지막 문자는 정수여야 합니다.");
    }

    @Test
    void 연산자_자리에_피연산자가_오면_유효한_수식이_아니다() {
        // given
        TokenValidator operatorValidator = TokenValidator.findValidator(1);

        // when & then
        assertThatThrownBy(() -> operatorValidator.validate("3", 1, 3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수식은 피연산자와 연산자가 번갈아 나와야 합니다.");
    }

    @Test
    void 유효한_토큰을_검증한다() {
        // given
        TokenValidator firstNumberValidator = TokenValidator.findValidator(0);
        TokenValidator operatorValidator = TokenValidator.findValidator(1);
        TokenValidator lastNumberValidator = TokenValidator.findValidator(2);

        // when & then
        assertThatCode(() -> {
            firstNumberValidator.validate("1", 0, 3);
            operatorValidator.validate("+", 1, 3);
            lastNumberValidator.validate("2", 2, 3);
        }).doesNotThrowAnyException();
    }
}
