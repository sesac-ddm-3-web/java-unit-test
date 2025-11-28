package org.example.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ExpressionValidatorTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = new ExpressionValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "3",
            "3 + 3",
            "4 / 5 + 7",
            "3 * 5 + 8 - 9"
    })
    void 정상_수식이_들어오면_통과한다(String input) {
        assertThatCode(() -> validator.validate(input))
                .doesNotThrowAnyException();
    }


    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"  "})
    void 빈_값이_들어오면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> validator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비어있는 값은 허용되지 않습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "+1",
            "1 +",
            "1 ++ 2",
            "1 * (2)",
            "abc",
            "1 + 2 *",
            "1 + 2 3"
    })
    void 올바르지_않은_수식이_들어오면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> validator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 수식 형식입니다.");
    }
}