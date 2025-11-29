package org.example;

public class Main {
    public static void main(String[] args) {
        Tokenizer tokenizer = new Tokenizer();
        StringCalculator calculator = new StringCalculator(tokenizer);
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();

        String expression = inputView.inputExpression();
        int result = calculator.calculate(expression);
        resultView.OutputExpression(result);
    }
}