package org.example;

import java.util.Scanner;

public class Formula {
    private String refinedFormula;
    Scanner scanner = new Scanner(System.in);
    private Validation validation = new Validation();

    public void inputData() {
        System.out.print("식 입력 : ");

        String formula = scanner.nextLine();

        try {
            validation.validateFormula(formula);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        refineFormula(formula);
    }

    public void refineFormula(String formula) {
        this.refinedFormula = formula.replaceAll("\\s+", "");
    }

    public String getRefinedFormula() {
        return refinedFormula;
    }
}
