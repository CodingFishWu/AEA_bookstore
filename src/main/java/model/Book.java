package model;

import java.io.Serializable;

/**
 * Created by fish on 3/25/16.
 */
public class Book implements Serializable{
    private String id;
    private String name;
    private String author;
    private double price;
    private int stock;
    private String category;

    public Book(String id, String name, String author, double price, int stock, String category) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.category = category;
    }
    public Book() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {

        return name;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
