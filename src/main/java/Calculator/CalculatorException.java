package Calculator;

public class CalculatorException extends RuntimeException{
    public CalculatorException(ExceptionCode exceptionCode){
        super(exceptionCode.getMessage());
    }
}
