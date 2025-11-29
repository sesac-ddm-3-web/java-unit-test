import org.example.StringCalculator.Operator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class OperatorTest {

    @DisplayName("기호에 맞는 연산자를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"+,PLUS", "-,MINUS", "*,MULTIPLY", "/,DIVIDE"})
    void find_operator(String symbol, Operator expected) {
        // when
        Operator actual = Operator.from(symbol);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("지원하지 않는 연산자 기호가 들어오면 예외를 던진다.")
    @Test
    void unknown_operator() {
        // given
        String invalidSymbol = "^";

        // when & then
        assertThatThrownBy(() -> Operator.from(invalidSymbol))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("지원하지 않는 연산자");
    }
}
