package org.example;

public class Validation {

    //✔ 2 + 3 - 4 / 3
    //✔ 10*5+3
    //✔ 7 - 2
    public void validateFormula(String formula) {
        String trimmed = formula.trim();

        String pattern = "^\\s*\\d+\\s*(?:[+\\-*/]\\s*\\d+\\s*)*$";

        if (!trimmed.matches(pattern)) {
            throw new IllegalArgumentException("유효하지 않은 입력값입니다.");
        }
    }

}
