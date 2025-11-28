package SeSAC.calculator_test_code_example_;

import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {
    @Test
    @DisplayName("올바른 식에 대해 연산 수행 성공")
    void calculateSuccessTest() {
        //given
        Calculator calculator = new Calculator();
        String targetExpressionTokens = "1+33/2-10";

        //when
        double calculateResult = calculator.calculate(targetExpressionTokens);

        //then
        assertThat(calculateResult).isEqualTo(7);
    }

    @Test
    @DisplayName("0으로 나누는 경우 예외 발생")
    void CatchZeroDivdeExceptionTest() {
        //given
        Calculator calculator = new Calculator();
        String targetExpressionTokens = "1+33/2/0";
        //when&then
        Assertions.assertThrows(
                ArithmeticException.class,
                ()->{
                    calculator.calculate(targetExpressionTokens);
                }
        );

    }


}