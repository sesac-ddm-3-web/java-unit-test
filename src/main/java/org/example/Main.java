package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tokenizer tokenizer = new Tokenizer();
        StringCalculator calculator = new StringCalculator(tokenizer);
        InputView inputView = new InputView(sc);
        ResultView resultView = new ResultView();

        String expression = inputView.inputExpression();
        int result = calculator.calculate(expression);
        resultView.OutputExpression(result);
    }
}