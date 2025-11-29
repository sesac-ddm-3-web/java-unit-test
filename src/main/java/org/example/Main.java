package org.example;

public class Main {
    public static void main(String[] args) {

        Formula formula = new Formula();
        Calculator calculator = new Calculator();

        formula.inputData();

        try {
            System.out.println(calculator.calculate(formula.getRefinedFormula()));
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}