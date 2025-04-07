package com.example.autopark;

import java.util.Objects;

public class Car {
    private String model;
    private double fuel;
    private double mileage;
    private final double fuelConsumptionPerKm;
    private final double maxFuel;
    private Manufacturer manufacturer;

    public Car() {
        this.maxFuel = 0;
        this.fuelConsumptionPerKm = 0;
    }

    public Car(String model, double maxFuel, double fuelConsumptionPerKm, Manufacturer manufacturer) {
        this.model = model;
        this.maxFuel = maxFuel;
        this.fuelConsumptionPerKm = fuelConsumptionPerKm;
        this.fuel = maxFuel;
        this.mileage = 0;
        this.manufacturer = manufacturer;
    }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public double getFuel() { return fuel; }
    public double getMileage() { return mileage; }

    public static Car create(String model, double maxFuel, double fuelConsumptionPerKm, Manufacturer manufacturer) {
        return new Car(model, maxFuel, fuelConsumptionPerKm, manufacturer);
    }
    

    public void update(String model, double fuel, double mileage, Manufacturer manufacturer) {
        if (fuel < 0 || fuel > maxFuel) {
            throw new IllegalArgumentException("Fuel level must be between 0 and max fuel capacity");
        }
        if (mileage < 0) {
            throw new IllegalArgumentException("Mileage cannot be negative");
        }
        this.model = model;
        this.fuel = fuel;
        this.mileage = mileage;
        this.manufacturer = manufacturer;
    }
    

    public void delete() {
        this.model = null;
        this.fuel = 0;
        this.mileage = 0;
        this.manufacturer = null;
    }


    public Manufacturer getManufacturer() { return manufacturer; }
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void drive(double distance) {
        double neededFuel = distance * fuelConsumptionPerKm;
        if (neededFuel > fuel) {
            throw new IllegalArgumentException("Not enough fuel for the trip.");
        }
        fuel -= neededFuel;
        mileage += distance;
    }

    public void refuel(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Fuel amount must be positive.");
        }
        fuel = Math.min(maxFuel, fuel + amount);
    }

    public void refuelToFull() {
        fuel = maxFuel;
    }
    
    @Override
    public String toString() {
        return String.format("Car: %s (Manufacturer: %s)\n" +
                           "Current fuel level: %.2f liters\n" + 
                           "Total mileage: %.2f km",
                           model,
                           manufacturer.getName(),
                           fuel,
                           mileage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return model.equals(car.model) && manufacturer.equals(car.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, manufacturer);
    }
}
