import org.example.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    @Test
    @DisplayName("차의 기본 위치는 0 이다.")
    void getPosition0() {
        Car car = new Car();

        int position = car.getPosition();

        assertEquals(0, position);
    }

    @Test
    @DisplayName("이동 호출시 위치가 1만큼 증가해야한다.")
    void getPosition1() {
        Car car = new Car();
        car.move();
        int position = car.getPosition();

        assertEquals(1, position);
    }

}
