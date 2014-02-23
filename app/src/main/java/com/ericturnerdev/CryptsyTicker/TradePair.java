package com.ericturnerdev.CryptsyTicker;

/**
 * TradePair Data Type
 */

public class TradePair {

    private int marketId;
    private double lastTradePrice;
    private double currentBuy;
    private double currentSell;
    private double volume;
    private String baseCoin;
    private String mainCoin;
    private int visible;
    private double absoluteChange;

    public double getAbsoluteChange() {
        return absoluteChange;
    }

    public void setAbsoluteChange(double absoluteChange) {
        this.absoluteChange = absoluteChange;
    }

    public double getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(double percentChange) {
        this.percentChange = percentChange;
    }

    private double percentChange;

    public TradePair(int marketId, double lastTradePrice, double currentBuy, double currentSell, double volume, String baseCoin, String mainCoin, int visible) {
        this.marketId = marketId;
        this.lastTradePrice = lastTradePrice;
        this.currentBuy = currentBuy;
        this.currentSell = currentSell;
        this.volume = volume;
        this.baseCoin = baseCoin;
        this.mainCoin = mainCoin;
        this.visible = visible;
    }

    public TradePair(int marketId, double lastTradePrice, double currentBuy, double currentSell, double volume, String baseCoin, String mainCoin, int visible, double percentChange, double absoluteChange) {
        this.marketId = marketId;
        this.lastTradePrice = lastTradePrice;
        this.currentBuy = currentBuy;
        this.currentSell = currentSell;
        this.volume = volume;
        this.baseCoin = baseCoin;
        this.mainCoin = mainCoin;
        this.visible = visible;
        this.percentChange = percentChange;
        this.absoluteChange = absoluteChange;
    }

    public TradePair() {
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public double getLastTradePrice() {
        return lastTradePrice;
    }

    public void setLastTradePrice(double lastTradePrice) {
        this.lastTradePrice = lastTradePrice;
    }

    public double getCurrentBuy() {
        return currentBuy;
    }

    public void setCurrentBuy(double currentBuy) {
        this.currentBuy = currentBuy;
    }

    public double getCurrentSell() {
        return currentSell;
    }

    public void setCurrentSell(double currentSell) {
        this.currentSell = currentSell;
    }

    public String getBaseCoin() {
        return baseCoin;
    }

    public void setBaseCoin(String baseCoin) {
        this.baseCoin = baseCoin;
    }

    public String getMainCoin() {
        return mainCoin;
    }

    public void setMainCoin(String mainCoin) {
        this.mainCoin = mainCoin;
    }

    public String toString(){

        return "" + this.getBaseCoin() + "_" + this.getMainCoin() + ": " + this.getLastTradePrice() + " ," + this.getVolume() + " ," + this.getVisible();

    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }
}
