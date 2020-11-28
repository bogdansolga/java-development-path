package com.java.training.stores.project;

import com.java.training.stores.project.services.MenuService;

public class Main {

    public static void main(String[] args) {
        MenuService.mainMenu();

        //DRY - Don't Repeat Yourself
        //SRP - Single Responsibility Principle
        //Isolation of Concerns

        //Don't make me think --> use smaller methods, to avoid the cognitive strain
    }
}
