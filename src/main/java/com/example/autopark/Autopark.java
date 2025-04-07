package com.example.autopark;

import java.util.ArrayList; 
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.util.Collection;

public class Autopark {
    private final Map<String, Car> cars = new HashMap<>();

    public void addCar(Car car) {
        if (cars.containsKey(car.getModel())) {
            throw new IllegalArgumentException("Авто з такою моделлю вже існує.");
        }
        cars.put(car.getModel(), car);
    }

    public void setCars(List<Car> carList) {
        cars.clear();
        for (Car car : carList) {
            cars.put(car.getModel(), car);
        }
    }

    public void removeCar(Car car) {
        cars.remove(car.getModel());
    }

    public Car getCar(String model) {
        return cars.get(model);
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars.values());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Autopark)) return false;
        Autopark autopark = (Autopark) o;
        return cars.equals(autopark.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cars);
    }
}
