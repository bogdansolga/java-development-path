package com.java.training.d03.homework.mariana.car.types;

public class Van extends AbstractCar {

    private boolean slidingDoor;

    public Van(String brand, String model, String color, int nrOfSeats, int nrOfDoors, int topSpeed, String fuelType, Engine engine, int price, boolean slidingDoor) {
        super(brand, model, color, nrOfSeats, nrOfDoors, topSpeed, fuelType, engine, price);

        this.slidingDoor = slidingDoor;
    }

    public boolean isSlidingDoor() {
        return slidingDoor;
    }

    @Override
    public void displayFeatures() {
        System.out.println("Van car features are: " + this.toString());
    }

    @Override
    public int getPrice() {
        return super.price;
    }

    @Override
    public double kmDriven() {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString() +
                "slidingDoor=" + slidingDoor +
                ", price=" + price +
                " " + super.getBrand();

    }
}
