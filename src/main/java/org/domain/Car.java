package org.domain;

public class Car {
    private static final int MOVE_CONDITION = 4;

    private final NumberGenerator numberGenerator;
    private int position;

    public Car(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
        this.position = 0;
    }

    public void move() {
        if(isMovable()) {
            position++;
        }
    }

    public int getPosition() {
        return position;
    }

    private boolean isMovable() {
        return numberGenerator.generate() >= MOVE_CONDITION;
    }


}
