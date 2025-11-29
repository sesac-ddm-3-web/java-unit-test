import org.example.calculator.Calculator;
import org.example.calculator.CalculatorImpl;
import org.example.expression.SimpleExpressionParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorImplTest {

    private final Calculator calculator = new CalculatorImpl(new SimpleExpressionParser());

    @Test
    @DisplayName("기본 예시 : 2 + 3 * 4 / 2 = 10 (왼쪽부터 순차 계산)")
    void calculate_basicExample() {
        int result = calculator.calculate("2 + 3 * 4 / 2");
        assertEquals(10, result);
    }

    @Test
    @DisplayName("공백 없이도 정상 동작 : 2+3*4/2 = 10")
    void calculate_withoutSpaces() {
        int result = calculator.calculate("2+3*4/2");
        assertEquals(10, result);
    }

    @Test
    @DisplayName("뺄셈과 덧셈의 순서대로 계산 : 10 - 3 + 2 = 9")
    void calculate_addAndSubtract() {
        int result = calculator.calculate("10 - 3 + 2");
        assertEquals(9, result);
    }

    @Test
    @DisplayName("곱셈과 나눗셈도 순서대로 계산 : 8 / 2 * 3 = 12")
    void calculate_multiplyAndDivide() {
        int result = calculator.calculate("8 / 2 * 3");
        assertEquals(12, result);
    }

    @Test
    @DisplayName("숫자 하나만 들어와도 그대로 반환")
    void calculate_singleNumber() {
        int result = calculator.calculate("7");
        assertEquals(7, result);
    }

    @Test
    @DisplayName("앞뒤에 불필요한 공백이 있어도 동작")
    void calculate_withLeadingAndTrailingSpaces() {
        int result = calculator.calculate("   2 + 3   ");
        assertEquals(5, result);
    }

    @Test
    @DisplayName("잘못된 수식 형식: 연산자로 끝나는 경우 예외")
    void calculate_invalidExpression_endsWithOperator() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculate("2 + 3 *")
        );
        assertTrue(ex.getMessage().contains("잘못된 수식"));
    }

    @Test
    @DisplayName("잘못된 수식 형식: 연산자로 시작하는 경우 예외")
    void calculate_invalidExpression_startsWithOperator() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculate("+ 2 3")
        );
        assertFalse(ex.getMessage().contains("잘못된 수식"));
    }

    @Test
    @DisplayName("숫자가 아닌 토큰이 포함되면 예외")
    void calculate_invalidToken_notNumber() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculate("2 + a * 3")
        );
        assertTrue(ex.getMessage().contains("숫자가 아닙니다"));
    }

    @Test
    @DisplayName("지원하지 않는 연산자가 포함되면 예외")
    void calculate_invalidOperator() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculate("2 @ 3")
        );
        assertTrue(ex.getMessage().contains("지원하지 않는 연산자"));
    }

    @Test
    @DisplayName("0으로 나누면 ArithmeticException 발생")
    void calculate_divideByZero() {
        ArithmeticException ex = assertThrows(
                ArithmeticException.class,
                () -> calculator.calculate("10 / 0")
        );
        assertTrue(ex.getMessage().contains("0으로 나눌 수 없습니다"));
    }

    @Test
    @DisplayName("null 입력 시 예외")
    void calculate_nullInput() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculate(null)
        );
        assertTrue(ex.getMessage().contains("입력이 비어"));
    }

    @Test
    @DisplayName("공백만 있는 입력 시 예외")
    void calculate_blankInput() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculate("   ")
        );
        assertTrue(ex.getMessage().contains("입력이 비어"));
    }

    @Test
    @DisplayName("여러 연산자 체인도 순차 계산 확인")
    void calculate_longExpression() {
        int result = calculator.calculate("1 + 2 + 3 + 4 * 2 / 2 - 1");
        assertEquals(9, result);
    }
}
