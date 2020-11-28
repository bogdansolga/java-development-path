package com.java.training.stores.project.services;

import com.java.training.stores.project.models.Product;
import com.java.training.stores.project.models.Section;
import com.java.training.stores.project.models.Store;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ProductService {

    private final StoreService storeService;

    private final SectionService sectionService;

    public ProductService(StoreService storeService, SectionService sectionService) {
        this.storeService = storeService;
        this.sectionService = sectionService;
    }

    public void add(String storeName, String sectionName, Product product) {
        Optional<Store> store = storeService.searchStore(storeName);

        Optional<Section> section = sectionService.searchSection(sectionName, store.get().getSections());

        if (searchProduct(product.getName(), section.get().getProducts()).isPresent()) {
            System.out.println("The product already exist!");
            return;
        }

        section.get().getProducts().add(product);

        storeService.saveStores();

        System.out.println("[Product] " + product.getName() + " was added successfully");
    }

    public void update(String storeName, String sectionName, String productName,
                              Product product) {
        Optional<Store> searchedStore = storeService.searchStore(storeName);

        Optional<Section> searchedSection = sectionService.searchSection(sectionName, searchedStore.get().getSections());

        Optional<Product> searchedProduct = searchProduct(productName, searchedSection.get().getProducts());

        searchedProduct.get().setId(product.getId());
        searchedProduct.get().setDescription(product.getDescription());
        searchedProduct.get().setName(product.getName());
        searchedProduct.get().setPrice(product.getPrice());

        storeService.saveStores();

        System.out.println("[Product] " + productName + " is now called " + product.getName());
    }

    public void delete(String storeName, String sectionName, String productName) {
        Optional<Store> searchedStore = storeService.searchStore(storeName);
        if (!searchedStore.isPresent()) {
            System.out.println("Store is not existent!");
            return;
        }

        Optional<Section> searchedSection = sectionService.searchSection(sectionName, searchedStore.get().getSections());
        if (!searchedSection.isPresent()) {
            System.out.println("Section is not existent!");
            return;
        }

        Optional<Product> searchedProduct = searchProduct(productName, searchedSection.get().getProducts());
        if (!searchedProduct.isPresent()) {
            System.out.println("Product is not existent!");
            return;
        }

        searchedSection.get().getProducts().remove(searchedProduct.get());

        storeService.saveStores();

        System.out.println("[Product] " + productName + " was deleted");
    }

    public Optional<Product> searchProduct(String productName, Set<Product> productSet) {
        return productSet.stream().filter(item -> item.getName().compareTo(productName) == 0).findFirst();
    }

    public void createProduct() {
        Optional<Store> store;
        Optional<Section> section;

        String chosenStore = storeService.readStore();

        if (chosenStore.equals("")) return;

        store = storeService.searchStore(chosenStore, StoreService.getStores());
        if (!store.isPresent()) {
            System.out.println("Store not existent");
            return;
        }

        String chosenSection = sectionService.readSection(store.get());

        if (chosenSection.equals("")) return;

        section = SectionService.searchSection(chosenSection, store.get().getSections());
        if (!section.isPresent()) {
            System.out.println("Section not existent");
            return;
        }

        if (section.get().getProducts() == null) section.get().setProducts(new HashSet<>());

        System.out.println("Choose an unique product name for this store and section: ");
        String productName = UtilService.getScanner().next();

        if (searchProduct(productName, section.get().getProducts()).isPresent()) {
            System.out.println("Product is already existent");
            return;
        }

        add(chosenStore, chosenSection, new Product(0, productName), StoreService.getStores());
    }

    public String readProduct(Section section) {
        if (section.getProducts() == null || section.getProducts().isEmpty()) {
            System.out.println("No products");
            section.setProducts(new HashSet<>());
            return "";
        }

        System.out.println("Type product name: ");
        for (Product product : section.getProducts()) {
            System.out.println(product.getName() + "\t");
        }

        return UtilService.getScanner().next();
    }


    public void editProduct() {
        String chosenStore = StoreService.readStore();

        if (chosenStore.equals("")) return;

        Optional<Store> store = StoreService.searchStore(chosenStore, StoreService.getStores());
        if (!store.isPresent()) {
            System.out.println("Store not existent!");
            return;
        }

        String chosenSection = SectionService.readSection(store.get());

        if (chosenSection.equals("")) return;

        Optional<Section> section = SectionService.searchSection(chosenSection, store.get().getSections());
        if (!section.isPresent()) {
            System.out.println("Section not existent!");
            return;
        }

        String chosenProduct = readProduct(section.get());

        if (chosenProduct.equals("")) return;

        if (!searchProduct(chosenProduct, section.get().getProducts()).isPresent()) {
            System.out.println("Product not existent!");
        }

        System.out.println("Choose a new name:");
        String newData = UtilService.getScanner().next();

        update(chosenStore, chosenSection, chosenProduct, new Product(0, newData), StoreService.getStores());
    }

    public void deleteProduct() {
        String chosenStore = StoreService.readStore();

        if (chosenStore.equals("")) return;

        Optional<Store> store = StoreService.searchStore(chosenStore, StoreService.getStores());
        if (!store.isPresent()) {
            System.out.println("Store not existent!");
            return;
        }

        String chosenSection = SectionService.readSection(store.get());

        if (chosenSection.equals("")) return;

        Optional<Section> section = SectionService.searchSection(chosenSection, store.get().getSections());
        if (!section.isPresent()) {
            System.out.println("Section not existent!");
            return;
        }

        String chosenProduct = readProduct(section.get());

        if (chosenProduct.equals("")) return;

        if (!searchProduct(chosenProduct, section.get().getProducts()).isPresent()) {
            System.out.println("Product not existent!");
        }

        delete(chosenStore, chosenSection, chosenProduct, StoreService.getStores());
    }

    public void display(Section section) {
        if (section.getProducts() == null) {
            System.out.println("Couldn't find any products of this section");
            return;
        }
        section.getProducts().forEach(product -> System.out.println(product.getName()));
    }

    public void displayProducts() {
        String chosenStore = StoreService.readStore();

        if (chosenStore.equals("")) return;

        Optional<Store> store = StoreService.searchStore(chosenStore, StoreService.getStores());
        if (!store.isPresent()) {
            System.out.println("Store not existent!");
            return;
        }

        String chosenSection = SectionService.readSection(store.get());

        if (chosenSection.equals("")) return;

        Optional<Section> section = SectionService.searchSection(chosenSection, store.get().getSections());
        if (!section.isPresent()) {
            System.out.println("Section not existent");
            return;
        }

        display(section.get());
    }
}
