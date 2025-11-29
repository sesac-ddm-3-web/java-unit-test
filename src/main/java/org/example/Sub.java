package org.example;

public class Sub implements Operation{

    @Override
    public double calculate(double a, double b) {
        return a-b;
    }
}
