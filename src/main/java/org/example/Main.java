package org.example;

import org.example.calculator.Calculator;
import org.example.calculator.CalculatorImpl;
import org.example.expression.SimpleExpressionParser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {//
        Calculator calculator = new CalculatorImpl(new SimpleExpressionParser());
        Scanner scanner = new Scanner(System.in);
        int result = calculator.calculate(scanner.nextLine());
        System.out.println(result);
    }
}