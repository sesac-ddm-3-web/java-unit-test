import org.example.Calculator;
import org.example.OperationFactory;
import org.example.Tokens;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    OperationFactory factory = new OperationFactory();
    Calculator calculator = new Calculator(factory);

    @Test
    @DisplayName("단일 연산은 정확한 결과를 반환해야 한다")
    void 단일연산은_정확한_결과를_반환한다() {
        Tokens tokens = new Tokens(
                new ArrayList<>(List.of(2.0, 3.0)),
                new ArrayList<>(List.of("+"))
        );

        double result = calculator.calculate(tokens);
        assertEquals(5.0, result);
    }

    @Test
    @DisplayName("연산은 왼쪽에서 오른쪽 순서대로 계산되어야 한다")
    void 연산은_왼쪽에서_오른쪽순서로_계산된다() {
        Tokens tokens = new Tokens(
                new ArrayList<>(List.of(10.0, 20.0, 3.0)),
                new ArrayList<>(List.of("+", "*"))
        );

        double result = calculator.calculate(tokens);
        assertEquals(90.0, result);
    }

    @Test
    @DisplayName("연산자가 없는 경우 숫자만 결과로 반환해야 한다")
    void 연산자가_없으면_숫자가_반환된다() {
        Tokens tokens = new Tokens(
                new ArrayList<>(List.of(5.0)),
                new ArrayList<>()
        );

        double result = calculator.calculate(tokens);
        assertEquals(5.0, result);
    }
}
