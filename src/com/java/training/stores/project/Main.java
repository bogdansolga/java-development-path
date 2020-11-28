package com.java.training.stores.project;

import com.java.training.stores.project.services.MenuService;
import com.java.training.stores.project.services.ProductService;
import com.java.training.stores.project.services.SectionService;
import com.java.training.stores.project.services.StoreService;

public class Main {

    public static void main(String[] args) {
        StoreService storeService = new StoreService();
        SectionService sectionService = new SectionService(storeService); // dependency injection
        ProductService productService = new ProductService(storeService, sectionService);

        MenuService menuService = new MenuService();
        menuService.mainMenu(storeService, sectionService, productService);

        //DRY - Don't Repeat Yourself
        //SRP - Single Responsibility Principle
        //Isolation of Concerns

        //Don't make me think --> use smaller methods, to avoid the cognitive strain
    }
}
