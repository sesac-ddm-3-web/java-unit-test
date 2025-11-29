import org.example.StringCalculator.Expression;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ExpressionTest {

    @DisplayName("수식 객체는 생성 시 내부의 모든 공백을 제거한다.")
    @Test
    void remove_spaces() {
        // given
        String input = "1 + 2 * 3";

        // when
        Expression expression = new Expression(input);

        // then
        assertThat(expression.getValue()).isEqualTo("1+2*3");
    }

    @DisplayName("수식이 null이거나 비어있으면 예외가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   "})
    void validate_blank(String input) {
        // when & then
        assertThatThrownBy(() -> new Expression(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수식은 비어있을 수 없습니다.");
    }
}
