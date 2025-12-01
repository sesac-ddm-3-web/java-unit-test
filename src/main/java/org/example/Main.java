package org.example;

import java.util.Scanner;

import org.example.domain.Calculator;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Calculator calculator = new Calculator();

        while (true) {
            System.out.print("수식을 입력하세요: ");
            String expression = in.nextLine();

            if ("exit".equals(expression)) {
                break;
            }

            System.out.println(expression + " = " + calculator.calculate(expression));
        }
    }
}