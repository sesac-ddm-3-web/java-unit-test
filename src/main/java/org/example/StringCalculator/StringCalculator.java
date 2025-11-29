package org.example.StringCalculator;

public class StringCalculator {

    private final Tokenizer tokenizer;
    private final Evaluator evaluator;

    public StringCalculator(Tokenizer tokenizer, Evaluator evaluator) {
        this.tokenizer = tokenizer;
        this.evaluator = evaluator;
    }

    public int calculate(String input) {
        Expression expression = new Expression(input);
        return evaluator.evaluate(tokenizer.tokenize(expression));
    }
}
