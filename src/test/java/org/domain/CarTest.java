package org.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class CarTest {
    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 8, 9})
    void 생성된_숫자가_4이상이면_이동한다(int value) {
        NumberGenerator numberGenerator = () -> value;
        Car car = new Car(numberGenerator);

        car.move();

        assertThat(car.getPosition()).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void 생성된_숫자가_4미만이면_이동하지_않는다(int value) {
        NumberGenerator numberGenerator = () -> value;
        Car car = new Car(numberGenerator);

        car.move();

        assertThat(car.getPosition()).isEqualTo(0);
    }
}