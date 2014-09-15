package com.ericturnerdev.Altcoin;

import java.util.ArrayList;
import java.util.List;

/**
 * Market data type to match Cryptsy JSON Return
 */

public class Market implements Comparable<Market> {

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
    private List<BuySellItem> sellorders = new ArrayList<BuySellItem>();
    private List<BuySellItem> buyorders = new ArrayList<BuySellItem>();
    private boolean visible = false;

    //For cryptocoincharts API
    private double price_before_24h = 0.0;
    private double volume_btc = 0.0;
    private double price = 0.0;
    private String id = "";

    public Market() {

    }

    //Constructor for Pairs class (only data needed to show on Settings page)
    public Market(int marketid, String secondarycode, String primarycode, String primaryname) {

        this.marketid = marketid;
        this.primaryname = primaryname;
        this.primarycode = primarycode;
        this.secondarycode = secondarycode;
        this.visible = false;
        this.label = secondarycode + "/" + primarycode;
        this.id = this.label;
        //Log.i("fromMarket", "Label: " + this.label);

    }

    public Market(int marketid, String label, double lasttradeprice, double volume, String lasttradetime, String primaryname, String primarycode, String secondaryname, String secondarycode, List<TradeItem> recenttrades, List<BuySellItem> sellorders, List<BuySellItem> buyorders, boolean visible) {

        this.marketid = marketid;
        this.label = label;
        this.lasttradeprice = lasttradeprice;
        this.volume = volume;
        this.lasttradetime = lasttradetime;
        this.primaryname = primaryname;
        this.primarycode = primarycode;
        this.secondaryname = secondaryname;
        this.secondarycode = secondarycode;
        this.recenttrades = recenttrades;
        this.sellorders = sellorders;
        this.buyorders = buyorders;
        this.visible = visible;
    }

    public String getId() {
        return id;
    }

    public void setPrice_before_24h(double price_before_24h) {
        this.price_before_24h = price_before_24h;
    }

    public void setVolume_btc(double volume_btc) {
        this.volume_btc = volume_btc;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice_before_24h() {
        return price_before_24h;
    }

    public double getVolume_btc() {
        return volume_btc;
    }

    public double getPrice() {
        return price;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

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

    public List<BuySellItem> getSellorders() {
        return sellorders;
    }

    public void setSellorders(List<BuySellItem> sellorders) {
        this.sellorders = sellorders;
    }

    public List<BuySellItem> getBuyorders() {
        return buyorders;
    }

    public void setBuyorders(List<BuySellItem> buyorders) {
        this.buyorders = buyorders;
    }

    @Override
    public String toString() {

        String val = "";
        try {
            val = " marketid: " + getMarketid() + " label: " + getLabel() + " volume: " + getVolume() + " time: " + getLasttradetime() + " primaryname: " + getPrimaryname() + " primarycode: " + getPrimarycode() + " secondaryname: " + getSecondaryname() + " secondarycode: " + getSecondarycode() +
                    " lasttradeprice: " + getLasttradeprice() + " visible: " + visible + "recenttrades:\n" + getRecenttrades().toString() + "\nsellorders:" + getSellorders().toString() + "\nbuyorders:" + getBuyorders().toString();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return val;
    }

    //Used for recenttrades only
    public class TradeItem {

        public TradeItem(double price, double quantity, double total){

            this.price = price;
            this.quantity = quantity;
            this.total = total;
        }

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

        public String toString() {

            return "    id:" + getId() + " time: " + getTime() + " price:" + getPrice() + " quantity: " + getQuantity() + " total; " + getTotal() + "\n";

        }
    }

    /*
    //Used for sellorders and buyorders only
    public class OrderItem {

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

        public String toString() {

            return "      price:" + getPrice() + " quantity: " + getQuantity() + " total; " + getTotal() + "\n";

        }

    }
    */

    public int compareTo(Market m) {

        if (this.getSecondarycode().compareTo(m.getSecondarycode()) > 0) {
            return 1;
        } else if (this.getSecondarycode().compareTo(m.getSecondarycode()) < 0) {
            return -1;
        } else {
            return 0;
        }

    }
}


