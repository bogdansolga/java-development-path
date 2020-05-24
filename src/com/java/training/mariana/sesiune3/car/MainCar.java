package com.java.training.mariana.sesiune3.car;

import com.java.training.mariana.sesiune3.car.enums.CarType;
import com.java.training.mariana.sesiune3.car.types.*;

import java.util.*;

public class MainCar {

    public static void main(String[] args) {

        Engine carEngine2000 = new Engine(2.0);
        Engine carEngine1400 = new Engine(1.4);

        FamilyCar familyCar1 = new FamilyCar("Mercedes", "E70", "red", 150, "gas", carEngine2000, 7000, true, false);
        System.out.println(familyCar1.toString());


        FamilyCar familyCar2 = new FamilyCar("Mercedes", "E70", "black", 180, "diesel", carEngine2000, 15000, false, true);
        System.out.println(familyCar2.toString());

        colorService(familyCar2);

        System.out.println("----------------------------------------------------------------------------------");
        checkEquality(familyCar1, familyCar2);
        System.out.println("----------------------------------------------------------------------------------");

        Van vanCar1 = createVanCars(carEngine1400);

        System.out.println("----------------------------------------------------------------------------------");

        checkInstanceof(familyCar1, vanCar1);

        System.out.println("----------------------------------------------------------------------------------");

        enumType();

        System.out.println("----------------------------------------------------------------------------------");

        carHashMap(familyCar1, familyCar2, vanCar1);

        System.out.println("----------------------------------------------------------------------------------");



    }

    private static void carHashMap(FamilyCar familyCar1, FamilyCar familyCar2, Van vanCar1) {
        Map<String, AbstractCar> carMap = new HashMap<>();
        carMap.put("1",familyCar1);
        carMap.put("2",familyCar2);
        carMap.put("3", vanCar1);
        final Set<Map.Entry<String, AbstractCar>> entries = carMap.entrySet();
        for (Map.Entry<String, AbstractCar> entry : entries) {
            System.out.println(entry.getKey() + " - " + entry.getValue());

        }
    }

    private static void checkEquality(FamilyCar familyCar1, FamilyCar familyCar2) {
        System.out.println("Are the familyCar objects equal? " + familyCar1.equals(familyCar2));
    }

    private static void colorService(FamilyCar familyCar2) {
        ServiceCar serviceCar = new ServiceCar();
        serviceCar.changeColor(familyCar2,"green");
    }

    private static Van createVanCars(Engine carEngine1400) {
        Van vanCar1 = new Van("VW","golf","blue",5, 4,180,"diesel",carEngine1400,20000, false);
        System.out.println(vanCar1.toString());

        Van vanCar2 = new Van("VW","golf","black",9, 4,150,"diesel",carEngine1400,20000, true);
        System.out.println(vanCar2.toString());
        return vanCar1;
    }

    private static void checkInstanceof(FamilyCar familyCar1, Van vanCar1) {
        List<AbstractCar> cars = new ArrayList<>();

        cars.add(familyCar1);
        cars.add(vanCar1);

        for(AbstractCar car : cars){
            if(car instanceof Van){
                Van vanCar = (Van) car;
                car.displayFeatures();
            }
        }
    }

    private static void enumType() {
        CarType carType[] = CarType.values();
        for (CarType type : carType) {
            type.getCarType().displayFeatures();
        }
    }


}
