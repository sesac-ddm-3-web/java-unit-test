package org.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RacingCarsTest {

    private RacingCars racingCars;

    @Test
    void 지정된_수만큼_레이싱_자동차를_생성한다() {
        NumberGenerator generator = () -> 0;
        int participantsSize = 3;
        racingCars = RacingCars.create(participantsSize, generator);

        assertThat(racingCars.getCars()).hasSize(participantsSize);
    }

    @Test
    void 자동차들을_모두_이동시킬_수_있다() {
        NumberGenerator generator = () -> 4;
        int participantsSize = 3;
        racingCars = RacingCars.create(participantsSize, generator);

        racingCars.moveAll();

        List<Car> cars = racingCars.getCars();
        boolean result = cars.stream().allMatch(car -> car.getPosition() == 1);
        assertThat(result).isTrue();
    }

    @Test
    void 일부_자동차만_이동할_수_있다() {
        int participantsSize = 3;
        int[] numbers = {4, 3, 4};
        NumberGenerator generator = new SequenceGenerator(numbers);
        racingCars = RacingCars.create(participantsSize, generator);

        racingCars.moveAll();

        List<Car> cars = racingCars.getCars();
        assertThat(cars.get(0).getPosition()).isEqualTo(1);
        assertThat(cars.get(1).getPosition()).isEqualTo(0);
        assertThat(cars.get(2).getPosition()).isEqualTo(1);
    }

    private static class SequenceGenerator implements NumberGenerator {
        private int[] numbers;
        private int idx = 0;

        public SequenceGenerator(int[] numbers) {
            this.numbers = numbers;
        }

        @Override
        public int generate() {
            return numbers[idx++];
        }
    }
}