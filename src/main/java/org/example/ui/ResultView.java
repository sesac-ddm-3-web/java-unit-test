package org.example.ui;

import org.example.domain.Car;
import org.example.domain.Cars;

public class ResultView {

    public void printBeforeGame(){
        System.out.println();
        System.out.println("실행 결과");
    }

    public void printCarGame(Cars cars){
        for(Car car : cars.getCars()){
            System.out.println("-".repeat(car.getPosition()));
        }
    }
    public void printNewLine(){
        System.out.println();
    }
}
