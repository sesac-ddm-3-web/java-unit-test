package org.example;

public class Dvide implements Operation {
    @Override
    public double calculate(double a, double b) {
        if(b==0) {
            throw new ArithmeticException("0으로는 나눌수 없습니다.");
        }
        return a/b;
    }
}
