package com.example.racing_game.car;

import com.example.racing_game.moving_strategy.MovingStrategy;
import lombok.Getter;

@Getter
public class Car {

    private Integer id;
    private Integer position;
    private final MovingStrategy movingStrategy;

    public Car(Integer id, MovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
        this.position = 0;
        this.id = id;
    }

    public void move(){
        if (movingStrategy.isMovable()) {
            this.position++;
        }
    }
}
