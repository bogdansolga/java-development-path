package com.java.training.d03.homework.mariana.car.types;

public class FamilyCar extends AbstractCar {

    private boolean childSeat;
    private boolean sunRoof;

    private final static int DISCOUNT = 200;

    public FamilyCar(String brand, String model, String color, int topSpeed, String fuelType, Engine engine, int price, boolean childSeat, boolean sunRoof) {
        super(brand, model, color, 7, 5, topSpeed, fuelType, engine, price);

        this.childSeat = childSeat;
        this.sunRoof = sunRoof;
    }


    public boolean isChildSeat() {
        return childSeat;
    }

    public boolean isSunRoof() {
        return sunRoof;
    }

    @Override
    public void displayFeatures() {
        System.out.println("Family car features are: " + this.toString());
    }

    @Override
    public int getPrice() {
        return super.price - DISCOUNT;
    }

    @Override
    public double kmDriven() {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString() +
                "childSeat=" + childSeat +
                ", sunRoof=" + sunRoof +
                ", price=" + price;

    }



}
