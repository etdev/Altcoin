package com.ericturnerdev.Altcoin;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Eric Turner (ericturnerdev) on 2/21/14.  Code for the settings page where users can filter their coins.
 */
public class SettingsFragment extends Fragment {

    GridView mGridView;
    TextView mTextView;
    String TAG = "SettingsFragment";
    ArrayList<Market> markets = Pairs.getAllMarkets();

    //Code for checkmarks and whatnot here
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        mGridView = (GridView) v.findViewById(R.id.settingsGrid);
        mTextView = (TextView) v.findViewById(R.id.settingsHeading);

        mGridView.setAdapter(new SettingsGridAdapter());

        //Log.i(TAG, "Dogecoin price: " + Pairs.getMarket(132).getPrice());

        return v;
    }

    static class ViewHolder {
        TextView tv;
        TextView tv2;
        TextView tv3;
        CheckBox ch;

    }

    public class SettingsGridAdapter extends BaseAdapter {


        public View getView(int position, View convertView, ViewGroup parent) {

            View v;
            //Log.i(TAG, "Populating Settings Grid.  pairs: " + Pairs.getAllMarkets());

            if (convertView == null) {

                v = LayoutInflater.from(getActivity()).inflate(R.layout.settings_item, null);

            } else {
                v = convertView;
            }

            ViewHolder h = new ViewHolder();
            h.ch = (CheckBox) v.findViewById(R.id.settingsItemCheckBox);
            h.tv = (TextView) v.findViewById(R.id.settingsItemTV);
            h.tv2 = (TextView) v.findViewById(R.id.settingsItemTV2);
            h.tv3 = (TextView) v.findViewById(R.id.settingsCoinName);
            v.setTag(h);

            v.setOnClickListener(new itemClickListener(position, h.ch));

            if (markets.get(position).isVisible()) {
                h.ch.setChecked(true);
            } else {
                h.ch.setChecked(false);
            }

            h.tv.setText("" + markets.get(position).getSecondarycode().toUpperCase() + "/");
            h.tv2.setText(markets.get(position).getPrimarycode().toUpperCase());
            h.tv3.setText(markets.get(position).getPrimaryname().length() <= 12 ? Pairs.getAllMarkets().get(position).getPrimaryname() : Pairs.getAllMarkets().get(position).getPrimaryname().substring(0, 12));

            //Log.i(TAG, "" + Pairs.getAllMarkets().get(position).getPrimarycode().toUpperCase() + "/" + Pairs.getAllMarkets().get(position).getSecondarycode().toUpperCase() + " added to gridview");


            //Attach your custom CheckBoxClickListener to the checkbox
            //h.ch.setOnClickListener(new itemClickListener(position, h.ch));

            return v;
        }

        public final int getCount() {
            return markets.size();

        }

        public final long getItemId(int position) {
            return position;
        }

        public final String getItem(int position) {
            return "" + markets.get(position).getSecondarycode().toUpperCase() + "/" + markets.get(position).getPrimarycode().toUpperCase();

        }
    }

    //Custom OnClickListener for the checkboxes in settings
    public class itemClickListener implements View.OnClickListener {

        int position;
        CheckBox ch;

        public itemClickListener(int _position, CheckBox ch) {

            this.position = _position;
            this.ch = ch;


        }

        @Override
        public void onClick(View v) {

            if (!ch.isChecked()) {

                //Run this when the CheckBox goes from unchecked to checked:
                Log.i("aaa", "CheckBox " + markets.get(position).getSecondarycode().toUpperCase() + "/" + markets.get(position).getPrimarycode().toUpperCase() + " is checked");
                //Set the Pairs entry to true
                markets.get(position).setVisible(true);
                DatabaseHandler db = new DatabaseHandler(getActivity());
                db.setVis(Pairs.getAllMarkets().get(position), 1);
                ch.setChecked(true);
                db.close();
            }

            //Run this when the CheckBox goes from checked to unchecked:
            else {
                Log.i("aaa", "CheckBox " + Pairs.getAllMarkets().get(position).getSecondarycode().toUpperCase() + "/" + Pairs.getAllMarkets().get(position).getPrimarycode().toUpperCase() + " is UNchecked");
                markets.get(position).setVisible(false);
                DatabaseHandler db = new DatabaseHandler(getActivity());
                db.setVis(Pairs.getAllMarkets().get(position), 0);
                ch.setChecked(false);
                db.close();

            }

        }
    }

}
