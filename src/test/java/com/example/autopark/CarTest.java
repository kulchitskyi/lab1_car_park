import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.example.autopark.*;

public class CarTest {

    @Test
    public void testDriveReducesFuelAndIncreasesMileage() {
        Manufacturer m = new Manufacturer("Toyota", "Japan");
        Car car = new Car("Camry", 60, 0.1, m);

        car.drive(100);
        assertEquals(50.0, car.getFuel(), 0.001);
        assertEquals(100.0, car.getMileage(), 0.001);
    }

    @Test
    public void testDriveThrowsExceptionIfNotEnoughFuel() {
        Manufacturer m = new Manufacturer("Toyota", "Japan");
        Car car = new Car("Camry", 10, 1, m);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> car.drive(20));
        assertTrue(exception.getMessage().contains("Not enough fuel for the trip."));
    }

    @Test
    public void testRefuelToFullWorksCorrectly() {
        Manufacturer m = new Manufacturer("Toyota", "Japan");
        Car car = new Car("Supra", 50, 0.1, m);
        car.drive(100);
        car.refuelToFull();
        assertEquals(50.0, car.getFuel(), 0.001);
    }
}
