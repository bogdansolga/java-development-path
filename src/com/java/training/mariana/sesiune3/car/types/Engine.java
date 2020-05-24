package com.java.training.mariana.sesiune3.car.types;

import java.util.Date;

public class Engine {

    private double engineCapacity;

    public Engine(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public double getCapacity() {
        return engineCapacity;
    }

    public void inspectEngine(Date date){
        System.out.println("Engine checked on " + date);
    }

    @Override
    public String toString() {
        return "{" +
                "capacity=" + engineCapacity +
                '}';
    }
}
