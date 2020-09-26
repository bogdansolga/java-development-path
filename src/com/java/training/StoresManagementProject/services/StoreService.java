package com.java.training.StoresManagementProject.services;


import com.java.training.StoresManagementProject.Main;
import com.java.training.StoresManagementProject.models.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class StoreService {
    public static void add(final String fileName, Store store, List<Store> storeList) {
        if (storeList == null) storeList = new ArrayList<>();

        if (searchStore(store.getName(),storeList) != null){
            System.out.println("Store already exist!");
            return;
        }
        storeList.add(store);
        UtilService.writeInXML(fileName.concat(".xml"),storeList);
        UtilService.writeInCSV(fileName.concat(".csv"),storeList);
    }

    public static void update(final String fileName,String storeName, Store store, List<Store> storeList){
        Store searchedStore = searchStore(storeName,storeList);
        if (searchedStore == null) return;

        searchedStore.setId(store.getId());
        searchedStore.setName(store.getName());
        UtilService.writeInXML(fileName.concat(".xml"),storeList);
        UtilService.writeInCSV(fileName.concat(".csv"),storeList);
    }

    public static void delete(final String fileName, String name, List<Store> storeList){
        Store searchedStore = searchStore(name,storeList);
        if (searchedStore == null) return;

        storeList.remove(searchedStore);
        UtilService.writeInXML(fileName.concat(".xml"),storeList);
        UtilService.writeInCSV(fileName.concat(".csv"),storeList);
    }

    public static Store searchStore(String name,List<Store> storeList) {
        try {
            return storeList.stream().filter(item -> item.getName().compareTo(name) == 0).findFirst().get();
        } catch (NoSuchElementException exception) {
            System.out.println("Could not find the store with the specified name;");
        }
        return null;
    }

    public static void createStore(){
        System.out.println("Please create a store");
        String storeName = UtilService.getScanner().next();
        add(Main.getFileName(),new Store(0,storeName),Main.getStores());
    }

    public static String readStore(){
        String chosenStore = "";
        System.out.println("Please enter a store:");
        for (Store store: Main.getStores()){
            System.out.print(store.getName()+"\t");
        }
        chosenStore = UtilService.getScanner().next();
        return chosenStore;
    }

    public static void editStore(){
        String newData = "";
        String chosenStore = readStore();
        System.out.println("Choose a new name:");
        newData = UtilService.getScanner().next();
        update(Main.getFileName(),chosenStore,new Store(0,newData),Main.getStores());
    }

    public static void deleteStore(){
        String chosenStore = readStore();
        delete(Main.getFileName(),chosenStore, Main.getStores());
    }

    public static void display(List<Store> storeList){
        storeList.forEach(System.out::println);
    }

    public static void displayStores(){
        display(Main.getStores());
    }
}
