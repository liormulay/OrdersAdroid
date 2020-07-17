package com.example.orders.model;

import java.io.Serializable;
import java.util.List;

public class ItemsRequestModel implements Serializable {

    private static final long serialVersionUID = -7817376925341892843L;

    /**
     * The items that user wants
     */
    private List<ItemRequest> itemsRequest;

    public ItemsRequestModel() {
    }

    public ItemsRequestModel(List<ItemRequest> itemsRequest) {
        this.itemsRequest = itemsRequest;
    }

    public void setItemsRequest(List<ItemRequest> itemsRequest) {
        this.itemsRequest = itemsRequest;
    }

    public List<ItemRequest> getItemsRequest() {
        return itemsRequest;
    }
}
