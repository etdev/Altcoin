package com.ericturnerdev.Altcoin;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Eric Turner (ericturnerdev) on 2/21/14.
 */
public class SettingsFragment extends Fragment {

    GridView mGridView;
    TextView mTextView;
    String TAG = "SettingsFragment";

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


    public class SettingsGridAdapter extends BaseAdapter {


        public View getView(int position, View convertView, ViewGroup parent) {

            View v;
            TextView tv;
            TextView tv2;
            CheckBox ch;
            //Log.i(TAG, "Populating Settings Grid.  pairs: " + Pairs.getAllMarkets());

            if (convertView == null) {

                v = LayoutInflater.from(getActivity()).inflate(R.layout.settings_item, null);

            } else {
                v = convertView;
            }

            //v = LayoutInflater.from(getActivity()).inflate(R.layout.settings_item, null);
            tv = (TextView) v.findViewById(R.id.settingsItemTV);
            tv2 = (TextView) v.findViewById(R.id.settingsItemTV2);
            ch = (CheckBox) v.findViewById(R.id.settingsItemCheckBox);
            if (Pairs.getAllMarkets().get(position).isVisible()) {
                ch.setChecked(true);
            } else {
                ch.setChecked(false);
            }

            tv.setText("" + Pairs.getAllMarkets().get(position).getSecondarycode().toUpperCase() + "/");
            tv2.setText(Pairs.getAllMarkets().get(position).getPrimarycode().toUpperCase());

            //Log.i(TAG, "" + Pairs.getAllMarkets().get(position).getPrimarycode().toUpperCase() + "/" + Pairs.getAllMarkets().get(position).getSecondarycode().toUpperCase() + " added to gridview");


            //Attach your custom CheckBoxClickListener to the checkbox
            ch.setOnClickListener(new CheckBoxClickListener(position, Pairs.getAllMarkets()));
            return v;
        }

        public final int getCount() {
            return Pairs.getAllMarkets().size();

        }

        public final long getItemId(int position) {
            return position;
        }

        public final String getItem(int position) {
            return "" + Pairs.getAllMarkets().get(position).getSecondarycode().toUpperCase() + "/" + Pairs.getAllMarkets().get(position).getPrimarycode().toUpperCase();

        }
    }

    //Custom OnClickListener for the checkboxes in settings
    public class CheckBoxClickListener implements View.OnClickListener {

        int position;

        public CheckBoxClickListener(int _position, ArrayList<Market> _pairs) {

            this.position = _position;


        }

        @Override
        public void onClick(View v) {

            if (((CheckBox) v).isChecked()) {

                //Run this when the CheckBox goes from unchecked to checked:
                //Log.i("aaa", "CheckBox " + Pairs.getAllMarkets().get(position).getSecondarycode().toUpperCase() + "/" + Pairs.getAllMarkets().get(position).getPrimarycode().toUpperCase() + " is checked");
                //Set the Pairs entry to true
                Pairs.getAllMarkets().get(position).setVisible(true);
                DatabaseHandler db = new DatabaseHandler(getActivity());
                db.setVis(Pairs.getAllMarkets().get(position), 1);
                db.close();
            }

            //Run this when the CheckBox goes from checked to unchecked:
            else {
                //Log.i("aaa", "CheckBox " + Pairs.getAllMarkets().get(position).getSecondarycode().toUpperCase() + "/" + Pairs.getAllMarkets().get(position).getPrimarycode().toUpperCase() + " is UNchecked");
                Pairs.getAllMarkets().get(position).setVisible(false);
                DatabaseHandler db = new DatabaseHandler(getActivity());
                db.setVis(Pairs.getAllMarkets().get(position), 0);
                db.close();

            }

        }

    }

}
