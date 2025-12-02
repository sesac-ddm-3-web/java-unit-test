package kr.co.calculator.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;

class OutputHelperTest {

    @DisplayName("printResult는 '결과: 값' 형식으로 출력한다")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, -3, 10})
    void printResult_결과_형식으로_출력(int value) {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            OutputHelper outputHelper = new OutputHelper();

            // when
            outputHelper.printResult(value);

            // then
            assertThat(outContent.toString())
                    .contains("결과: " + value);
        } finally {
            System.setOut(originalOut);
        }
    }

    @DisplayName("printExceptionMessage는 전달받은 예외 메시지를 그대로 출력한다")
    @Test
    void printExceptionMessage_메시지_그대로_출력() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            OutputHelper outputHelper = new OutputHelper();
            String message = "계산식은 비어 있을 수 없습니다.";

            // when
            outputHelper.printExceptionMessage(message);

            // then
            assertThat(outContent.toString())
                    .contains(message);
        } finally {
            System.setOut(originalOut);
        }
    }
}
