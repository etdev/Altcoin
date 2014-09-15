package com.ericturnerdev.Altcoin;

/**
 * Created by ericturner on 2014/09/15.
 */
public class BuySellItem {

    double price = 0.0;
    double quantity = 0.0;
    double total = 0.0;

    public BuySellItem(double _price, double _quantity, double _total){
        this.price = _price;
        this.quantity = _quantity;
        this.total = _total;
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

    public String toString(){
        return "{ price: " + this.price + ", quantity: " + this.quantity + ", total: " + this.total + " }";
    }
}
