package com.java.training.mariana.sesiune3.car.types;

public abstract class AbstractCar {

    private final String brand;

    private final String model; //polo

    private String color;

    private final int nrOfSeats;

    private final int nrOfDoors;

    private final int topSpeed;

    private final String fuelType;

    private final Engine engine;

    protected final int price;

    public AbstractCar(String brand, String model, String color, int nrOfSeats, int nrOfDoors,
                       int topSpeed, String fuelType, Engine engine, int price) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.nrOfSeats = nrOfSeats;
        this.nrOfDoors = nrOfDoors;
        this.topSpeed = topSpeed;
        this.fuelType = fuelType;
        this.engine = engine;
        this.price = price;
    }

   public void setColor(String color) {
        this.color = color;
   }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getNrOfSeats() {
        return nrOfSeats;
    }

    public int getNrOfDoors() {
        return nrOfDoors;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public String getFuelType() {
        return fuelType;
    }

    public Engine getEngine() {
        return engine;
    }

    public abstract int getPrice();
    public abstract double kmDriven();

    public abstract void displayFeatures();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractCar that = (AbstractCar) o;

        if (brand != null ? !brand.equals(that.brand) : that.brand != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        return engine != null ? engine.equals(that.engine) : that.engine == null;
    }

    @Override
    public int hashCode() {
        int result = brand != null ? brand.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (engine != null ? engine.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", nrOfSeats=" + nrOfSeats +
                ", nrOfDoors=" + nrOfDoors +
                ", topSpeed=" + topSpeed +
                ", fuelType='" + fuelType + '\'' +
                ", engine=" + engine +
                ", price=" + price;
    }
}
