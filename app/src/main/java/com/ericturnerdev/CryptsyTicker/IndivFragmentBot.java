package com.ericturnerdev.CryptsyTicker;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Code for Bottom fragment of Individual Trade Pair activity
 */
public class IndivFragmentBot extends Fragment {

    private int marketId;
    private double volume;

    public IndivFragmentBot() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        marketId = IndivActivity.marketId;
        volume = IndivActivity.volume;

        View v = inflater.inflate(R.layout.fragment_indiv_bot, container, false);

        //Set price
        TextView priceTV = (TextView) v.findViewById(R.id.priceTV);
        priceTV.setText("Price: " + Pairs.getMarket(marketId).getLasttradeprice());

        //Set volume
        TextView volTV = (TextView) v.findViewById(R.id.volumeTV);
        volTV.setText("Volume: " + volume);

        //Write Table labels
        TextView label1A = (TextView) v.findViewById(R.id.btblr0c0);
        TextView label2A = (TextView) v.findViewById(R.id.btblr0c1);
        label1A.setText("Buy price:");
        label2A.setText("amount:");

        //Write Table labels
        TextView label1B = (TextView) v.findViewById(R.id.stblr0c0);
        TextView label2B = (TextView) v.findViewById(R.id.stblr0c1);
        label1B.setText("Sell price:");
        label2B.setText("amount:");

        DecimalFormat df = new DecimalFormat("#.00");

        //TextViews buy table
        TextView btblr1c0 = (TextView) v.findViewById(R.id.btblr1c0);
        btblr1c0.setText("" + Pairs.getMarket(marketId).getBuyorders().get(0).getPrice());
        TextView btblr2c0 = (TextView) v.findViewById(R.id.btblr2c0);
        btblr2c0.setText("" + Pairs.getMarket(marketId).getBuyorders().get(1).getPrice());
        TextView btblr3c0 = (TextView) v.findViewById(R.id.btblr3c0);
        btblr3c0.setText("" + Pairs.getMarket(marketId).getBuyorders().get(2).getPrice());
        TextView btblr4c0 = (TextView) v.findViewById(R.id.btblr4c0);
        btblr4c0.setText("" + Pairs.getMarket(marketId).getBuyorders().get(3).getPrice());
        TextView btblr5c0 = (TextView) v.findViewById(R.id.btblr5c0);
        btblr5c0.setText("" + Pairs.getMarket(marketId).getBuyorders().get(4).getPrice());
        TextView btblr6c0 = (TextView) v.findViewById(R.id.btblr6c0);
        btblr6c0.setText("" + Pairs.getMarket(marketId).getBuyorders().get(5).getPrice());
        TextView btblr7c0 = (TextView) v.findViewById(R.id.btblr7c0);
        btblr7c0.setText("" + Pairs.getMarket(marketId).getBuyorders().get(6).getPrice());
        TextView btblr8c0 = (TextView) v.findViewById(R.id.btblr8c0);
        btblr8c0.setText("" + Pairs.getMarket(marketId).getBuyorders().get(7).getPrice());
        TextView btblr9c0 = (TextView) v.findViewById(R.id.btblr9c0);
        btblr9c0.setText("" + Pairs.getMarket(marketId).getBuyorders().get(8).getPrice());
        TextView btblr10c0 = (TextView) v.findViewById(R.id.btblr10c0);
        btblr10c0.setText("" + Pairs.getMarket(marketId).getBuyorders().get(9).getPrice());

        TextView btblr1c1 = (TextView) v.findViewById(R.id.btblr1c1);
        btblr1c1.setText("" + (double) (Math.round((Pairs.getMarket(marketId).getBuyorders().get(0).getTotal() * 1000)) / 1000));
        TextView btblr2c1 = (TextView) v.findViewById(R.id.btblr2c1);
        btblr2c1.setText("" + (double) (Math.round(Pairs.getMarket(marketId).getBuyorders().get(1).getTotal() * 1000) / 1000));
        TextView btblr3c1 = (TextView) v.findViewById(R.id.btblr3c1);
        btblr3c1.setText("" + (double) (Math.round(Pairs.getMarket(marketId).getBuyorders().get(2).getTotal() * 1000) / 1000));
        TextView btblr4c1 = (TextView) v.findViewById(R.id.btblr4c1);
        btblr4c1.setText("" + (double) (Math.round(Pairs.getMarket(marketId).getBuyorders().get(3).getTotal() * 1000) / 1000));
        TextView btblr5c1 = (TextView) v.findViewById(R.id.btblr5c1);
        btblr5c1.setText("" + (double) (Math.round(Pairs.getMarket(marketId).getBuyorders().get(4).getTotal() * 1000) / 1000));
        TextView btblr6c1 = (TextView) v.findViewById(R.id.btblr6c1);
        btblr6c1.setText("" + (double) (Math.round(Pairs.getMarket(marketId).getBuyorders().get(5).getTotal() * 1000) / 1000));
        TextView btblr7c1 = (TextView) v.findViewById(R.id.btblr7c1);
        btblr7c1.setText("" + (double) (Math.round(Pairs.getMarket(marketId).getBuyorders().get(6).getTotal() * 1000) / 1000));
        TextView btblr8c1 = (TextView) v.findViewById(R.id.btblr8c1);
        btblr8c1.setText("" + (double) (Math.round(Pairs.getMarket(marketId).getBuyorders().get(7).getTotal() * 1000) / 1000));
        TextView btblr9c1 = (TextView) v.findViewById(R.id.btblr9c1);
        btblr9c1.setText("" + (double) (Math.round(Pairs.getMarket(marketId).getBuyorders().get(8).getTotal() * 1000) / 1000));
        TextView btblr10c1 = (TextView) v.findViewById(R.id.btblr10c1);
        btblr10c1.setText("" + (double) (Math.round(Pairs.getMarket(marketId).getBuyorders().get(9).getTotal() * 1000) / 1000));


