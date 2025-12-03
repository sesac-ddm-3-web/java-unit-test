package org.example.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarTest {

    private Car car;

    @BeforeEach
    public void init() {
        car = new Car(1);
    }

    @DisplayName("[성공] 움직인다.")
    @Test
    public void success_move() {
        // given
        MoveStrategy fakeMoveStrategy = () -> true;

        // when
        car.move(fakeMoveStrategy);

        // then
        assertThat(car.getDistance()).isEqualTo(1);
    }

    @DisplayName("[성공] 움직이지 않는다.")
    @Test
    public void success_not_move() {
        // given
        MoveStrategy fakeMoveStrategy = () -> false;

        // when
        car.move(fakeMoveStrategy);

        // then
        assertThat(car.getDistance()).isEqualTo(0);
    }

    @DisplayName("[성공] 여러 번 움직인다.")
    @Test
    public void success_move_multiple_times() {
        // given
        MoveStrategy fakeMoveStrategy = () -> true;

        // when
        car.move(fakeMoveStrategy);
        car.move(fakeMoveStrategy);
        car.move(fakeMoveStrategy);

        // then
        assertThat(car.getDistance()).isEqualTo(3);
    }
}