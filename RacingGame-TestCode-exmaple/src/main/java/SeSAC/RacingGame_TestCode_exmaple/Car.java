package SeSAC.RacingGame_TestCode_exmaple;

public class Car {
    private long carId;
    private int raceResult;

    public Car(long carId) {
        this.carId = carId;
        this.raceResult = 0;
    }

    public void drive(int driveDistanceFromRule) {
        raceResult += driveDistanceFromRule;
    }

    @Override
    public String toString(){
        return (carId + "번 참가자");
    }

    public String raceState(){
        return "-".repeat(raceResult);
    }
}
