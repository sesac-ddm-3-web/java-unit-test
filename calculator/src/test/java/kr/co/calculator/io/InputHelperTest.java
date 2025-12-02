package kr.co.calculator.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.*;

class InputHelperTest {

    @DisplayName("inputExpression은 프롬프트를 출력하고 사용자가 입력한 한 줄을 반환한다")
    @Test
    void inputExpression_프롬프트_출력_및_입력_반환() {
        // given
        String userInput = "1 + 2 * 3\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            InputHelper inputHelper = new InputHelper(scanner);

            // when
            String expression = inputHelper.inputExpression();

            // then
            assertThat(expression).isEqualTo("1 + 2 * 3");
            assertThat(outContent.toString())
                    .contains("계산식을 입력하세요:");
        } finally {
            System.setOut(originalOut);
        }
    }
}
