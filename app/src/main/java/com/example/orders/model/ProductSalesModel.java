package com.example.orders.model;

public class ProductSalesModel extends ProductBaseModel {

    private static final long serialVersionUID = -5235656766585797092L;

    private int quantity;


    public ProductSalesModel() {
    }

    public ProductSalesModel(int productId, String productName, String imageUrl, int quantity) {
        super(productId, productName, imageUrl);
        this.quantity = quantity;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
