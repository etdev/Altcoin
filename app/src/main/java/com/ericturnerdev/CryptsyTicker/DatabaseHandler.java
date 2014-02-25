package com.ericturnerdev.CryptsyTicker;

/**
 * Class for handling database operations
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {


    String TAG = "DatabaseHandler";

    //All Static variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "cryptsy";

    //Table A PAIRS:
    //marketId PRIMARY KEY, mainCoin, baseCoin, lastTradePrice, lastTradeTime, volume, visible

    //Table B TRADES(recentTrades):
    //marketId FOREIGN KEY, time, price, quantity, total

    //Table C SELL(sellOrders):
    //marketId FOREIGN KEY, price, quantity, total

    //Table D BUY(buyOrders):
    //marketId FOREIGN KEY, price, quantity, total

    //Table E VISIBILITY:
    //marketId FOREIGN KEY, visible

    private static final String TABLE_PAIRS = "pairs";
        private static final String PAIRS_MARKETID = "marketid";
        private static final String PAIRS_LABEL = "label";
        private static final String PAIRS_LASTTRADEPRICE = "lasttradeprice";
        private static final String PAIRS_VOLUME = "volume";
        private static final String PAIRS_LASTTRADETIME = "lasttradetime";
        private static final String PAIRS_PRIMARYNAME = "primaryname";
        private static final String PAIRS_PRIMARYCODE = "primarycode";
        private static final String PAIRS_SECONDARYNAME = "secondaryname";
        private static final String PAIRS_SECONDARYCODE = "secondarycode";

    private static final String TABLE_TRADES = "trades";
        private static final String TRADES_MARKETID = "marketid";
        private static final String TRADES_TIME = "time";
        private static final String TRADES_PRICE = "price";
        private static final String TRADES_QUANTITY = "quantity";
        private static final String TRADES_TOTAL = "total";

    private static final String TABLE_SELL = "sell";
        private static final String SELL_MARKETID = "marketid";
        private static final String SELL_PRICE = "price";
        private static final String SELL_QUANTITY = "quantity";
        private static final String SELL_TOTAL = "total";

    private static final String TABLE_BUY = "buy";
        private static final String BUY_MARKETID = "marketid";
        private static final String BUY_PRICE = "price";
        private static final String BUY_QUANTITY = "quantity";
        private static final String BUY_TOTAL = "total";

    private static final String TABLE_VISIBILITY = "visibility";
        private static final String VIS_MARKETID = "marketid";
        private static final String VIS_VISIBLE = "visible";

    public DatabaseHandler(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    //Create tables initially (first time the app is ever run)
    @Override
    public void onCreate(SQLiteDatabase db){

        //Create Pairs Table
        String CREATE_PAIRS_TABLE = "CREATE TABLE " + TABLE_PAIRS + "("
                + PAIRS_MARKETID + " INTEGER, "
                + PAIRS_LABEL + " TEXT, "
                + PAIRS_LASTTRADEPRICE + " REAL, "
                + PAIRS_VOLUME + " REAL, "
                + PAIRS_LASTTRADETIME + " TEXT, "
                + PAIRS_PRIMARYNAME + " TEXT, "
                + PAIRS_PRIMARYCODE + " TEXT, "
                + PAIRS_SECONDARYNAME + " TEXT, "
                + PAIRS_SECONDARYCODE + " TEXT"
                + ")";
        //Debug:
        Log.i(TAG, "CREATE_PAIRS_TABLE: " + CREATE_PAIRS_TABLE);

        //Create Trades Table
        String CREATE_TRADES_TABLE = "CREATE TABLE " + TABLE_TRADES + "("
                + TRADES_MARKETID + " INTEGER, "
                + TRADES_TIME + " TEXT, "
                + TRADES_PRICE + " REAL, "
                + TRADES_QUANTITY + " REAL, "
                + TRADES_TOTAL + " REAL"
                + ")";
        //Debug:
        Log.i(TAG, "CREATE_TRADES_TABLE: " + CREATE_TRADES_TABLE);

        //Create Sell Table
        String CREATE_SELL_TABLE = "CREATE TABLE " + TABLE_SELL + "("
                + SELL_MARKETID + " INTEGER, "
                + SELL_PRICE + " REAL, "
                + SELL_QUANTITY + " REAL, "
                + SELL_TOTAL + " REAL"
                + ")";

        //Create Buy Table
        String CREATE_BUY_TABLE = "CREATE TABLE " + TABLE_BUY + "("
                + BUY_MARKETID + " INTEGER, "
                + BUY_PRICE + " REAL, "
                + BUY_QUANTITY + " REAL, "
                + BUY_TOTAL + " REAL"
                + ")";

        //Create Visibility Table
        String CREATE_VIS_TABLE = "CREATE TABLE " + TABLE_VISIBILITY + "("
                + VIS_MARKETID + " INTEGER, "
                + VIS_VISIBLE + " REAL"
                + ")";

        db.execSQL(CREATE_PAIRS_TABLE);
        db.execSQL(CREATE_TRADES_TABLE);
        db.execSQL(CREATE_SELL_TABLE);
        db.execSQL(CREATE_BUY_TABLE);
        db.execSQL(CREATE_VIS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        //Drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAIRS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRADES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SELL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VISIBILITY);

        //Create tables again
        onCreate(db);

    }

    //Add Trade Pair
    public void addPair(Market m){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PAIRS_MARKETID, m.getMarketid());
        values.put(PAIRS_LABEL, m.getLabel());
        values.put(PAIRS_LASTTRADEPRICE, m.getLasttradeprice());
        values.put(PAIRS_VOLUME, m.getMarketid());
        values.put(PAIRS_LASTTRADETIME, m.getLasttradetime());
        values.put(PAIRS_PRIMARYNAME, m.getPrimaryname());
        values.put(PAIRS_PRIMARYCODE, m.getPrimarycode());
        values.put(PAIRS_SECONDARYNAME, m.getSecondaryname());
        values.put(PAIRS_SECONDARYCODE, m.getSecondarycode());

        //Inserting
        db.insert(TABLE_PAIRS, null, values);
        db.close();

    }

    //Add row to Trades
    public void addTrade(Market m, Market.TradeItem ti){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TRADES_MARKETID, m.getMarketid());
        values.put(TRADES_TIME, ti.getTime());
        values.put(TRADES_PRICE, ti.getPrice());
        values.put(TRADES_QUANTITY, ti.getQuantity());
        values.put(TRADES_TOTAL, ti.getTotal());

        //Insert
        db.insert(TABLE_TRADES, null, values);
        db.close();
    }

    //Add row to Sell
    public void addSell(Market m, Market.OrderItem oi){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SELL_MARKETID, m.getMarketid());
        values.put(SELL_PRICE, oi.getPrice());
        values.put(SELL_QUANTITY, oi.getQuantity());
        values.put(SELL_TOTAL, oi.getTotal());

        //Insert
        db.insert(TABLE_SELL, null, values);
        db.close();
    }

    //Add row to Buy
    public void addBuy(Market m, Market.OrderItem oi){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BUY_MARKETID, m.getMarketid());
        values.put(BUY_PRICE, oi.getPrice());
        values.put(BUY_QUANTITY, oi.getQuantity());
        values.put(BUY_TOTAL, oi.getTotal());

        //Insert
        db.insert(TABLE_BUY, null, values);
        db.close();
    }

    //Set Visibility
    public void addVis(Market m, int vis){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(VIS_MARKETID, m.getMarketid());
        values.put(VIS_VISIBLE, vis);

        //Insert
        db.insert(TABLE_BUY, null, values);
        db.close();

    }

    //Get TradePair
    /*
    public Market getTradePair(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        //FIGURE OUT WHAT THIS LINE IS DOING
        //                                                          id, base, main, price, buy, sell, volume, visible
        Cursor cursor = db.query(TABLE_PAIRS, new String[] {KEY_ID, KEY_BASE, KEY_MAIN, KEY_PRICE, KEY_BUY, KEY_SELL, KEY_VOLUME, KEY_VISIBLE}, KEY_ID + "=?", new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()){
            TradePair tp = new TradePair(cursor.getInt(0), cursor.getDouble(1), cursor.getDouble(2), cursor.getDouble(3), cursor.getDouble(4), cursor.getString(5), cursor.getString(6), Integer.parseInt(cursor.getString(7)));
            //Log.i("DatabaseHandler", "aaa: I know cursor is NOT null");
            return tp;
        }
        else{ //Log.i("DatabaseHandler", "aaa: I know cursor is null");
            return null; }


        //return null;
    }
    */

    /*
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
    */

    public String printPairs(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PAIRS, new String[] {PAIRS_MARKETID, PAIRS_LABEL, PAIRS_LASTTRADEPRICE, PAIRS_VOLUME, PAIRS_LASTTRADETIME, PAIRS_PRIMARYNAME, PAIRS_PRIMARYCODE, PAIRS_SECONDARYNAME, PAIRS_SECONDARYCODE}, null, null, null, null, null, null);
        return cursor.toString();

    }
    //getPairsCount
    public int getMarketsCount(){

        String countQuery = "SELECT * FROM " + TABLE_PAIRS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();

    }

    //Update Pairs Table
    public int updatePair(Market m){

        SQLiteDatabase db = this.getWritableDatabase();

        //Update Pairs table
        ContentValues values = new ContentValues();
        values.put(PAIRS_MARKETID, m.getMarketid());
        values.put(PAIRS_LABEL, m.getLabel());
        values.put(PAIRS_LASTTRADEPRICE, m.getLasttradeprice());
        values.put(PAIRS_VOLUME, m.getMarketid());
        values.put(PAIRS_LASTTRADETIME, m.getLasttradetime());
        values.put(PAIRS_PRIMARYNAME, m.getPrimaryname());
        values.put(PAIRS_PRIMARYCODE, m.getPrimarycode());
        values.put(PAIRS_SECONDARYNAME, m.getSecondaryname());
        values.put(PAIRS_SECONDARYCODE, m.getSecondarycode());

        return db.update(TABLE_PAIRS, values, PAIRS_MARKETID + " = ?", new String[] {String.valueOf(m.getMarketid())});

    }

    //NOTE: STILL NEED TO ADD UPDATING AND DELETING

    /*
    //Deleting single pair
    public void deleteTradePair(TradePair tp){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PAIRS, KEY_ID + " = ?", new String[]{String.valueOf(tp.getMarketId())});
        db.close();

    }
    */



    //Clear table
    public void clearTable(String tblName){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tblName, null, null);

    }


}
