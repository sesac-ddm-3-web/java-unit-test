package org.example;

import java.util.List;

public class Tokens {

    private List<Double>  numbers;
    private List<String> operators;
    private double value;
    public Tokens(List<Double> numbers, List<String> operators) {
        this.numbers = numbers;
        this.operators = operators;
    }

    public List<Double> getNumbers() {
        return numbers;
    }

    public List<String> getOperators() {
        return operators;
    }
}
