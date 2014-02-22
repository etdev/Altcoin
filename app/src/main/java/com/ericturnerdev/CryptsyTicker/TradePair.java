package com.ericturnerdev.CryptsyTicker;

import java.util.ArrayList;

/**
 *  TradePair datatype
 */
public class TradePair {

    int marketId;
    boolean visible;
    String mainCoin;
    String baseCoin;
    double lastTradePrice;
    double currentBuyPrice;
    double currentSellPrice;
    double volume;
    double high;
    double low;
    ArrayList<OrderItem> buyOrders;
    ArrayList<OrderItem> sellOrders;
    ArrayList<OrderItem> recentTrades;

    public TradePair(boolean visible, String mainCoin, String baseCoin, double lastTradePrice, double currentBuyPrice, double currentSellPrice, double volume, double high, double low, ArrayList<OrderItem> buyOrders, ArrayList<OrderItem> sellOrders, ArrayList<OrderItem> recentTrades) {
        this.visible = visible;
        this.mainCoin = mainCoin;
        this.baseCoin = baseCoin;
        this.lastTradePrice = lastTradePrice;
        this.currentBuyPrice = currentBuyPrice;
        this.currentSellPrice = currentSellPrice;
        this.volume = volume;
        this.high = high;
        this.low = low;
        this.buyOrders = buyOrders;
        this.sellOrders = sellOrders;
        this.recentTrades = recentTrades;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getMainCoin() {
        return mainCoin;
    }

    public int getMarketId(){

        return marketId;
    }

    public void setMainCoin(String mainCoin) {
        this.mainCoin = mainCoin;
    }

    public String getBaseCoin() {
        return baseCoin;
    }

    public void setBaseCoin(String baseCoin) {
        this.baseCoin = baseCoin;
    }

    public double getLastTradePrice() {
        return lastTradePrice;
    }

    public void setLastTradePrice(double lastTradePrice) {
        this.lastTradePrice = lastTradePrice;
    }

    public double getCurrentBuyPrice() {
        return currentBuyPrice;
    }

    public void setCurrentBuyPrice(double currentBuyPrice) {
        this.currentBuyPrice = currentBuyPrice;
    }

    public double getCurrentSellPrice() {
        return currentSellPrice;
    }

    public void setCurrentSellPrice(double currentSellPrice) {
        this.currentSellPrice = currentSellPrice;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public ArrayList<OrderItem> getBuyOrders() {
        return buyOrders;
    }

    public void setBuyOrders(ArrayList<OrderItem> buyOrders) {
        this.buyOrders = buyOrders;
    }

    public ArrayList<OrderItem> getSellOrders() {
        return sellOrders;
    }

    public void setSellOrders(ArrayList<OrderItem> sellOrders) {
        this.sellOrders = sellOrders;
    }

    public ArrayList<OrderItem> getRecentTrades() {
        return recentTrades;
    }

    public void setRecentTrades(ArrayList<OrderItem> recentTrades) {
        this.recentTrades = recentTrades;
    }
}
