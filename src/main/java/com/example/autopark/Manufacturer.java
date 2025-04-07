package com.example.autopark;

import java.util.List;
import java.util.Objects;

public class Manufacturer {
    private String name;
    private String country;

    public Manufacturer() {
        
    }

    public Manufacturer(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public double getAverageMileage(List<Car> cars) {
        return cars.stream()
                .filter(c -> c.getManufacturer().equals(this))
                .mapToDouble(Car::getMileage)
                .average()
                .orElse(0.0);
    }

    public static Manufacturer create(String name, String country) {
        return new Manufacturer(name, country);
    }

    public void update(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public void delete() {
        this.name = null;
        this.country = null;
    }

    public long getNumberOfCars(List<Car> cars) {
        return cars.stream()
                .filter(c -> c.getManufacturer().equals(this))
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manufacturer)) return false;
        Manufacturer that = (Manufacturer) o;
        return Objects.equals(name, that.name) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }
}
