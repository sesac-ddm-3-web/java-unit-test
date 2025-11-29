import org.example.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    void beforeEach() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("덧셈")
    void addTest() {
        assertThat(calculator.calculate("2+3")).isEqualTo("5");
    }

    @Test
    @DisplayName("뺼셈")
    void subTest() {
        assertThat(calculator.calculate("5-3")).isEqualTo("2");
    }

    @Test
    @DisplayName("곱셈")
    void mulTest() {
        assertThat(calculator.calculate("2*3")).isEqualTo("6");
    }

    @Test
    @DisplayName("나눗셈")
    void divTest() {
        assertThat(calculator.calculate("9/3")).isEqualTo("3");
    }

    @Test
    @DisplayName("0으로 나누면 예외 발생")
    void divExceptionTest() {
        assertThatThrownBy(() -> calculator.calculate("3/0"))
                .isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("0으로 나눌 수 없습니다.");
    }

    @Test
    @DisplayName("복합 계산")
    void allTest(){
        assertThat(calculator.calculate("5-3+2*4/2")).isEqualTo("8");
    }

}
