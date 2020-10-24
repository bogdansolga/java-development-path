package com.java.training.StoresManagementProject.services;

import com.java.training.StoresManagementProject.Main;

public class MenuService {

    public static void clearTrick() {
        for (int i = 1; i <= 20; i++)
            System.out.println("");
    }

    public static void pause() {
        System.out.println("");
        System.out.println("Press any key to go back to the menu..");
        UtilService.readValue();
    }

    public static void exit() {
        System.out.println("");
        System.out.println("Thank you for using our app!");
    }

    public static void secondaryMenuSelection(String option) {
        try {
            final String selected = UtilService.readValue();
            final int selectedValue = Integer.parseInt(selected);
            switch (selectedValue) {
                case 1:
                    //Option "Display" is selected
                    switch (option) {
                        case "Stores":
                            StoreService.displayStores();
                            break;
                        case "Sections":
                            SectionService.displaySections();
                            break;
                        case "Products":
                            ProductService.displayProducts();
                            break;
                    }
                    pause();
                    secondaryMenu(option);
                    break;
                case 2:
                    //Option "Create" is selected
                    switch (option) {
                        case "Stores":
                            StoreService.createStore();
                            break;
                        case "Sections":
                            SectionService.createSection();
                            break;
                        case "Products":
                            ProductService.createProduct();
                            break;
                    }
                    pause();
                    secondaryMenu(option);
                    break;
                case 3:
                    //Option "Edit" is selected
                    switch (option) {
                        case "Stores":
                            StoreService.editStore();
                            break;
                        case "Sections":
                            SectionService.editSection();
                            break;
                        case "Products":
                            ProductService.editProduct();
                            break;
                    }
                    pause();
                    secondaryMenu(option);
                    break;
                case 4:
                    //Option "Delete" is selected
                    switch (option) {
                        case "Stores":
                            StoreService.deleteStore();
                            break;
                        case "Sections":
                            SectionService.deleteSection();
                            break;
                        case "Products":
                            ProductService.deleteProduct();
                            break;
                    }
                    pause();
                    secondaryMenu(option);
                    break;
                case 5:
                    //Option "Back to Main Menu" is selected
                    mainMenu();
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage()+" - invalid input. Please select an option.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            secondaryMenuSelection(option);
        }
    }

    public static void secondaryMenu(String option) {
        clearTrick();
        System.out.println( option + " Menu:");
        System.out.println("1 - Display");
        System.out.println("2 - Create");
        System.out.println("3 - Edit");
        System.out.println("4 - Delete");
        System.out.println("5 - Back to Main Menu");
        secondaryMenuSelection(option);
    }

    public static void mainMenuSelection() {
        try {
            final String selected = UtilService.readValue();
            final int selectedValue = Integer.parseInt(selected);
            switch (selectedValue) {
                case 1:
                    //Option "Stores" is selected
                    secondaryMenu("Stores");
                    break;
                // loadStores(); createNewStore();
                case 2:
                    //Option "Sections" is selected
                    secondaryMenu("Sections");
                    break;
                case 3:
                    //Option "Products" is selected
                    secondaryMenu("Products");
                    break;
                case 4:
                    //Option "Display" is selected
                    //displayAll();
                    System.out.println(Main.getStores());
                    pause();
                    mainMenu();
                    break;
                case 5:
                    //Option "Export CSV" is selected
                    //exportCSV();
                    pause();
                    mainMenu();
                    break;
                case 6:
                    //Option "Archive CSV as Zip" is selected
                    //archiveAsZip();
                    pause();
                    mainMenu();
                    break;
                case 7:
                    //Option "Exit" is selected
                    exit();
            }
        }catch(NumberFormatException e) {
            System.out.println(e.getMessage()+" - invalid input. Please select an option.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            mainMenuSelection();
        }
    }

    public static void mainMenu() {
        clearTrick();
        System.out.println("Welcome to Stores Management App");
        System.out.println("Choose an option:");
        System.out.println("1 - Stores");
        System.out.println("2 - Sections");
        System.out.println("3 - Products");
        System.out.println("4 - Display all data");
        System.out.println("5 - Export CSV");
        System.out.println("6 - Archive CSV as Zip");
        System.out.println("7 - Exit");
        mainMenuSelection();
    }
}
