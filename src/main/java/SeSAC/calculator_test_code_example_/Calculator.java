package SeSAC.calculator_test_code_example_;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Stack;

public class Calculator {
    public double calculate(String expression) {
        ExpressionParser parser = new ExpressionParser();
        PostFixExpressionConverter postFixExpressionConverter = new PostFixExpressionConverter();

        List<String> parsedTokens = parser.checkInputExpressionValidation(expression);
        List<String> convertedTokens = postFixExpressionConverter.convert(parsedTokens);

        Stack<Double> stack = new Stack<>();
        convertedTokens.forEach(token -> {
            if (token.matches("\\d+")) {
                stack.push(Double.parseDouble(token));
            }
            else{
                double opd2 = stack.pop();
                double opd1 = stack.pop();

                Operator operator = Operator.findBySymbol(token);
                stack.push(operator.calculate(opd1, opd2));
            }
        });
        double calculateResult = stack.pop();
        return calculateResult;
    }
}
