package com.example.orders.model;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {

    private Date orderDate;

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
