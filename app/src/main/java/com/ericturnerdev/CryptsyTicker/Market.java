package com.ericturnerdev.CryptsyTicker;

import java.util.ArrayList;
import java.util.List;

/**
 * Market datatype to match Cryptsy JSON Return
 */

    public class Market{

        private int marketid = 0;
        private String label = "";
        private double lasttradeprice = 0.00;
        private double volume = 0.00;
        private String lasttradetime = "";
        private String primaryname = "";
        private String primarycode = "";
        private String secondaryname = "";
        private String secondarycode = "";
        private List<TradeItem> recenttrades = new ArrayList<TradeItem>();
        private List<OrderItem> sellorders = new ArrayList<OrderItem>();
        private List<OrderItem> buyorders = new ArrayList<OrderItem>();

        public int getMarketid() {
            return marketid;
        }

        public void setMarketid(int marketid) {
            this.marketid = marketid;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public double getLasttradeprice() {
            return lasttradeprice;
        }

        public void setLasttradeprice(double lasttradeprice) {
            this.lasttradeprice = lasttradeprice;
        }

        public double getVolume() {
            return volume;
        }

        public void setVolume(double volume) {
            this.volume = volume;
        }

        public String getLasttradetime() {
            return lasttradetime;
        }

        public void setLasttradetime(String lasttradetime) {
            this.lasttradetime = lasttradetime;
        }

        public String getPrimaryname() {
            return primaryname;
        }

        public void setPrimaryname(String primaryname) {
            this.primaryname = primaryname;
        }

        public String getPrimarycode() {
            return primarycode;
        }

        public void setPrimarycode(String primarycode) {
            this.primarycode = primarycode;
        }

        public String getSecondaryname() {
            return secondaryname;
        }

        public void setSecondaryname(String secondaryname) {
            this.secondaryname = secondaryname;
        }

        public String getSecondarycode() {
            return secondarycode;
        }

        public void setSecondarycode(String secondarycode) {
            this.secondarycode = secondarycode;
        }

        public List<TradeItem> getRecenttrades() {
            return recenttrades;
        }

        public void setRecenttrades(List<TradeItem> recenttrades) {
            this.recenttrades = recenttrades;
        }

        public List<OrderItem> getSellorders() {
            return sellorders;
        }

        public void setSellorders(List<OrderItem> sellorders) {
            this.sellorders = sellorders;
        }

        public List<OrderItem> getBuyorders() {
            return buyorders;
        }

        public void setBuyorders(List<OrderItem> buyorders) {
            this.buyorders = buyorders;
        }

        @Override
        public String toString(){

            String val = "";
            try{
            val =  " marketid: " + getMarketid() + " label: " + getLabel() + " volume: " + getVolume() + " time: " + getLasttradetime() + " primaryname: " + getPrimaryname() + " primarycode: " + getPrimarycode() + " secondaryname: " + getSecondaryname() + " secondarycode: " + getSecondarycode()
                    + "recenttrades:\n" + getRecenttrades().toString() + "\nsellorders:" + getSellorders().toString() + "\nbuyorders:" + getBuyorders().toString();
            } catch(RuntimeException e) {e.printStackTrace(); }

            return val;
        }

    //Used for recenttrades only
    public class TradeItem{

        private int id = 0;
        private String time = "";
        private double price = 0.0;
        private double quantity = 0.0;
        private double total = 0.0;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
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

            return "    id:" + getId() + " time: " + getTime() + " price:" + getPrice() + " quantity: " + getQuantity() + " total; " + getTotal() + "\n";

        }
    }

    //Used for sellorders and buyorders only
    public class OrderItem{

        private double price = 0.0;
        private double quantity = 0.0;
        private double total = 0.0;

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

            return "      price:" + getPrice() + " quantity: " + getQuantity() + " total; " + getTotal() + "\n";

        }
    }
}


