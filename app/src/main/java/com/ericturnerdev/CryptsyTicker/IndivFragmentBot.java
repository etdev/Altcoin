package com.ericturnerdev.CryptsyTicker;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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


        //Set order data
        for (int i = 0; i < 10; i++) {


        }

        return v;

    }

}
