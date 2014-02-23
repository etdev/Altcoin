package com.ericturnerdev.CryptsyTicker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for handling database operations
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    //All Static variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "cryptsy";
    private static final String TABLE_PAIRS = "pairs";
    private static final String KEY_ID = "marketid";
    private static final String KEY_BASE = "basecoin";
    private static final String KEY_MAIN = "maincoin";
    private static final String KEY_PRICE = "lasttradeprice";
    private static final String KEY_BUY = "currentbuy";
    private static final String KEY_SELL = "currentsell";
    private static final String KEY_VOLUME = "volume";
    private static final String KEY_VISIBLE = "visible";

    public DatabaseHandler(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    //Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db){

        //id, base, main, price, buy, sell, volume, visible

        String CREATE_PAIRS_TABLE = "CREATE TABLE " + TABLE_PAIRS + "("
                + KEY_ID + " INTEGER, "
                + KEY_BASE + " TEXT, "
                + KEY_MAIN + " TEXT, "
                + KEY_PRICE + " REAL, "
                + KEY_BUY + " REAL, "
                + KEY_SELL + " REAL, "
                + KEY_VOLUME + " REAL, "
                + KEY_VISIBLE + " INTEGER"
                + ")";
        Log.i("aaa", "CREATE_PAIRS_TABLE: " + CREATE_PAIRS_TABLE);

        db.execSQL(CREATE_PAIRS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        //Drop older table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAIRS);

        //Create tables again
        onCreate(db);

    }

    //Add Trade Pair
    public void addTradePair(TradePair tp){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, tp.getMarketId());
        values.put(KEY_BASE, tp.getBaseCoin());
        values.put(KEY_MAIN, tp.getMainCoin());
        values.put(KEY_PRICE, tp.getLastTradePrice());
        values.put(KEY_BUY, tp.getCurrentBuy());
        values.put(KEY_SELL, tp.getCurrentSell());
        values.put(KEY_VOLUME, tp.getVolume());
        values.put(KEY_VISIBLE, tp.getVisible());


        //Inserting row
        db.insert(TABLE_PAIRS, null, values);
        db.close();


    }

    //Get TradePair
    public TradePair getTradePair(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        //FIGURE OUT WHAT THIS LINE IS DOING
        //                                                          id, base, main, price, buy, sell, volume, visible
        Cursor cursor = db.query(TABLE_PAIRS, new String[] {KEY_ID, KEY_BASE, KEY_MAIN, KEY_PRICE, KEY_BUY, KEY_SELL, KEY_VOLUME, KEY_VISIBLE}, KEY_ID + "=?", new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()){
            TradePair tp = new TradePair(cursor.getInt(0), cursor.getDouble(1), cursor.getDouble(2), cursor.getDouble(3), cursor.getDouble(4), cursor.getString(5), cursor.getString(6), Integer.parseInt(cursor.getString(7)));
            Log.i("DatabaseHandler", "aaa: I know cursor is NOT null");
            return tp;
        }
        else{ Log.i("DatabaseHandler", "aaa: I know cursor is null"); return null; }


        //return null;
    }

    //Get All TradePairs

    public List<TradePair> getAllPairs(){

        List<TradePair> tpList = new ArrayList<TradePair>();
        //Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PAIRS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //Loop through all rows and add to list
        if (cursor.moveToFirst()){

            do{
                TradePair tp = new TradePair();
                tp.setMarketId(Integer.parseInt(cursor.getString(0)));
                tp.setBaseCoin(cursor.getString(1));
                tp.setMainCoin(cursor.getString(2));
                tp.setLastTradePrice(Double.parseDouble(cursor.getString(3)));
                tp.setCurrentBuy(Double.parseDouble(cursor.getString(4)));
                tp.setCurrentSell(Double.parseDouble(cursor.getString(5)));
                tp.setVolume(Double.parseDouble(cursor.getString(6)));
                tp.setVisible(Integer.parseInt(cursor.getString(7)));


                //Add TradePair to list
                tpList.add(tp);

            } while (cursor.moveToNext());


        } //end if

        //return TradePair List
        return tpList;

    } //end getAllPairs

    //getPairsCount
    public int getPairsCount(){

        String countQuery = "SELECT * FROM " + TABLE_PAIRS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();

    }

    //Update single pair
    public int updatePair(TradePair tp){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, tp.getMarketId());
        values.put(KEY_PRICE, tp.getLastTradePrice());
        values.put(KEY_BUY, tp.getCurrentBuy());
        values.put(KEY_SELL, tp.getCurrentSell());
        values.put(KEY_BASE, tp.getBaseCoin());
        values.put(KEY_MAIN, tp.getMainCoin());
        values.put(KEY_VOLUME, tp.getVolume());
        values.put(KEY_VISIBLE, tp.getVisible());

        return db.update(TABLE_PAIRS, values, KEY_ID + " = ?", new String[] {String.valueOf(tp.getMarketId())});


    }

    //Deleting single pair
    public void deleteTradePair(TradePair tp){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PAIRS, KEY_ID + " = ?", new String[]{String.valueOf(tp.getMarketId())});
        db.close();

    }

    /*
    //Drop Table
    public void dropTable(String tblName){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE " + tblName);

    }
    */

    //Clear table
    public void clearTable(String tblName){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tblName, null, null);

    }


}
