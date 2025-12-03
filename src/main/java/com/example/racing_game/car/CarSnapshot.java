package com.example.racing_game.car;


public record CarSnapshot(int id, int position) {

    public static CarSnapshot from(Car car) {
        return new CarSnapshot(
                car.getId(),
                car.getPosition()
        );
    }
}