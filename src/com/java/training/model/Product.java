package com.java.training.model;

import java.io.Serializable;

public class Product implements Comparable<Product>, Serializable {

    private int id;
    private String name;

    public Product() {
    }

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        /*
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
        */

        return id + ", " + name;
    }

    @Override
    public int compareTo(Product o) {
        //return name.compareTo(o.getName());
        return Integer.compare(id, o.getId());
    }
}
