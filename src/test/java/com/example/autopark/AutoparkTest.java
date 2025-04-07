import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.example.autopark.*;
import java.util.Arrays;
import java.util.List;

public class AutoparkTest {

    @Test
    public void testAddAndRemoveCar() {
        Autopark autopark = new Autopark();
        Manufacturer m = new Manufacturer("Mazda", "Japan");
        Car car = new Car("CX-5", 55, 0.08, m);

        autopark.addCar(car);
        assertEquals(1, autopark.getCars().size());

        autopark.removeCar(car);
        assertEquals(0, autopark.getCars().size());
    }

    @Test
    public void testAddDuplicateCar() {
        Autopark autopark = new Autopark();
        Manufacturer m = new Manufacturer("Honda", "Japan");
        Car car = new Car("Civic", 45, 0.06, m);

        autopark.addCar(car);
        assertThrows(IllegalArgumentException.class, () -> autopark.addCar(car));
    }

    @Test 
    public void testGetNonExistentCar() {
        Autopark autopark = new Autopark();
        assertNull(autopark.getCar("NonExistentModel"));
    }


    @Test
    public void testSetCars() {
        Autopark autopark = new Autopark();
        Manufacturer toyota = new Manufacturer("Toyota", "Japan");
        List<Car> carList = Arrays.asList(
            new Car("Camry", 60, 0.06, toyota),
            new Car("Corolla", 45, 0.05, toyota)
        );

        autopark.setCars(carList);
        assertEquals(2, autopark.getCars().size());
        assertNotNull(autopark.getCar("Camry"));
        assertNotNull(autopark.getCar("Corolla"));
    }
}
