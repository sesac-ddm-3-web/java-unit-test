package org.example;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class CarTest {

    @ParameterizedTest
    @ValueSource(ints = {4,5,6,7,8,9})
    @DisplayName("숫자가 4이상이면 자동차가 전진해야한다")
    void move_if_number_is_4_or_more(int number){

        //given
        Car car = new Car();

        //when
        car.move(number);

        //then
        assertThat(car.getPosition()).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    @DisplayName("숫자가 4미만이면 자동차가 멈춰야한다")
    void stop_if_number_is_3_or_less(int number){

        //given
        Car car = new Car();

        //when
        car.move(number);

        //then
        assertThat(car.getPosition()).isEqualTo(0);
    }
}
