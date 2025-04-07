package com.example;

import com.example.autopark.*;

import java.io.File;


public class App
{
    public static void main(String[] args) throws Exception {
        
        System.out.println("\n****** Creating new car and manufacturer ******");
        Autopark autopark = new Autopark();
        Manufacturer toyota = Manufacturer.create("Nissan", "Japan");
        Car skyline = Car.create("Skyline", 60, 0.08, toyota);
        autopark.addCar(skyline);
        System.out.println("Created: " + skyline);


        System.out.println("\n****** Reading car info ******");
        System.out.println("Model: " + skyline.getModel());
        System.out.println("Fuel level: " + skyline.getFuel());
        System.out.println("Mileage: " + skyline.getMileage());
        System.out.println("Manufacturer: " + skyline.getManufacturer().getName());


        System.out.println("\n****** Updating car ******");
        skyline.update("Skyline gtr", 45.5, 1000.0, toyota);
        System.out.println("Updated car: " + skyline);


        System.out.println("\n****** Deleting car ******");
        skyline.delete();
        autopark.removeCar(skyline);
        System.out.println("Car deleted from autopark");
    }
}
