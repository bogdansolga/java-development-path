package com.java.training.mariana.sesiune3.car;


import com.java.training.mariana.sesiune3.car.interf.CarRepository;
import com.java.training.mariana.sesiune3.car.interf.DiskCarRepository;


public class MainCarInterf {
    public static void main(String[] args) {
        CarRepository repository = new DiskCarRepository();
        System.out.println(repository.getCar());
        repository.getCar().displayFeatures();


    }


}
