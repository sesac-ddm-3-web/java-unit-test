package com.example.racing_game;

import com.example.racing_game.car.Car;
import com.example.racing_game.moving_strategy.MovingStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarTest {

    @Test
    @DisplayName("isMovable이 true이면 Car는 한 칸 전진")
    void move_success_when_strategy_returns_true() {
        // given
        MovingStrategy alwaysMove = () -> true;
        Car car = new Car(1, alwaysMove);

        // when
        car.move();

        // then
        assertThat(car.getPosition()).isEqualTo(1);
    }

    @Test
    @DisplayName("isMovable이 false이면 Car는 정지")
    void move_fail_when_strategy_returns_false() {
        // given
        MovingStrategy neverMove = () -> false;
        Car car = new Car(1, neverMove);

        // when
        car.move();

        // then
        assertThat(car.getPosition()).isEqualTo(0);
    }

    @Test
    @DisplayName("여러 번 이동하면 position이 누적 증가")
    void move_multiple_times_accumulates_position() {
        // given
        MovingStrategy alwaysMove = () -> true;
        Car car = new Car(1, alwaysMove);

        // when
        car.move();
        car.move();
        car.move();

        // then
        assertThat(car.getPosition()).isEqualTo(3);
    }

    @Test
    @DisplayName("Car는 생성 시 position이 0")
    void initial_position_is_zero() {
        // given
        MovingStrategy anyStrategy = () -> true;

        // when
        Car car = new Car(1, anyStrategy);

        // then
        assertThat(car.getPosition()).isEqualTo(0);
    }

    @Test
    @DisplayName("Car는 생성 시 id를 정상적으로 가짐")
    void car_has_correct_id() {
        // given
        MovingStrategy anyStrategy = () -> true;

        // when
        Car car = new Car(99, anyStrategy);

        // then
        assertThat(car.getId()).isEqualTo(99);
    }
}