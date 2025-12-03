package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarsTest {

    @Test
    @DisplayName("입력한 숫자만큼 자동차가 생성되야 한다")
    void create_cars_check_size(){

        //given
        Cars cars = new Cars(3);

        //when & then
        assertThat(cars.getCars()).hasSize(3);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1})
    @DisplayName("자동차가 2대 미만일 경우 예외를 일으킨다.")
    void throw_exception_if_less_than_two_cars(int number){


        //when & then
        assertThatThrownBy(() ->new Cars(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차는 2대 이상이어야 합니다");
    }

    @ParameterizedTest
    @ValueSource(ints = {4,5,6,7,8,9})
    @DisplayName("조건이 맞으면 모든 차가 전진해야 한다")
    void move_all_cars(int number){
        //given
        Cars cars = new Cars(3);

        //when
        cars.moveAll(() -> number);

        //then
        for (Car car : cars.getCars()) {
            assertThat(car.getPosition()).isEqualTo(1);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    @DisplayName("조건이 틀리면 모든 차가 멈춰야 한다")
    void stop_all_cars(int number){

        //given
        Cars cars = new Cars(3);

        //when
        cars.moveAll(() -> number);

        //then
        for (Car car : cars.getCars()) {
            assertThat(car.getPosition()).isEqualTo(0);
        }
    }
}
