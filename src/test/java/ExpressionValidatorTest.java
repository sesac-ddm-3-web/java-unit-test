

import org.example.ExpressionValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExpressionValidatorTest {

    ExpressionValidator validator = new ExpressionValidator();

    @Test
    @DisplayName("입력이 null이면 예외가 발생해야 한다")
    void 입력이_null이면_예외가_발생한다() {
        assertThrows(NullPointerException.class,
                () -> validator.validate(null));
    }

    @Test
    @DisplayName("문자열이 공백이면 예외가 발생해야 한다")
    void 문자열이_공백이면_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validate("   "));
    }

    @Test
    @DisplayName("숫자와 연산자가 아닌 문자가 포함되면 예외가 발생해야 한다")
    void 잘못된_문자가_포함되면_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validate("2 + a"));
    }

    @Test
    @DisplayName("연산자가 연속으로 등장하면 예외가 발생해야 한다")
    void 연산자_연속등장시_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validate("2 ++ 3"));
    }

    @Test
    @DisplayName("정상적인 표현식은 검증을 통과해야 한다")
    void 정상표현식은_검증을_통과한다() {
        assertDoesNotThrow(() -> validator.validate("10 + 20 * 3"));
    }
}
