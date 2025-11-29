package org.example;

import java.util.Scanner;

public class InputView {
    public String inputExpression(){
        System.out.println("계산식을 입력하세요: ");
        Scanner scanner=new Scanner(System.in);
        return scanner.nextLine();
    }
}
