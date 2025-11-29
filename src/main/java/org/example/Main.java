package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ExpressionValidator expressionValidator = new ExpressionValidator();
        Tokenizer tokenizer = new Tokenizer();
        OperationFactory operationFactory = new OperationFactory();
        Calculator calculator = new Calculator(operationFactory);

        Scanner scanner = new Scanner(System.in);
        System.out.print("식을 입력해주세요(ex. 4 + 2 * 2) : ");
        String input = scanner.nextLine();

        expressionValidator.validate(input);

        Tokens tokens = tokenizer.tokenize(input);
        double result = calculator.calculate(tokens);

        System.out.println(result);
    }
}