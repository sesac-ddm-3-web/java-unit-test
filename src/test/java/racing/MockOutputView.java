package racing;

import java.util.List;
import racing.domain.Car;
import racing.view.OutputView;

public class MockOutputView extends OutputView {

    private int countAnnounceRaceResultCall = 0;
    private int countPrintRoundCall = 0;

    @Override
    public void announceRaceResult() {
        this.countAnnounceRaceResultCall++;
    }

    @Override
    public void printRound(List<Car> participants) {
        this.countPrintRoundCall++;
    }

    public int getCountAnnounceRaceResultCall() {
        return countAnnounceRaceResultCall;
    }

    public int getCountPrintRoundCall() {
        return countPrintRoundCall;
    }
}
