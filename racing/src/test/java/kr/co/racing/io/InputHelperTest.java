package kr.co.racing.io;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

class InputHelperTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5})
    void inputNumberOfCars_입력한_자동차_대수만큼_그대로_되돌려준다(int input) {
        // given
        ByteArrayInputStream in = new ByteArrayInputStream((input + "\n").getBytes());
        Scanner scanner = new Scanner(in);
        InputHelper inputHelper = new InputHelper(scanner);

        // when
        Integer numberOfCars = inputHelper.inputNumberOfCars();

        // then
        assertThat(numberOfCars).isEqualTo(input);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 10})
    void inputTryCount_입력한_시도_횟수를_그대로_되돌려준다(int input) {
        // given
        ByteArrayInputStream in = new ByteArrayInputStream((input + "\n").getBytes());
        Scanner scanner = new Scanner(in);
        InputHelper inputHelper = new InputHelper(scanner);

        // when
        Integer tryCount = inputHelper.inputTryCount();

        // then
        assertThat(tryCount).isEqualTo(input);
    }
}
