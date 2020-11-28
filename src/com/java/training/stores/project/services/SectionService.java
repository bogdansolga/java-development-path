package com.java.training.stores.project.services;

import com.java.training.stores.project.models.Section;
import com.java.training.stores.project.models.Store;

import java.util.*;

public class SectionService {

    public static void add(Section section, String storeName, List<Store> storeList) {
        Optional<Store> store = StoreService.searchStore(storeName, storeList);
        if (!store.isPresent()) {
            return;
        }

        store.get().getSections().add(section);

        StoreService.saveStores(storeList);
        System.out.println("[Section] " + section.getName() + " was added successfully");
    }

    public static void update(Section section, String storeName, String sectionName, List<Store> storeList) {
        // one way to unwrap the Optional - less disruptive

        Optional<Store> store = StoreService.searchStore(storeName, storeList);
        if (!store.isPresent()) {
            return;
        }

        Optional<Section> searchedSection = searchSection(sectionName, store.get().getSections());
        if (!searchedSection.isPresent()) {
            return;
        }

        searchedSection.get().setId(section.getId());
        searchedSection.get().setName(section.getName());

        StoreService.saveStores(storeList);

        System.out.println("[Section] " + sectionName + " is now called " + section.getName());
    }

    public static void delete(String sectionName, String storeName, List<Store> storeList) {
        // another way to unwrap the Optional --> short circuiting operation, interrupts the execution flow

        Store store = StoreService.searchStore(storeName, storeList)
                                  .orElseThrow(() -> new IllegalArgumentException("There is no store"));

        Section searchedSection = searchSection(sectionName, store.getSections())
                                        .orElseThrow(() -> new IllegalArgumentException("There is no section"));

        store.getSections()
             .remove(searchedSection);

        StoreService.saveStores(storeList);

        System.out.println("[Section] " + sectionName + " was deleted");
    }


    public static Optional<Section> searchSection(String sectionName, Set<Section> sectionList) {
        return sectionList.stream()
                          .filter(section -> section.getName()
                                                    .compareTo(sectionName) == 0)
                          .findFirst();
    }

    public static void createSection() {
        Optional<Store> searchedStore;

        String chosenStore = StoreService.readStore();

        if (chosenStore.equals("")) return;

        if (!(searchedStore = StoreService.searchStore(chosenStore, StoreService.getStores())).isPresent()) {
            System.out.println("Store is not existent!");
            return;
        }

        if (searchedStore.get().getSections() == null) searchedStore.get().setSections(new HashSet<>());

        System.out.println("Choose a section name");
        String sectionName = UtilService.getScanner().next();

        if (searchSection(sectionName, searchedStore.get().getSections()).isPresent()) {
            System.out.println("Section is already existent");
            return;
        }

        add(new Section(0, sectionName), chosenStore, StoreService.getStores());
    }

    public static String readSection(Store store) {
        if (store.getSections() == null || store.getSections().isEmpty()) {
            System.out.println("No sections");
            store.setSections(new HashSet<>());
            return "";
        }

        System.out.println("Type the wanted section: ");
        for (Section loopSection : store.getSections()) {
            System.out.println(loopSection.getName() + "\t");
        }

        return UtilService.getScanner().next();
    }

    public static void editSection() {
        String chosenStore = StoreService.readStore();

        if (chosenStore.equals("")) return;

        Optional<Store> store = StoreService.searchStore(chosenStore, StoreService.getStores());
        if (!store.isPresent()) {
            System.out.println("Store is not existent!");
            return;
        }

        String chosenSection = readSection(store.get());

        if (chosenSection.equals("")) return;

        if (!searchSection(chosenSection, store.get().getSections()).isPresent()) {
            System.out.println("Section is not existent");
            return;
        }

        System.out.println("Choose a new name:");
        String newData = UtilService.getScanner().next();

        update(new Section(0, newData), chosenStore, chosenSection, StoreService.getStores());
    }

    public static void deleteSection() {
        String chosenStore = StoreService.readStore();

        if (chosenStore.equals("")) return;

        Optional<Store> store = StoreService.searchStore(chosenStore, StoreService.getStores());
        if (!store.isPresent()) {
            System.out.println("Store is not existent!");
            return;
        }

        String chosenSection = readSection(store.get());

        if (chosenSection.equals("")) return;

        if (!searchSection(chosenSection, store.get().getSections()).isPresent()) {
            System.out.println("Section is not existent!");
            return;
        }

        delete(chosenSection, chosenStore, StoreService.getStores());
    }

    public static void display(Store store) {
        if (store.getSections() == null) {
            System.out.println("Couldn't find any sections of this storage");
            return;
        }
        store.getSections().forEach(section -> System.out.println(section.getName()));
    }

    public static void displaySections() {
        String chosenStore = StoreService.readStore();

        if (chosenStore.equals("")) return;

        if (!StoreService.searchStore(chosenStore, StoreService.getStores()).isPresent()) {
            System.out.println("Store is not existent!");
            return;
        }

        Optional<Store> store = StoreService.searchStore(chosenStore, StoreService.getStores());
        display(store.get());
    }
}
