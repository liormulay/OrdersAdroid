package com.example.orders.model;

public class ItemResponse extends ProductBaseModel {

    private static final long serialVersionUID = 5741038666778991543L;

    private int quantity;

    private float price;

    public ItemResponse(int productId, int quantity, String productName, float price, String imageUrl) {
        super(productId, productName, imageUrl);
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

