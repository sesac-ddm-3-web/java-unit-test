package calculator.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String inputExpression() {
        System.out.print("계산할 수식을 입력해주세요. : ");

        return scanner.nextLine();
    }
}
