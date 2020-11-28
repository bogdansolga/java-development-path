package com.java.training.stores.project.services;

import com.java.training.stores.project.models.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StoreService {

    private static final String FILE_NAME = "Store";

    private static List<Store> stores;

    // executed when the class is initialised for the 1st time, before the constructor
    static {
        stores = UtilService.getAllData(FILE_NAME + ".xml");
        if (stores == null) stores = new ArrayList<>();
    }

    public static List<Store> getStores() {
        return stores;
    }

    public static void setStores(List<Store> stores) {
        StoreService.stores = stores;
    }

    public static void add(Store store, List<Store> storeList) {
        storeList.add(store);

        saveStores(storeList);

        System.out.println("[Store] " + store.getName() + " was added successfully");
    }

    public static void update(String storeName, Store store, List<Store> storeList) {
        Optional<Store> searchedStore = searchStore(storeName, storeList);

        searchedStore.get().setId(store.getId());
        searchedStore.get().setName(store.getName());

        saveStores(storeList);

        System.out.println("[Store] " + storeName + " is now called " + store.getName());
    }

    public static void delete(String name, List<Store> storeList) {
        Optional<Store> searchedStore = searchStore(name, storeList);

        storeList.remove(searchedStore.get());

        saveStores(storeList);

        System.out.println("[Store] " + name + " was deleted");
    }

    public static Optional<Store> searchStore(String name, List<Store> storeList) {
        return storeList.stream().filter(item -> item.getName().compareTo(name) == 0).findFirst();
    }

    public static void createStore() {
        if (getStores() == null) setStores(new ArrayList<>());

        System.out.println("Choose an unique deposit name");
        String storeName = UtilService.getScanner().next();

        if (searchStore(storeName, getStores()).isPresent()) {
            System.out.println("Store already exist!");
            return;
        }

        add(new Store(0, storeName), getStores());
    }

    public static String readStore() {
        if (getStores() == null || getStores().isEmpty()) {
            System.out.println("No stores");
            setStores(new ArrayList<>());
            return "";
        }

        System.out.println("Type the wanted storage:");
        for (Store store : getStores()) {
            System.out.print(store.getName() + "\t");
        }

        return UtilService.getScanner().next();
    }

    public static void editStore() {
        String chosenStore = readStore();

        if (chosenStore.equals("")) return;

        if (!searchStore(chosenStore, getStores()).isPresent()) {
            System.out.println("Store is not existent!");
            return;
        }

        System.out.println("Choose a new name:");
        String newData = UtilService.getScanner().next();

        update(chosenStore, new Store(0, newData), getStores());
    }

    public static void deleteStore() {
        String chosenStore = readStore();

        if (chosenStore.equals("")) return;

        if (!searchStore(chosenStore, getStores()).isPresent()) {
            System.out.println("Store is not existent!");
            return;
        }

        delete(chosenStore, getStores());
    }

    public static void display(List<Store> storeList) {
        if (storeList == null || storeList.isEmpty()) {
            System.out.println("No stores");
            return;
        }

        storeList.forEach((store) -> System.out.println(store.getName()));
    }

    public static void displayStores() {
        display(getStores());
    }

    // encapsulation - only the StoreService 'knows' where and how the stores are saved
    // the other services just invoke saveStores --> it is the contract between them
    public static void saveStores(List<Store> stores) {
        UtilService.writeInXML(FILE_NAME.concat(".xml"), stores);
        UtilService.writeInCSV(FILE_NAME.concat(".csv"), stores);
    }
}
