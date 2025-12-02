package Calculator;

import java.util.Scanner;

public class CalcApplication {
    public static void main(String[] args){
        // 초기화
        Scanner scanner = new Scanner(System.in);
        Tokenizer tokenizer = new Tokenizer();
        Calculator calculator = new Calculator(tokenizer, scanner);

        // 실행
        calculator.run();

    }
}
