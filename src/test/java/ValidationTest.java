import org.example.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ValidationTest {

    @Test
    @DisplayName("유효하지 않은 입력값에 대한 예외 발생")
    void invalidExceptionTest() {
        Validation validation = new Validation();

        assertThatThrownBy(() -> validation.validateFormula("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 입력값입니다.");
    }

}
