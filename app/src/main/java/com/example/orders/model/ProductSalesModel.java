package com.example.orders.model;

public class ProductSalesModel extends Product {

    private static final long serialVersionUID = -5235656766585797092L;

    private int salesQuantity;

    public ProductSalesModel(int productId, String productName, String imageUrl, float price, int stockQuantity, int salesQuantity) {
        super(productId, productName, imageUrl, price, stockQuantity);
        this.salesQuantity = salesQuantity;
    }

    public int getSalesQuantity() {
        return salesQuantity;
    }

    public void setSalesQuantity(int salesQuantity) {
        this.salesQuantity = salesQuantity;
    }
}
