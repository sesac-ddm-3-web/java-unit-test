package Calculator;

import java.util.Arrays;
import java.util.Objects;

public enum Operation {
    PLUS("+"){
        public double apply(double x , double y){
            return x + y;
        }
    },
    MINUS("-"){
        public double apply(double x , double y){
            return x - y;
        }
    },
    MULTIPLY("*"){
        public double apply(double x , double y){
            return x * y;
        }
    },
    DIVIDE("/"){
        public double apply(double x , double y){
            if(y == 0){
                throw new CalculatorException(ExceptionCode.DIVIDE_BY_ZERO);
            }
            return x / y;
        }
    };

    private final String op;
    Operation(String op){
        this.op = op;
    }


    public static Operation findOperator(String operator){
        if(Objects.isNull(operator)){
            throw new CalculatorException(ExceptionCode.INVALID_OPERATOR);
        }

        return Arrays.stream(values())
                .filter(operation -> Objects.equals(operation.op , operator))
                .findFirst()
                .orElseThrow(() -> new CalculatorException(ExceptionCode.INVALID_OPERATOR));
    }


    public abstract double apply(double x, double y);
}
