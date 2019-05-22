package com.bignerdranch.android.bqtabs.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.bignerdranch.android.bqtabs.database.BurgerQueenDBSchema.*;

import static com.bignerdranch.android.bqtabs.database.BurgerQueenDBFunc.createDummyUser;
import static com.bignerdranch.android.bqtabs.database.BurgerQueenDBFunc.populateMenuTable;
import static com.bignerdranch.android.bqtabs.database.BurgerQueenDBFunc.saveSpecials;

public class BurgerQueenDBHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "burgerQueen.db";

    public BurgerQueenDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

        final String CREATE_MENU_SQL = "CREATE TABLE " + MenuTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                MenuTable.Cols.ITEMID + " INTEGER, " +
                MenuTable.Cols.ITEMNAME + " TEXT, " +
                MenuTable.Cols.ITEMIMAGE + " TEXT, " +
                MenuTable.Cols.ITEMCATEGORY + " TEXT, " +
                MenuTable.Cols.ITEMDESCRIPTION + " TEXT, " +
                MenuTable.Cols.ITEMPRICE + " INTEGER" +
                ")";

        final String CREATE_FAVOURITES_SQL = "CREATE TABLE " + FavouritesTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                FavouritesTable.Cols.ITEMID + " INTEGER" +
                ")";

        final String CREATE_USERTABLE_SQL = "CREATE TABLE " + UserTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                UserTable.Cols.USERNAME + " TEXT, " +
                UserTable.Cols.PASSWORD + " VARCHAR, " +
                UserTable.Cols.EMAIL + " VARCHAR, " +
                UserTable.Cols.HOME_RESTAURANT + " TEXT, " +
                UserTable.Cols.DATE_REGISTERED + " DEFAULT CURRENT_TIMESTAMP, " +
                UserTable.Cols.FIRST_LOGIN_DATE + " VARCHAR " +
                ")";

        final String CREATE_SPECIALSTABLE_SQL = "CREATE TABLE " + SpecialsTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                SpecialsTable.Cols.ITEMID + " INTEGER" +
                ")";

        final String CREATE_ORDERTABLE_SQL = "CREATE TABLE " + OrderTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                OrderTable.Cols.ITEMID + " INTEGER " +
                ")";

        final String CREATE_COUPONSTABLE_SQL = "CREATE TABLE " + CouponsTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                CouponsTable.Cols.COUPONNAME + " TEXT, " +
                CouponsTable.Cols.COUPONID + " TEXT, " +
                CouponsTable.Cols.DISCOUNT + " INTEGER," +
                CouponsTable.Cols.USERID + " INTEGER, " +
                CouponsTable.Cols.COUPONUSED + " TEXT" +
                ")";

        db.execSQL(CREATE_MENU_SQL);
        db.execSQL(CREATE_FAVOURITES_SQL);
        db.execSQL(CREATE_USERTABLE_SQL);
        db.execSQL(CREATE_SPECIALSTABLE_SQL);
        db.execSQL(CREATE_COUPONSTABLE_SQL);
        db.execSQL(CREATE_ORDERTABLE_SQL);

        populateMenuTable(db);
        saveSpecials(db);
        createDummyUser(db);


    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public Cursor alluserdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from CREATE_USERTABLE_SQL ",null);
        return cursor;
    }

}
