package SeSAC.RacingGame_TestCode_exmaple;

import java.security.InvalidParameterException;

public class RacingGame {
    ParticipantList participantList;
    RacingRule racingRule;
    long participantNumber;
    long tryNumber;

    public RacingGame(long participantNumber, long tryNumber, RacingRule racingRule) {
        if(tryNumber < 1) {
            throw new InvalidParameterException("시도 횟수는 0보다 커야합니다.");
        }
        this.participantList = new ParticipantList(participantNumber);
        this.participantNumber = participantNumber;
        this.tryNumber = tryNumber;
        this.racingRule = racingRule;
    }

    public void startRace() {
        for(int i = 0; i < tryNumber; i++) {
            System.out.println("["+(i+1)+" 회차]");
            participantList.doRace(racingRule);
            participantList.printRaceState();
            System.out.println();

        }
    }
}
