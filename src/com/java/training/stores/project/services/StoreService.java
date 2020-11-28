package com.java.training.stores.project.services;

import com.java.training.stores.project.models.Store;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class StoreService {

    //TODO read it from a .properties file
    private static final String FILE_NAME = "Store";

    private final Set<Store> stores;

    public StoreService() {
        stores = new HashSet<>();
    }

    // CRUD
    //  C: create / save store
    //  R: displayOne, displayAll
    //  U: updateOne
    //  D: deleteOne

    public void add(Store store) {
        stores.add(store);

        saveStores();

        System.out.println("[Store] " + store.getName() + " was added successfully");
    }

    public void update(String storeName, Store store) {
        Optional<Store> searchedStore = searchStore(storeName);

        searchedStore.get().setId(store.getId());
        searchedStore.get().setName(store.getName());

        saveStores();

        System.out.println("[Store] " + storeName + " is now called " + store.getName());
    }

    public void delete(String name) {
        Optional<Store> searchedStore = searchStore(name);

        stores.remove(searchedStore.get());

        saveStores();

        System.out.println("[Store] " + name + " was deleted");
    }

    public Optional<Store> searchStore(String name) {
        return stores.stream()
                     .filter(item -> item.getName().compareTo(name) == 0)
                     .findFirst();
    }

    public void createStore() {
        System.out.println("Choose an unique deposit name");
        String storeName = UtilService.getScanner().next();

        if (searchStore(storeName).isPresent()) {
            System.out.println("Store already exist!");
            return;
        }

        add(new Store(0, storeName));
    }

    public String readStore() {
        if (stores.isEmpty()) {
            System.out.println("No stores");
            return "";
        }

        System.out.println("Type the wanted storage:");
        for (Store store : stores) {
            System.out.print(store.getName() + "\t");
        }

        return UtilService.getScanner().next();
    }

    public void editStore() {
        String chosenStore = readStore();

        if (chosenStore.equals("")) return;

        if (!searchStore(chosenStore).isPresent()) {
            System.out.println("Store is not existent!");
            return;
        }

        System.out.println("Choose a new name:");
        String newData = UtilService.getScanner().next();

        update(chosenStore, new Store(0, newData));
    }

    public void deleteStore() {
        String chosenStore = readStore();

        if (chosenStore.equals("")) return;

        if (!searchStore(chosenStore).isPresent()) {
            System.out.println("Store is not existent!");
            return;
        }

        delete(chosenStore);
    }

    public void display(List<Store> storeList) {
        if (storeList == null || storeList.isEmpty()) {
            System.out.println("No stores");
            return;
        }

        storeList.forEach((store) -> System.out.println(store.getName()));
    }

    public void displayStores() {
        display();
    }

    // encapsulation - only the StoreService 'knows' where and how the stores are saved
    // the other services just invoke saveStores --> it is the contract between them
    public void saveStores() {
        UtilService.writeInXML(FILE_NAME.concat(".xml"), stores);
        UtilService.writeInCSV(FILE_NAME.concat(".csv"), stores);
    }

    public Set<Store> getStores() {
        return stores;
    }
}
