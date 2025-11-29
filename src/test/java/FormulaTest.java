import org.example.Formula;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class FormulaTest {

    @Test
    @DisplayName("공백 제거")
    void refineTest(){
        Formula formula = new Formula();

        formula.refineFormula("   8  /  9 ");

        assertThat(formula.getRefinedFormula()).isEqualTo("8/9");
    }
}
