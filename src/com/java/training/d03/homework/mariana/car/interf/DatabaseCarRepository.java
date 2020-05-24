package com.java.training.d03.homework.mariana.car.interf;

import com.java.training.d03.homework.mariana.car.types.AbstractCar;
import com.java.training.d03.homework.mariana.car.types.Engine;

public class DatabaseCarRepository implements CarRepository {
    @Override
    public AbstractCar getCar() {
        return new AbstractCar("BMW Database", "another", "blue",6,5,190,
                "",new Engine(1.2),12000) {
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
                System.out.println("Database Car Repository");
            }
        };
    }
}
