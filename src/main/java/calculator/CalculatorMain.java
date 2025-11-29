package calculator;

import calculator.domain.Calculator;
import calculator.domain.ExpressionTokenizer;
import calculator.view.InputView;
import calculator.view.OutputView;
import java.math.BigDecimal;
import java.util.Scanner;

public class CalculatorMain {

    public static void main(String[] args) {
        ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer();
        Calculator calculator = new Calculator(expressionTokenizer);
        OutputView outputView = new OutputView();

        try (Scanner sc = new Scanner(System.in)) {
            InputView inputView = new InputView(sc);
            String expression = inputView.inputExpression();
            BigDecimal result = calculator.calculate(expression);

            outputView.printResult(result);
        } catch (IllegalArgumentException ex) {
            outputView.printExceptionMessage(ex.getMessage());
        }
    }
}
