package com.example.orders.model;

import java.io.Serializable;

public abstract class ProductBaseModel implements Serializable {

    private static final long serialVersionUID = -4814365604368308880L;

    private int productId;

    private String productName;

    private String imageUrl;

    public ProductBaseModel() {
    }

    public ProductBaseModel(int productId, String productName, String imageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.imageUrl = imageUrl;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
