package com.ericturnerdev.CryptsyTicker;

/**
 * Datatype used by Cryptsy's sellorders, buyorders arrays
 */
public class OrderItem {

    double price;
    double quantity;
    double total;


    //Constructor
    public OrderItem(double price, double quantity, double total) {
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}




