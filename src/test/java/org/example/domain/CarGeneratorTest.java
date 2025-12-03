package org.example.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CarGeneratorTest {

    @Test
    @DisplayName("받은 개수만큼 Car객체 생성")
    void generateCarTest() {
        CarGenerator carGenerator = new CarGenerator();

        assertThat(carGenerator.generateCar(3).size())
                .isEqualTo(3);
    }
}