package calculator.view;

import java.math.BigDecimal;

public class OutputView {

    public void printResult(BigDecimal result) {
        System.out.println(result.toString());
    }

    public void printExceptionMessage(String message) {
        System.out.println(message);
    }
}
