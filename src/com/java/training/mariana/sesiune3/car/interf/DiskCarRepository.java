package com.java.training.mariana.sesiune3.car.interf;

import com.java.training.mariana.sesiune3.car.types.AbstractCar;
import com.java.training.mariana.sesiune3.car.types.Engine;

public class DiskCarRepository implements CarRepository {
    @Override
    public AbstractCar getCar() {
        return new AbstractCar("BMW Disk", "polo", "red",4,5,190,
                "battery",new Engine(2.0),13000) {
            @Override
            public int getPrice() {
                return this.price;
            }

            @Override
            public double kmDriven() {
                return 0;
            }

            @Override
            public void displayFeatures() {
                System.out.println("Disk car repository");
            }
        };
    }
}
