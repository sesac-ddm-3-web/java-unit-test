package racing.view;

import racing.domain.Car;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String POSITION_MARK = "-";

    public void announceRaceResult() {
        System.out.println();
        System.out.println("실행 결과");
    }

    public void printRound(List<Car> participants) {
        String result = participants.stream()
                                    .map(this::formatCarPosition)
                                    .collect(Collectors.joining(System.lineSeparator()));

        System.out.println(result);
        System.out.println();
    }

    public void printExceptionMessage(String message) {
        System.out.println(message);
    }

    private String formatCarPosition(Car car) {
        return POSITION_MARK.repeat(car.getPosition());
    }
}
