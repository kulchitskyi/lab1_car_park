import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import com.example.autopark.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class AutoparkIOTest {

    @Test
    public void testSerializeAndDeserializeAutoparkSortedByModel(@TempDir Path tempDir) throws IOException {

        Autopark autopark = new Autopark();
        Manufacturer bmw = new Manufacturer("BMW", "Germany");
        Manufacturer audi = new Manufacturer("Audi", "Germany");
        
        Car car1 = new Car("X5", 70, 0.1, bmw);
        Car car2 = new Car("A4", 50, 0.08, audi);
        
        autopark.addCar(car1);
        autopark.addCar(car2);


        File tempFile = tempDir.resolve("test_autopark.json").toFile();


        AutoparkIO.serializeAutoparkSortedByModel(autopark, tempFile);
        assertTrue(tempFile.exists());
        assertTrue(tempFile.length() > 0);


        Autopark deserializedAutopark = AutoparkIO.deserializeAutopark(tempFile);
        assertNotNull(deserializedAutopark);
        assertEquals(2, deserializedAutopark.getCars().size());
        

        List<Car> cars = deserializedAutopark.getCars();
        assertTrue(cars.get(0).getModel().compareTo(cars.get(1).getModel()) < 0);
    }

    @Test
    public void testDeserializeNonExistentFile() {
        File nonExistentFile = new File("non_existent.json");
        assertThrows(IOException.class, () -> AutoparkIO.deserializeAutopark(nonExistentFile));
    }

    @Test
    public void testSerializeEmptyAutopark(@TempDir Path tempDir) throws IOException {
        Autopark emptyAutopark = new Autopark();
        File tempFile = tempDir.resolve("empty_autopark.json").toFile();

        AutoparkIO.serializeAutoparkSortedByModel(emptyAutopark, tempFile);
        
        Autopark deserializedAutopark = AutoparkIO.deserializeAutopark(tempFile);
        assertNotNull(deserializedAutopark);
        assertTrue(deserializedAutopark.getCars().isEmpty());
    }
}
