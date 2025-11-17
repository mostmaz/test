package com.example.mobilecaseshop.models;

public class Product {
    private String id;
    private String name;
    private String description;
    private double price;
    private String imageUrl; // URL or local resource name for the image
    private boolean isTopSold;
    private boolean isLatest;

    public Product(String id, String name, String description, double price, String imageUrl, boolean isTopSold, boolean isLatest) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.isTopSold = isTopSold;
        this.isLatest = isLatest;
    }

    // --- Getters for all fields ---

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isTopSold() {
        return isTopSold;
    }

    public boolean isLatest() {
        return isLatest;
    }
}
