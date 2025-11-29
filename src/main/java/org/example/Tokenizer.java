package org.example;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    public Tokens tokenize(String expression) {
        List<Double> numbers = new ArrayList<>();
        List<String> operators = new ArrayList<>();

        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            if (token.matches("\\d+")) {
                numbers.add(Double.parseDouble(token));
            } else{
                operators.add(token);
            }
        }
        return new Tokens(numbers, operators);
    }
}
