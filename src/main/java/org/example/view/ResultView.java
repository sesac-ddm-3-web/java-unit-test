package org.example.view;

import java.util.List;

public class ResultView {

    public void resultComment() {
        System.out.println("실행 결과 :");
    }

    public void resultView(List<Integer> carResult) {
        for (Integer position : carResult) {
            int i = 0;

            System.out.print("|");

            while (i < position) {
                System.out.print("-");
                i++;
            }
            System.out.println();
        }
        System.out.println();
    }
}
