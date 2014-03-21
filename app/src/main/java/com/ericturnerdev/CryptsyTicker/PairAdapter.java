package com.ericturnerdev.CryptsyTicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * ArrayAdapter for displaying the info for trade pairs in main activity
 */
public class PairAdapter extends ArrayAdapter<Market> {

    String TAG = "PairAdapter";

    public Context context;
    public int layoutResourceId;
    public ArrayList<Market> pairs = new ArrayList<Market>();

    public PairAdapter(Context context, int layoutResourceId, ArrayList<Market> pairs) {

        super(context, layoutResourceId, pairs);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.pairs = pairs;
        //Log.i(TAG, "Pairs: " + pairs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //convertView is the view that will be recycled to create the new View
        //parent is the ListView holding the rows (I THINK)
        View v = convertView; //The current view
        TextView topTV1, topTV2, topTV3, botTV1, botTV2, moreTopTV, moreTopTV2;
        final int currentPosition = position;
        //If there's no view to recycle
        if (convertView == null) {

            //Make a new view with an inflater
            LayoutInflater inflater = ((Activity) context).getLayoutInflater(); //Note ((Activity)context)
            //Inflate the row item
            v = inflater.inflate(R.layout.pair_item_view, parent, false); //using pair_item_view.xml

        }

        moreTopTV = (TextView) v.findViewById(R.id.moretop_tv1);
        moreTopTV2 = (TextView) v.findViewById(R.id.moretop_tv2);
        topTV1 = (TextView) v.findViewById(R.id.top_tv1);
        topTV2 = (TextView) v.findViewById(R.id.top_tv2);
        //topTV3 = (TextView) v.findViewById(R.id.top_tv3);
        botTV1 = (TextView) v.findViewById(R.id.bot_tv1);
        botTV2 = (TextView) v.findViewById(R.id.bot_tv2);

        double absoluteChange;
        double percentChange;
        double averagePrice = 0.0;

        //TEMPORARY.  IF POSSIBLE I'D RATHER DO THIS WITH SQLite etc.
        //Get average of last 100 trades
        for (int i = 0; i < pairs.get(position).getRecenttrades().size(); i++) {

            averagePrice += pairs.get(position).getRecenttrades().get(i).getPrice();

        }

        averagePrice = averagePrice / pairs.get(position).getRecenttrades().size();
        //Log.i(TAG, "averagePrice: " + averagePrice);

        absoluteChange = pairs.get(position).getLasttradeprice() - averagePrice;
        //Log.i(TAG, "absoluteChange " + absoluteChange);

        percentChange = (absoluteChange / (pairs.get(position).getLasttradeprice())) * 100;
        //Log.i(TAG, "percentChange: " + percentChange);

        percentChange = (double) Math.round((percentChange * 100)) / 100;

        //topTV3.setText("" + Format.checkFormat(pairs.get(position).getLasttradeprice(), pairs.get(position).getPrimarycode()));


        moreTopTV.setText("/" + pairs.get(position).getPrimarycode());
        moreTopTV.setTextColor(Color.parseColor("#333333"));
        moreTopTV.setTextSize(26);
        moreTopTV2.setText(pairs.get(position).getPrimaryname());
        moreTopTV2.setTextColor(Color.parseColor("#000000"));
        moreTopTV2.setTextSize(26);
        if (pairs.get(position).getLasttradeprice() == 0.0){ topTV1.setText("fetching data..."); topTV2.setTextColor(Color.BLACK);}
        else { topTV1.setText(Html.fromHtml("<b>" + Format.formatNum(pairs.get(position).getLasttradeprice(), pairs.get(position).getPrimarycode()) + "</b>" + " (" + Format.checkFormat(pairs.get(position).getLasttradeprice(), pairs.get(position).getPrimarycode()) + ")")); }
        if (absoluteChange > 0) {
            topTV2.setTextColor(Color.parseColor("#33CC33"));
            topTV2.setText("+" + percentChange + "%");
        } else if (absoluteChange < 0) {
            topTV2.setTextColor(Color.parseColor("red"));
            topTV2.setText("" + percentChange + "%");
        } else {
            topTV2.setText("" + percentChange + "%");
        }

        if (pairs.get(position).getBuyorders().size() > 0) {
            botTV1.setText("Buy: " + Format.formatNum(pairs.get(position).getBuyorders().get(0).getPrice(), pairs.get(position).getPrimarycode()));
        } else {
            botTV1.setText("Buy: no data");
        }
        if (pairs.get(position).getSellorders().size() > 0) {
            botTV2.setText("Sell: " + Format.formatNum(pairs.get(position).getSellorders().get(0).getPrice(), pairs.get(position).getPrimarycode()));
        } else {
            botTV2.setText("Sell: no data");
        }

        v.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Log.i(TAG, "CLICKED: " + pairs.get(currentPosition).getPrimarycode() + "/" + pairs.get(currentPosition).getSecondarycode());
                Intent intent = new Intent(getContext(), IndivActivity.class);
                intent.putExtra("marketId", pairs.get(currentPosition).getMarketid());
                intent.putExtra("volume", pairs.get(currentPosition).getVolume());
                getContext().startActivity(intent);

            }

        });
        return v;

    }

}
