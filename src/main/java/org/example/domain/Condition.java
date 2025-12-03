package org.example.domain;

public class Condition {

    public int generateRandomNum() {
        return (int) (Math.random() * 9 + 0);
    }

    public boolean canGo(int condition) {
        return condition > 3 ? true : false;
    }
}
