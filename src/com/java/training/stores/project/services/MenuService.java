package com.java.training.stores.project.services;

import com.java.training.stores.project.exceptions.InvalidNumericInputException;

public class MenuService {

    private StoreService storeService;

    private SectionService sectionService;

    private ProductService productService;

    public void clearTrick() {
        for (int i = 1; i <= 20; i++)
            System.out.println("");
    }

    public void pause() {
        System.out.println("");
        System.out.println("Press any key to go back to the menu..");
        UtilService.readValue();
    }

    public void exit() {
        System.out.println("");
        System.out.println("Thank you for using our app!");
    }

    public void secondaryMenuSelection(String option) {
        try {
            final String selected = UtilService.readValue();
            if (UtilService.isNumeric(selected)) {
                final int selectedValue = Integer.parseInt(selected);

                //FIXME use a switch instead of several if / else blocks
                if (selectedValue == 1) {
                    //Option "Display" is selected
                    switch (option) {
                        case "Stores":
                            storeService.displayStores();
                            break;
                        case "Sections":
                            sectionService.displaySections();
                            break;
                        case "Products":
                            productService.displayProducts();
                            break;
                    }
                    pause();
                    secondaryMenu(option);
                } else if (selectedValue == 2) {
                    //Option "Create" is selected
                    switch (option) {
                        case "Stores":
                            storeService.createStore();
                            break;
                        case "Sections":
                            sectionService.createSection();
                            break;
                        case "Products":
                            productService.createProduct();
                            break;
                    }
                    pause();
                    secondaryMenu(option);
                } else if (selectedValue == 3) {
                    //Option "Edit" is selected
                    switch (option) {
                        case "Stores":
                            storeService.editStore();
                            break;
                        case "Sections":
                            sectionService.editSection();
                            break;
                        case "Products":
                            productService.editProduct();
                            break;
                    }
                    pause();
                    secondaryMenu(option);
                } else if (selectedValue == 4) {
                    //Option "Delete" is selected
                    switch (option) {
                        case "Stores":
                            storeService.deleteStore();
                            break;
                        case "Sections":
                            sectionService.deleteSection();
                            break;
                        case "Products":
                            productService.deleteProduct();
                            break;
                    }
                    pause();
                    secondaryMenu(option);
                } else if (selectedValue == 5) {
                    //Option "Back to Main Menu" is selected
                    mainMenu(storeService, sectionService, productService);
                } else throw new InvalidNumericInputException();
            }
        } catch (InvalidNumericInputException e) {
            System.out.println(e.getMessage());
            takeANap();
            secondaryMenuSelection(option);
        }
    }

    public void secondaryMenu(String option) {
        clearTrick();
        System.out.println(option + " Menu:");
        System.out.println("1 - Display");
        System.out.println("2 - Create");
        System.out.println("3 - Edit");
        System.out.println("4 - Delete");
        System.out.println("5 - Back to Main Menu");
        secondaryMenuSelection(option);
    }

    public void mainMenuSelection() {
        try {
            switch (UtilService.readInteger()) {
                case 1:
                    secondaryMenu("Stores");
                    break;

                case 2:
                    secondaryMenu("Sections");
                    break;

                case 3:
                    secondaryMenu("Products");
                    break;

                case 4:
                    UtilService.displayStock(storeService.getStores());
                    pause();
                    mainMenu(storeService, sectionService, productService);
                    break;

                case 5:
                    UtilService.archiveCsvAsZip();
                    pause();
                    mainMenu(storeService, sectionService, productService);
                    break;

                case 6:
                    UtilService.archiveXmlAsZip();
                    pause();
                    mainMenu(storeService, sectionService, productService);
                    break;

                case 7:
                    exit();
            }
        } catch (InvalidNumericInputException e) {
            System.out.println(e.getMessage());
            takeANap();
            mainMenuSelection();
        }
    }

    private void takeANap() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void mainMenu(StoreService storeService, SectionService sectionService, ProductService productService) {
        this.storeService = storeService;
        this.sectionService = sectionService;
        this.productService = productService;

        clearTrick();
        System.out.println("Welcome to Stores Management App");
        System.out.println("Choose an option:");
        System.out.println("1 - Stores");
        System.out.println("2 - Sections");
        System.out.println("3 - Products");
        System.out.println("4 - Display all data");
        System.out.println("5 - Archive CSV as Zip");
        System.out.println("6 - Archive XML as Zip");
        System.out.println("7 - Exit");
        mainMenuSelection();
    }
}