        //TextViews sell table
        TextView stblr1c0 = (TextView) v.findViewById(R.id.stblr1c0);
        stblr1c0.setText("" + Pairs.getMarket(marketId).getSellorders().get(0).getPrice());
        TextView stblr2c0 = (TextView) v.findViewById(R.id.stblr2c0);
        stblr2c0.setText("" + Pairs.getMarket(marketId).getSellorders().get(1).getPrice());
        TextView stblr3c0 = (TextView) v.findViewById(R.id.stblr3c0);
        stblr3c0.setText("" + Pairs.getMarket(marketId).getSellorders().get(2).getPrice());
        TextView stblr4c0 = (TextView) v.findViewById(R.id.stblr4c0);
        stblr4c0.setText("" + Pairs.getMarket(marketId).getSellorders().get(3).getPrice());
        TextView stblr5c0 = (TextView) v.findViewById(R.id.stblr5c0);
        stblr5c0.setText("" + Pairs.getMarket(marketId).getSellorders().get(4).getPrice());
        TextView stblr6c0 = (TextView) v.findViewById(R.id.stblr6c0);
        stblr6c0.setText("" + Pairs.getMarket(marketId).getSellorders().get(5).getPrice());
        TextView stblr7c0 = (TextView) v.findViewById(R.id.stblr7c0);
        stblr7c0.setText("" + Pairs.getMarket(marketId).getSellorders().get(6).getPrice());
        TextView stblr8c0 = (TextView) v.findViewById(R.id.stblr8c0);
        stblr8c0.setText("" + Pairs.getMarket(marketId).getSellorders().get(7).getPrice());
        TextView stblr9c0 = (TextView) v.findViewById(R.id.stblr9c0);
        stblr9c0.setText("" + Pairs.getMarket(marketId).getSellorders().get(8).getPrice());
        TextView stblr10c0 = (TextView) v.findViewById(R.id.stblr10c0);
        stblr10c0.setText("" + Pairs.getMarket(marketId).getSellorders().get(9).getPrice());


        TextView stblr1c1 = (TextView) v.findViewById(R.id.stblr1c1);
        stblr1c1.setText("" + (double) (Math.round((Pairs.getMarket(marketId).getSellorders().get(0).getTotal() * 1000)) / 1000));
        TextView stblr2c1 = (TextView) v.findViewById(R.id.stblr2c1);
        stblr2c1.setText("" + (double) (Math.round(Pairs.getMarket(marketId).getSellorders().get(1).getTotal() * 1000) / 1000));
        TextView stblr3c1 = (TextView) v.findViewById(R.id.stblr3c1);
        stblr3c1.setText("" + (double) (Math.round(Pairs.getMarket(marketId).getSellorders().get(2).getTotal() * 1000) / 1000));
        TextView stblr4c1 = (TextView) v.findViewById(R.id.stblr4c1);
        stblr4c1.setText("" + (double) (Math.round(Pairs.getMarket(marketId).getSellorders().get(3).getTotal() * 1000) / 1000));
        TextView stblr5c1 = (TextView) v.findViewById(R.id.stblr5c1);
        stblr5c1.setText("" + (double) (Math.round(Pairs.getMarket(marketId).getSellorders().get(4).getTotal() * 1000) / 1000));
        TextView stblr6c1 = (TextView) v.findViewById(R.id.stblr6c1);
        stblr6c1.setText("" + (double) (Math.round(Pairs.getMarket(marketId).getSellorders().get(5).getTotal() * 1000) / 1000));
        TextView stblr7c1 = (TextView) v.findViewById(R.id.stblr7c1);
        stblr7c1.setText("" + (double) (Math.round(Pairs.getMarket(marketId).getSellorders().get(6).getTotal() * 1000) / 1000));
        TextView stblr8c1 = (TextView) v.findViewById(R.id.stblr8c1);
        stblr8c1.setText("" + (double) (Math.round(Pairs.getMarket(marketId).getSellorders().get(7).getTotal() * 1000) / 1000));
        TextView stblr9c1 = (TextView) v.findViewById(R.id.stblr9c1);
        stblr9c1.setText("" + (double) (Math.round(Pairs.getMarket(marketId).getSellorders().get(8).getTotal() * 1000) / 1000));
        TextView stblr10c1 = (TextView) v.findViewById(R.id.stblr10c1);
        stblr10c1.setText("" + (double) (Math.round(Pairs.getMarket(marketId).getSellorders().get(9).getTotal() * 1000) / 1000));


        //Set order data
        for (int i = 0; i < 10; i++) {
            //Get the buy order and sell data, and put it in the corresponding tables
        }

        return v;

    }

}
