package com.java.training.d03.homework.mariana.car.enums;

import com.java.training.d03.homework.mariana.car.types.*;

public enum CarType {
        FC("Family Car") {
            @Override
            public FamilyCar getCarType() {
                return new FamilyCar("Ford",
                        "focus",
                        "white",
                        170,
                        "diesel",
                        new Engine(2),
                        7000,
                        false,
                        false);
            }
        },
        V("Van") {
            @Override
            public Van getCarType() {
                return new Van("VW",
                        "vw",
                        "grey",
                        9,
                        5,
                        170,
                        "gas",
                        new Engine(2.0),
                        9000,
                        true);
            }
        },
        S("Sedan") {
            @Override
            public Sedan getCarType() {
                return new Sedan("BMW",
                        "sedan",
                        "red",
                        5,
                        5,
                        220,
                        "diesel",
                        new Engine(2.0),
                        11500);
            }
        };

        private final String name;

        CarType(String name) {
            this.name = name;
        }

        public  String getName(){
            return name;
        }

        public abstract AbstractCar getCarType();
}
