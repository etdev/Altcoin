package com.ericturnerdev.CryptsyTicker;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Fragment for top part of Individual trade pair activity (price chart)
 */

public class IndivFragmentChart extends Fragment {

    public final String JSON_API = "http://ericturnerdev.com/getJson.php";
    private GraphicalView mChart;
    private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
    private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
    private XYSeries mCurrentSeries;
    private XYSeriesRenderer mCurrentRenderer;
    public int marketId;
    public final String TAG = "IndivFragChart";
    public ArrayList<IndivTradeItem> resultAL;

    public IndivFragmentChart() {


    }

    private void initChart() {

        mCurrentSeries = new XYSeries("Sample Data");
        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setBackgroundColor(Color.parseColor("#666666"));
        mDataset.addSeries(mCurrentSeries);
        mRenderer.setShowAxes(false);
        mRenderer.clearXTextLabels();
        mRenderer.clearYTextLabels();
        mRenderer.setShowAxes(false);
        mRenderer.setXLabels(0);
        mCurrentRenderer = new XYSeriesRenderer();
        mCurrentRenderer.setLineWidth(8);
        mCurrentRenderer.setShowLegendItem(false);
        mCurrentRenderer.setFillPoints(true);
        mCurrentRenderer.setColor(Color.parseColor("#EEEEEE"));
        mCurrentSeries.setTitle("");
        mRenderer.addSeriesRenderer(mCurrentRenderer);

    }

    private void addData() {

        int i;
        Collections.sort(resultAL);
        for (i = 0; i < resultAL.size(); i++) {

            if (i == 0) {
                mCurrentSeries.add((double) i, getPoint(resultAL.get(i).getPrice(), resultAL.get(i + 1).getPrice(), resultAL.get(i + 2).getPrice()));
            } else if (i == resultAL.size() - 1) {
                mCurrentSeries.add((double) i, getPoint(resultAL.get(i).getPrice(), resultAL.get(i - 1).getPrice(), resultAL.get(i - 2).getPrice()));
            } else {
                mCurrentSeries.add((double) i, getPoint(resultAL.get(i).getPrice(), resultAL.get(i - 1).getPrice(), resultAL.get(i + 1).getPrice()));
            }

        }

        Log.i(TAG, "initChart run");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_indiv_chart, container, false);

        LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.fragment_indiv_chart, container, false);

        marketId = IndivActivity.marketId;
        // marketId = getArguments().getInt("marketId");
        Log.i(TAG, "marketId is: " + marketId);

        if (mChart == null) {

            initChart();
            //ddSampleData();
            mChart = ChartFactory.getCubeLineChartView(getActivity(), mDataset, mRenderer, 0.6f);

            ll.addView(mChart);

        } else {
            mChart.repaint();
        }


        //return ll;
        new JSONGet(getActivity(), marketId).execute(JSON_API);

        return v;


    }


    /*JSONGet Class*/
    public class JSONGet extends AsyncTask<String, Void, Double> {

        Context mContext;
        String TAG = "JSONGet";
        public int marketId;
        JSONArray resultArray;

        public JSONGet(Context context, int marketId) {

            mContext = context;
            this.marketId = marketId;

        }

        protected ArrayList<IndivTradeItem> getData() {

            String rawData = ""; //String representation of raw JSON Data

            Log.i(TAG, "marketId is: " + marketId);

            resultAL = new ArrayList<IndivTradeItem>();

            int i = 0;
            boolean apiSuccess = false;

            //Get the data from Cryptsy API
            while (!apiSuccess && i < 20) {
                apiSuccess = true;
                try {

                    Log.i(TAG, "marketId: " + marketId);

                    //rawData = string response from the API
                    Log.i(TAG, "JSONGet URL: " + JSON_API + "?" + "marketid=" + marketId + "&primary=" + Pairs.getMarket(marketId).getSecondarycode() + "&secondary=" + Pairs.getMarket(marketId).getPrimarycode());

                    rawData = new URLFetch().getURL(JSON_API + "?" + "marketid=" + marketId + "&primary=" + Pairs.getMarket(marketId).getSecondarycode() + "&secondary=" + Pairs.getMarket(marketId).getPrimarycode());
                    Log.i(TAG, "JSONGet rawData: " + rawData);

                    try {

                        resultArray = new JSONArray(rawData);


                        for (i = 0; i < resultArray.length(); i++) {

                            JSONObject temp = resultArray.getJSONObject(i);
                            String tempTime = temp.getString("time");
                            double tempPrice = temp.getDouble("price");
                            Log.i(TAG, "JSON Object: " + tempTime + ", " + tempPrice);
                            resultAL.add(new IndivTradeItem(tempPrice, tempTime));

                        }

                    } catch (JSONException e) {
                        Log.e(TAG, "aaa JSON Exception");
                    }

                } catch (IOException e) {
                    Log.e(TAG, "aaa Couldn't load data from api.  i is: " + i);
                    i++;
                    apiSuccess = false;
                }

            }

            return resultAL;


        }


        @Override
        protected Double doInBackground(String... params) {


            getData();

            return null;
        }

        protected void onPostExecute(Double d) {

            //CONTINUE WORKING HERE!

            Log.i(TAG, "From onPostExecute, here is resultAL: " + resultAL);

            //populateListView();
            addData();
            mChart.repaint();

        }

    }

    public class IndivTradeItem implements Comparable<IndivTradeItem> {

        double price;
        String date;

        public IndivTradeItem(double price, String date) {
            this.price = price;
            this.date = date;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        @Override
        public String toString() {

            return "(" + this.price + ", " + this.date + ")";

        }

        public int compareTo(IndivTradeItem t) {

            return (this.getDate().compareTo(t.getDate()));

        }
    }

    public double getPoint(double a, double b, double c) {

        double avg = (b + c) / 2;
        if (((avg / a) > (1.5 * avg)) || ((avg / a) < (0.75 * avg))) {
            return avg;
        } else return a;

    }

}
