package kr.co.racing.io;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class OutputHelperTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream originalOut;
    private OutputHelper outputHelper;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        outputHelper = new OutputHelper();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void printResult_실행결과_문자열과_함께_출력된다() {
        // given
        String result = "-\n-\n";

        // when
        outputHelper.printResult(result);

        // then
        assertThat(outContent.toString())
                .isEqualTo("실행 결과:\n" + result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "에러가 발생했습니다.",
            "잘못된 입력입니다.",
            "예외 메시지입니다."
    })
    void printExceptionMessage_예외메시지를_그대로_한줄로_출력한다(String message) {
        // when
        outContent.reset();
        outputHelper.printExceptionMessage(message);

        // then
        assertThat(outContent.toString())
                .isEqualTo(message + System.lineSeparator());
    }
}
