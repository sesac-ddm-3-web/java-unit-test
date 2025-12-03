package org.example.domain;

public class Car {
    private int position;

    public Car() {
        this.position = 0;
    }

    public void move(boolean canGo) {
        if (canGo) {
            this.position++;
        }
    }

    public int getPosition() {
        return position;
    }
}
