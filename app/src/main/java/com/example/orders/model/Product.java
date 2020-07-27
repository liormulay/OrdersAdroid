package com.example.orders.model;

public class Product extends ProductBaseModel {


    private float price;

    /**
     * The quantity of the product in the stock
     */
    private int stockQuantity;


    public Product() {
    }

    public Product(String productName, float price, int stockQuantity, String imageUrl) {
        this(0, productName, imageUrl, price, stockQuantity);
    }

    public Product(int productId, String productName, String imageUrl, float price, int stockQuantity) {
        super(productId, productName, imageUrl);
        this.price = price;
        this.stockQuantity = stockQuantity;
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
}
