import org.example.StringCalculator.Evaluator;
import org.example.StringCalculator.Operator;
import org.example.StringCalculator.token.NumberToken;
import org.example.StringCalculator.token.Token;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EvaluatorTest {

    private final Evaluator evaluator = new Evaluator();

    @DisplayName("토큰 리스트를 순차적으로 계산한다 (사칙연산 우선순위 무시, 앞에서 뒤로).")
    @Test
    void evaluate() {
        List<Token> tokens = List.of(
                new NumberToken(2),
                Operator.PLUS.getToken(),
                new NumberToken(3),
                Operator.MULTIPLY.getToken(),
                new NumberToken(4)
        );

        // when
        int result = evaluator.evaluate(tokens);

        // then
        assertThat(result).isEqualTo(20);
    }

    @DisplayName("연산자가 부족하거나 숫자로 끝나지 않는 경우 예외가 발생한다.")
    @Test
    void invalid_expression_format() {
        //given
        List<Token> tokens = List.of(
                new NumberToken(1),
                Operator.PLUS.getToken()
        );

        // when & then
        assertThatThrownBy(() -> evaluator.evaluate(tokens))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("연산식이 잘못되었습니다");
    }

    @DisplayName("연산은 숫자로 시작해야 한다.")
    @Test
    void start_with_operator() {
        // given : + 1
        List<Token> tokens = List.of(
                Operator.PLUS.getToken(),
                new NumberToken(1)
        );

        // when & then
        assertThatThrownBy(() -> evaluator.evaluate(tokens))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("연산은 숫자로 시작해야 합니다.");
    }

    @DisplayName("최종 계산 결과가 int 범위를 초과하면 예외가 발생한다.")
    @Test
    void result_overflow() {
        // given : 2147483647 + 1
        List<Token> tokens = List.of(
                new NumberToken(Integer.MAX_VALUE),
                Operator.PLUS.getToken(),
                new NumberToken(1)
        );

        // when & then
        assertThatThrownBy(() -> evaluator.evaluate(tokens))
                .isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("연산 결과가 int 범위를 초과했습니다");
    }

    @DisplayName("나눗셈 결과의 소수점은 버려진다.")
    @Test
    void divide_truncate() {
        // given : 3 / 2
        List<Token> tokens = List.of(
                new NumberToken(3),
                Operator.DIVIDE.getToken(),
                new NumberToken(2)
        );

        // when
        int result = evaluator.evaluate(tokens);

        // then
        assertThat(result).isEqualTo(1);
    }
}
