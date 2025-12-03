package org.example.racing;

public class RacingCar {
    private int position;
    private ValueGenerator valueGenerator;

    private static final int MOVE_CONDITION = 4;

    public RacingCar(ValueGenerator valueGenerator) {
        this.position = 0;
        this.valueGenerator = valueGenerator;
    }

    public void move(){
        if(valueGenerator.generate() >= MOVE_CONDITION){
            position++;
        }
    }

    public int getPosition() {
        return position;
    }
}
