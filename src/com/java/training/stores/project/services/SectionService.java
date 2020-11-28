package com.java.training.stores.project.services;

import com.java.training.stores.project.models.Section;
import com.java.training.stores.project.models.Store;

import java.util.*;

public class SectionService {

    private final StoreService storeService;

    public SectionService(StoreService storeService) {
        this.storeService = storeService;
    }

    public void add(Section section, String storeName) {
        Optional<Store> store = storeService.searchStore(storeName);
        if (!store.isPresent()) {
            return;
        }

        store.get().getSections().add(section);

        storeService.saveStores();
        System.out.println("[Section] " + section.getName() + " was added successfully");
    }

    public void update(Section section, String storeName, String sectionName) {
        // one way to unwrap the Optional - less disruptive

        Optional<Store> store = storeService.searchStore(storeName);
        if (!store.isPresent()) {
            return;
        }

        Optional<Section> searchedSection = searchSection(sectionName, store.get().getSections());
        if (!searchedSection.isPresent()) {
            return;
        }

        searchedSection.get().setId(section.getId());
        searchedSection.get().setName(section.getName());

        storeService.saveStores();

        System.out.println("[Section] " + sectionName + " is now called " + section.getName());
    }

    public void delete(String sectionName, String storeName) {
        // another way to unwrap the Optional --> short circuiting operation, interrupts the execution flow

        Store store = storeService.searchStore(storeName)
                                  .orElseThrow(() -> new IllegalArgumentException("There is no store"));

        Section searchedSection = searchSection(sectionName, store.getSections())
                                        .orElseThrow(() -> new IllegalArgumentException("There is no section"));

        store.getSections()
             .remove(searchedSection);

        storeService.saveStores();

        System.out.println("[Section] " + sectionName + " was deleted");
    }


    public Optional<Section> searchSection(String sectionName, Set<Section> sectionList) {
        return sectionList.stream()
                          .filter(section -> section.getName()
                                                    .compareTo(sectionName) == 0)
                          .findFirst();
    }

    public void createSection() {
        Optional<Store> searchedStore;

        String chosenStore = storeService.readStore();

        if (chosenStore.equals("")) return;

        if (!(searchedStore = storeService.searchStore(chosenStore)).isPresent()) {
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

        add(new Section(0, sectionName), chosenStore);
    }

    public String readSection(Store store) {
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

    public void editSection() {
        String chosenStore = storeService.readStore();

        if (chosenStore.equals("")) return;

        Optional<Store> store = storeService.searchStore(chosenStore);
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

        update(new Section(0, newData), chosenStore, chosenSection);
    }

    public void deleteSection() {
        String chosenStore = storeService.readStore();

        if (chosenStore.equals("")) return;

        Optional<Store> store = storeService.searchStore(chosenStore);
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

        delete(chosenSection, chosenStore);
    }

    public void display(Store store) {
        if (store.getSections() == null) {
            System.out.println("Couldn't find any sections of this storage");
            return;
        }
        store.getSections().forEach(section -> System.out.println(section.getName()));
    }

    public void displaySections() {
        String chosenStore = storeService.readStore();

        if (chosenStore.equals("")) return;

        if (!storeService.searchStore(chosenStore).isPresent()) {
            System.out.println("Store is not existent!");
            return;
        }

        Optional<Store> store = storeService.searchStore(chosenStore);
        display(store.get());
    }
}
