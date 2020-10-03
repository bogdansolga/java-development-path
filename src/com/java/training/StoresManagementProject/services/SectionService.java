package com.java.training.StoresManagementProject.services;


import com.java.training.StoresManagementProject.Main;
import com.java.training.StoresManagementProject.models.Section;
import com.java.training.StoresManagementProject.models.Store;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class SectionService {
    public static void add(final String fileName, Section section, String storeName, List<Store> storeList){
        Store store = StoreService.searchStore(storeName, storeList);
        if (store == null) return;
        if (store.getSections() == null) store.setSections(new HashSet<>());

        if (searchSection(section.getName(), store.getSections()) != null){
            System.out.println("Section already exist!");
            return;
        }
        store.getSections().add(section);
        UtilService.writeInXML(fileName.concat(".xml"), storeList);
        UtilService.writeInCSV(fileName.concat(".csv"), storeList);
    }

    public static void update(final String fileName, Section section, String storeName, String sectionName, List<Store> storeList){
        Store store = StoreService.searchStore(storeName, storeList);
        if (store == null) return;

        Section searchedSection = searchSection(sectionName, store.getSections());
        if (searchedSection == null) return;
        searchedSection.setId(section.getId());
        searchedSection.setName(section.getName());
        UtilService.writeInXML(fileName.concat(".xml"), storeList);
        UtilService.writeInCSV(fileName.concat(".csv"), storeList);
    }

    public static void delete(final String fileName, String sectionName, String storeName, List<Store> storeList){
        Store store = StoreService.searchStore(storeName, storeList);
        if (store == null) return;
        Section searchedSection = searchSection(sectionName, store.getSections());
        if (searchedSection == null) return;
        store.getSections().remove(searchedSection);
        UtilService.writeInXML(fileName.concat(".xml"), storeList);
        UtilService.writeInCSV(fileName.concat(".csv"), storeList);
    }


    public static Section searchSection(String sectionName, Set<Section> sectionList) {
        try {
            return sectionList.stream().filter(section -> section.getName().compareTo(sectionName) == 0).findFirst().get();
        } catch (NoSuchElementException exception) {
            System.out.println("Could not find the section with the specified name;");
        }
        return null;
    }

    public static void createSection(){
        String chosenStore = "";
        String sectionName="";
        chosenStore = StoreService.readStore();
        if (StoreService.searchStore(chosenStore, Main.getStores()) == null) return;
        System.out.println("Create a section");
        sectionName = UtilService.getScanner().next();
        add(Main.getFileName(), new Section(0,sectionName), chosenStore, Main.getStores() );
    }

    public static String readSection(Store store){
        String chosenSection = "";
        System.out.println("Please enter a section: ");
        for (Section loopSection : store.getSections()){
            System.out.println(loopSection.getName() + "\t");
        }
        chosenSection = UtilService.getScanner().next();
        return chosenSection;
    }

    public static void editSection(){
        String newData = "";
        String chosenStore = StoreService.readStore();
        Store store = StoreService.searchStore(chosenStore, Main.getStores());
        if (store == null) return;
        String chosenSection = readSection(store);
        System.out.println("Choose a new name:");
        newData =  UtilService.getScanner().next();
        update(Main.getFileName(), new Section(0,newData), chosenStore, chosenSection, Main.getStores());
    }

    public static void deleteSection(){
        String chosenSection = "";
        String chosenStore = StoreService.readStore();
        Store store = StoreService.searchStore(chosenStore, Main.getStores());
        if (store == null) return;
        chosenSection = readSection(store);
        delete(Main.getFileName(), chosenSection, chosenStore, Main.getStores());
    }

    public static void display(Store store){
        try {
            store.getSections().forEach(System.out::println);
        }catch (NullPointerException nullPointerException){
            System.out.println("There are no products for this section");
        }
    }

    public static void displaySections(){
        String chosenStore = StoreService.readStore();
        Store store = StoreService.searchStore(chosenStore, Main.getStores());
        display(store);
    }
}
