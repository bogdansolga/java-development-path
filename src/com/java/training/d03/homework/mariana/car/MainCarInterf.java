package com.java.training.d03.homework.mariana.car;

import com.java.training.d03.homework.mariana.car.interf.CarRepository;
import com.java.training.d03.homework.mariana.car.interf.DiskCarRepository;

public class MainCarInterf {
    public static void main(String[] args) {
        CarRepository repository = new DiskCarRepository();
        System.out.println(repository.getCar());
        repository.getCar().displayFeatures();
    }
}
