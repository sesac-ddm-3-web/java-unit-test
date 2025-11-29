import org.example.StringCalculator.Evaluator;
import org.example.StringCalculator.StringCalculator;
import org.example.StringCalculator.Tokenizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringCalculatorTest {

    private StringCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new StringCalculator(new Tokenizer(), new Evaluator());
    }

    @DisplayName("문자열 수식을 입력받아 계산 결과를 반환한다.")
    @Test
    void calculate_success() {
        // given
        String input = "10 + 2 * 3";

        // when
        int result = calculator.calculate(input);

        // then
        // (10 + 2) * 3 = 36
        assertThat(result).isEqualTo(36);
    }

    @DisplayName("공백이 섞여 있어도 정상적으로 계산한다.")
    @Test
    void calculate_with_spaces() {
        // given
        String input = " 4 /  2  + 3 ";

        // when
        int result = calculator.calculate(input);

        // then
        // (4 / 2) + 3 = 5
        assertThat(result).isEqualTo(5);
    }
}
