package com.ericturnerdev.CryptsyTicker;

import android.app.Fragment;
import android.content.Context;
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
 * Created by ericturner on 2/21/14.
 */
public class SettingsFragment extends Fragment {

    GridView mGridView;
    TextView mTextView;
    ArrayList<TradePair> pairs;
    Context mContext;
    String TAG = "SettingsFragment";

    /*Code for checkmarks and whatnot here*/
    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setRetainInstance(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        mGridView = (GridView)v.findViewById(R.id.settingsGrid);
        mTextView = (TextView)v.findViewById(R.id.settingsHeading);

        //Setup dummy SQLite Database
        DatabaseHandler db = new DatabaseHandler(getActivity());

        pairs = (ArrayList<TradePair>)db.getAllPairs();
        Log.i("aaaeee onCreateView", "Pairs from SQLite DB: " + pairs);
        //Log.i("aaa", "pairs count after: " + db.getPairsCount());

        //mGridView.setAdapter(new ArrayAdapter<TradePair>(getActivity(), android.R.layout.simple_list_item_1, pairs));

        mGridView.setAdapter(new SettingsGridAdapter(pairs));
        return v;

    }

    public class SettingsGridAdapter extends BaseAdapter {

        //Makes an arraylist of TradePairs
        ArrayList<TradePair> pairs;

        public SettingsGridAdapter(ArrayList<TradePair> _pairs){

            pairs = _pairs;

        }

        public View getView(int position, View convertView, ViewGroup parent){

            View v;
            TextView tv;
            TextView tv2;
            CheckBox ch;
            Log.i("aaaddd", "Populating Settings Grid.  pairs: " + pairs);

            if (convertView==null){

                v = LayoutInflater.from(getActivity()).inflate(R.layout.settings_item, null);

            } else{ v = convertView; }

            //v = LayoutInflater.from(getActivity()).inflate(R.layout.settings_item, null);
            tv = (TextView)v.findViewById(R.id.settingsItemTV);
            tv2 = (TextView)v.findViewById(R.id.settingsItemTV2);
            ch = (CheckBox)v.findViewById(R.id.settingsItemCheckBox);

            tv.setText("" + pairs.get(position).getMainCoin().toUpperCase() + "/");
            tv2.setText(pairs.get(position).getBaseCoin().toUpperCase());
            if (pairs.get(position).getVisible() == 1){
                ch.setChecked(true);
            }

            Log.i("aaafff", "" + pairs.get(position).getMainCoin().toUpperCase() + "/" + pairs.get(position).getBaseCoin().toUpperCase() + " added to gridview");


            //Attach your custom CheckBoxClickListener to the checkbox
            ch.setOnClickListener(new CheckBoxClickListener(position, pairs));

            //}

            //else {v = convertView; }
            Log.i("aaa", "Visible: " + pairs.get(position).getVisible());

            return v;
        }

        public final int getCount(){
            return pairs.size();

        }

        public final long getItemId(int position){
            return position;
        }

        public final String getItem(int position){
            return "" + pairs.get(position).getBaseCoin().toUpperCase() + "/" + pairs.get(position).getMainCoin().toUpperCase();

        }
    }

    //Custom OnClickListener for the checkboxes in settings
    public class CheckBoxClickListener implements View.OnClickListener{

        int position;
        ArrayList<TradePair> pairs;

        public CheckBoxClickListener(int _position, ArrayList<TradePair> _pairs){

            this.position = _position;
            this.pairs = _pairs;


        }

        @Override
        public void onClick(View v){

            if( ((CheckBox) v).isChecked() ) {

                //Run this when the CheckBox goes from unchecked to checked:
                Log.i("aaa", "CheckBox " + pairs.get(position).getBaseCoin().toUpperCase() + "/" + pairs.get(position).getMainCoin().toUpperCase() + " is checked");
                //I think I actually want to do SQLite database stuff here
                pairs.get(position).setVisible(1);
                DatabaseHandler db = new DatabaseHandler(getActivity());
                int upSuccess = db.updatePair(pairs.get(position));
                Log.i("aaa", "success for " + pairs.get(position).getBaseCoin().toUpperCase() + "/" + pairs.get(position).getMainCoin().toUpperCase() + " is" + upSuccess );
                Log.i("aaa", "Pairs from SQLite DB: " + db.getAllPairs());

            }
                //Run this when the CheckBox goes from checked to unchecked:
            else{
                Log.i("aaa", "CheckBox " + pairs.get(position).getBaseCoin().toUpperCase() + "/" + pairs.get(position).getMainCoin().toUpperCase() + " is UNchecked");
                pairs.get(position).setVisible(0);
                DatabaseHandler db = new DatabaseHandler(getActivity());
                int upSuccess = db.updatePair(pairs.get(position));
                Log.i("aaa", "success for " + pairs.get(position).getBaseCoin().toUpperCase() + "/" + pairs.get(position).getMainCoin().toUpperCase() + " is" + upSuccess );
                Log.i("aaa", "Pairs from SQLite DB: " + db.getAllPairs());

            }

        }

    }

}
