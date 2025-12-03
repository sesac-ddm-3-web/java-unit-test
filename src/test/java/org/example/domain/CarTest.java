package org.example.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CarTest {

    @Test
    @DisplayName("true면 position이 증가한다")
    void moveTest() {
        Car car = new Car();

        car.move(true);

        assertThat(car.getPosition()).isEqualTo(1);
    }

    @Test
    @DisplayName("false면 position이 증가하지 않는다")
    void noMoveTest() {
        Car car = new Car();

        car.move(false);

        assertThat(car.getPosition()).isEqualTo(0);
    }
}