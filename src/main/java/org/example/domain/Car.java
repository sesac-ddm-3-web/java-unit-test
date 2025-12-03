package org.example.domain;

public class Car {
    private int id;
    private int distance = 0;

    public Car(int id) {
        this.id = id;
    }

    public void move(MoveStrategy strategy) {
        if (strategy.canMove()) {
            distance++;
        }
    }

    public int getId() {
        return id;
    }

    public int getDistance() {
        return distance;
    }
}
