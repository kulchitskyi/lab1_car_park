import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.example.autopark.Autopark;
import com.example.autopark.Car;
import com.example.autopark.Manufacturer;

public class ManufacturerTest {

    @Test
    public void testAddAndRemoveCar() {
        Autopark autopark = new Autopark();
        Manufacturer m = new Manufacturer("Mazda", "Japan");
        Car car = new Car("RX-7", 55, 0.08, m);

        autopark.addCar(car);
        assertEquals(1, autopark.getCars().size());

        autopark.removeCar(car);
        assertEquals(0, autopark.getCars().size());
    }

    @Test
    public void testGetTotalMileage() {
        Autopark autopark = new Autopark();
        Manufacturer m = new Manufacturer("Mazda", "Japan");
        Car car1 = new Car("CX-5", 55, 0.08, m);
        Car car2 = new Car("3", 50, 0.07, m);

        autopark.addCar(car1);
        autopark.addCar(car2);

        car1.drive(100);
        car2.drive(50);

        assertEquals(75, m.getAverageMileage(autopark.getCars()), 0.001);
    }
    
    @Test
    public void testManufacturerEquality() {
        Manufacturer m1 = new Manufacturer("Toyota", "Japan");
        Manufacturer m2 = new Manufacturer("Toyota", "Japan");
        Manufacturer m3 = new Manufacturer("Honda", "Japan");

        assertEquals(m1, m2);
        assertNotEquals(m1, m3);
    }

    @Test
    public void testGetNumberOfCars() {
        Autopark autopark = new Autopark();
        Manufacturer toyota = new Manufacturer("Toyota", "Japan");
        Manufacturer honda = new Manufacturer("Honda", "Japan");

        Car car1 = new Car("Camry", 60, 0.06, toyota);
        Car car2 = new Car("Mark2", 45, 0.05, toyota);
        Car car3 = new Car("Civic", 50, 0.06, honda);

        autopark.addCar(car1);
        autopark.addCar(car2);
        autopark.addCar(car3);

        assertEquals(2, toyota.getNumberOfCars(autopark.getCars()));
        assertEquals(1, honda.getNumberOfCars(autopark.getCars()));
    }

}
