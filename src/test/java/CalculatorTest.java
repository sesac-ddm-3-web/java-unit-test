import Calculator.Calculator;
import Calculator.Tokenizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import Calculator.CalculatorException;
import Calculator.ExceptionCode;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class CalculatorTest {

    @Mock
    Tokenizer tokenizer;
    @Mock
    Scanner scanner;

    private Calculator calculator;
    @BeforeEach
    void setUp(){
        // Given
       this.calculator = new Calculator(tokenizer, scanner);
    }

  @ParameterizedTest
  @DisplayName("사칙연산이 잘 되는지 테스트한다")
  @CsvSource(
          {
                  "3, 2, *, 6",  // x=3, y=2, op='*', expected=6
                  "10, 5, +, 15", // x=10, y=5, op='+', expected=15
                  "10, 2, /, 5",  // x=10, y=2, op='/', expected=5
                  "7, 3, -, 4"    // x=7, y=3, op='-', expected=4
          }
  )
    void calculate_correct(double x, double y, String op, double expected){
        // Given x, y , op

        // When
        double result = calculator.calculate(x,y,op);

        //Then
        assertThat(result).isEqualTo(expected);

    }

    @Test
    @DisplayName("0으로 나누면 예외가 발생해야 한다")
    void calculate_divide_by_zero(){
        // When & Then
        assertThatThrownBy(() -> calculator.calculate(10, 0, "/"))
                .isInstanceOf(CalculatorException.class)
                .hasMessage(ExceptionCode.DIVIDE_BY_ZERO.getMessage());
    }

    @Test
    @DisplayName("입력값이 null이거나 빈 공백일 경우 예외가 발생해야한다.")
    void toDouble_invalid_input(){

        // When & Then
        assertThatThrownBy(() -> calculator.toDouble(null))
                .isInstanceOf(CalculatorException.class)
                .hasMessage(ExceptionCode.INVALID_INPUT.getMessage());
    }

    @Test
    @DisplayName("사칙연산 기호가 아닌 경우 예외가 발생해야 한다")
    void calculate_invalid_operator() {
        // Given
        String invalidOp = "^";

        // When & Then
        assertThatThrownBy(() -> calculator.calculate(10, 5, invalidOp))
                .isInstanceOf(CalculatorException.class)
                .hasMessage(ExceptionCode.INVALID_OPERATOR.getMessage());
    }
}
