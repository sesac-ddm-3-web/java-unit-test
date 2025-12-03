package org.example.domain;

public class Car {
    private int position = 0;

    public void move(int condition){
        if(condition >= 4){
            position++;
        }
    }
    public int getPosition(){
        return position;
    }
}
