package com.ericturnerdev.CryptsyTicker;

/**
 * Class for handling database operations
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    String TAG = "DatabaseHandler";

    //All Static variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "cryptsy";

    private static final String TABLE_VISIBILITY = "visibility";
    private static final String VIS_MARKETID = "marketid";
    private static final String VIS_VISIBLE = "visible";

    public DatabaseHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    //Create tables initially (first time the app is ever run)
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Log.i(TAG, "DatabaseHandler onCreate CALLED");

        //Create Visibility Table
        String CREATE_VIS_TABLE = "CREATE TABLE " + TABLE_VISIBILITY + "("
                + VIS_MARKETID + " INTEGER PRIMARY KEY, "
                + VIS_VISIBLE + " REAL"
                + ")";


        db.execSQL(CREATE_VIS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop older table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VISIBILITY);

        //Create tables again
        onCreate(db);

    }

    public void setVis(Market m, int vis) {


        //Check if the given data exists in the table:
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(VIS_MARKETID, m.getMarketid());
        values.put(VIS_VISIBLE, vis);

        Cursor cur = db.rawQuery("select * from " + TABLE_VISIBILITY + " where " + VIS_MARKETID + "  = '" + m.getMarketid() + "'", null);

        //If select doesn't return anything
        if (cur.getCount() == 0) {
            //Insert
            db.insert(TABLE_VISIBILITY, null, values);
            //db.execSQL("insert or replace into " + TABLE_VISIBILITY + " (" + VIS_MARKETID + ", " + VIS_VISIBLE + ") values (null" + ", " + vis + ")");
            db.close();

        } else {

            db.execSQL("UPDATE " + TABLE_VISIBILITY + " SET " + VIS_VISIBLE + "='" + vis + "' WHERE " + VIS_MARKETID + "='" + m.getMarketid() + "'");

        }

    }

    //getMarketsCount
    public int getMarketsCount() {

        int temp;
        String countQuery = "SELECT * FROM " + TABLE_VISIBILITY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        temp = cursor.getCount();
        cursor.close();

        //return count
        return temp;

    }

    //NOTE: STILL NEED TO ADD UPDATING AND DELETING

    public Cursor printMarkets() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_VISIBILITY, null);

        return cursor;

    }

    //Clear table
    public void clearTable(String tblName) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tblName, null, null);

    }

    public void dropTable(String tblName) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("drop table " + tblName);

    }


}
