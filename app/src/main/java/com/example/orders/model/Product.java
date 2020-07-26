package com.example.orders.model;

import java.io.Serializable;

public class Product implements Serializable {

    private static final long serialVersionUID = -4814365604368308880L;

    private String productName;

    private float price;

    /**
     * The quantity of the product in the stock
     */
    private int stockQuantity;

    private String imageUrl;

    public Product() {
    }

    public Product(String productName, float price, int stockQuantity, String imageUrl) {
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
