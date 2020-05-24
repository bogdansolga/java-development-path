package com.java.training.mariana.sesiune3.car.types;

public class Sedan  extends AbstractCar{

    private boolean isAutomatic;

    public Sedan(String brand, String model, String color, int nrOfSeats, int nrOfDoors, int topSpeed, String fuelType, Engine engine, int price) {
        super(brand, model, color, nrOfSeats, nrOfDoors, topSpeed, fuelType, engine, price);

        this.isAutomatic = isAutomatic;
    }

    public boolean isAutomatic() {
        return isAutomatic;
    }

    @Override
    public void displayFeatures() {
        System.out.println("Sedan car features are: " + super.toString());
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public double kmDriven() {
        return 0;
    }
}
