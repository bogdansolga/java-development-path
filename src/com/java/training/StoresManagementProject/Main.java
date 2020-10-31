package com.java.training.StoresManagementProject;
import com.java.training.StoresManagementProject.models.Store;
import com.java.training.StoresManagementProject.services.MenuService;
import com.java.training.StoresManagementProject.services.UtilService;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String fileName = "Store";
    private static List<Store> stores;
    public static void main(String[] args) {
        init();
        MenuService.mainMenu();
    }
    private static void init(){
        stores = UtilService.getAllData(fileName);
        if (stores == null) stores = new ArrayList<>();

    }
    public static List<Store> getStores() {
        return stores;
    }

    public static void setStores(List<Store> stores) {
        Main.stores = stores;
    }

    public static String getFileName() {
        return fileName;
    }
}
