package org.example;

import java.util.List;

public class Calculator {

    private OperationFactory operationFactory;
    public Calculator(OperationFactory operationFactory) {
        this.operationFactory = operationFactory;
    }

    public double calculate(Tokens tokens) {

        List<Double> numbers = tokens.getNumbers();
        List<String> operators = tokens.getOperators();

        while(!operators.isEmpty()) {
            double a = numbers.remove(0);
            double b = numbers.remove(0);
            String operator = operators.remove(0);

            Operation operation = operationFactory.get(operator);
            double result = operation.calculate(a,b);

            numbers.add(0,result);
        }

        return numbers.get(0);
    }
}
