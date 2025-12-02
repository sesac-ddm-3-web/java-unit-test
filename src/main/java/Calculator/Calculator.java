package Calculator;


import java.util.*;

public class Calculator {

    private final Tokenizer tokenizer;
    private final Scanner scanner;

    public Calculator(Tokenizer tokenizer, Scanner scanner){
        this.tokenizer = tokenizer;
        this.scanner = scanner;
    }

    public Double toDouble(String token){
        if(token == null){
            throw new CalculatorException(ExceptionCode.INVALID_INPUT);
        }
        try{
            return Double.parseDouble(token);
        } catch (NumberFormatException e) {
            throw new CalculatorException(ExceptionCode.INVALID_INPUT);
        }
    }

    public void run(){
        boolean flag = true;
        double result;
        while(flag){
            try{
                System.out.println("문자열을 입력하세요 (예시 : 1+2/3 , 종료 : exit");
                // 입력
                String input = scanner.nextLine();

                if(input == null){
                    throw new CalculatorException(ExceptionCode.INVALID_INPUT);
                }

                if(input.equals("exit")){
                    flag = false;
                    System.out.println("계산기 프로그램을 종료합니다. ");
                    return;
                }

                // 계산 처리
                Queue<String> tokens  = tokenizer.tokenize(input);

                // 맨 처음 하나 뽑기
                result = toDouble(tokens.poll());

                while(!tokens.isEmpty()){

                    String op = tokens.poll();
                    double num1 = toDouble(tokens.poll());

                    result = calculate(result, num1, op);
                }
                // 출력
                System.out.println("계산 결과는 : " + result);

            } catch (CalculatorException e) {
               System.out.println(e.getMessage());
            }

        }


    }

    public double calculate(double x , double y, String operator){

        Operation operation = Operation.findOperator(operator);

        return operation.apply(x, y);
    }

}

