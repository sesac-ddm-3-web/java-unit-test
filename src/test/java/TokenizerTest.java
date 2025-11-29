import org.example.StringCalculator.Expression;
import org.example.StringCalculator.Operator;
import org.example.StringCalculator.Tokenizer;
import org.example.StringCalculator.token.NumberToken;
import org.example.StringCalculator.token.Token;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TokenizerTest {

    @DisplayName("수식을 숫자와 연산자 토큰으로 분리한다.")
    @Test
    void tokenize() {
        // given
        Tokenizer tokenizer = new Tokenizer();
        Expression expression = new Expression("12+34*5");

        // when
        List<Token> tokens = tokenizer.tokenize(expression);

        // then
        assertThat(tokens).hasSize(5)
                .containsExactly(
                        new NumberToken(12),
                        Operator.PLUS.getToken(),
                        new NumberToken(34),
                        Operator.MULTIPLY.getToken(),
                        new NumberToken(5)
                );
    }

    @DisplayName("숫자와 연산자 외의 문자가 포함되면 예외가 발생한다.")
    @Test
    void invalid_character() {
        // given
        Tokenizer tokenizer = new Tokenizer();
        Expression expression = new Expression("1+2&3");

        // when & then
        assertThatThrownBy(() -> tokenizer.tokenize(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 문자입니다");
    }

    @DisplayName("Int 범위를 벗어나는 숫자가 있으면 예외가 발생한다.")
    @Test
    void integer_overflow() {
        // given
        Tokenizer tokenizer = new Tokenizer();
        String largeNumber = "2147483648";
        Expression expression = new Expression(largeNumber);

        // when & then
        assertThatThrownBy(() -> tokenizer.tokenize(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자 범위가 int을 초과했습니다");
    }
}
