package com.bignerdranch.android.bqtabs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.bignerdranch.android.bqtabs.database.BurgerQueenDBHelper;
import com.bignerdranch.android.bqtabs.database.BurgerQueenDBSchema.*;
import com.bignerdranch.android.bqtabs.database.FavouritesCursorWrapper;


public class FavouritesBase {

    private static FavouritesBase favouritesBase;

    private SQLiteDatabase db;
    private Context current_context;

    private FavouritesBase(Context context){

        current_context = context.getApplicationContext();
        db = new BurgerQueenDBHelper(current_context).getWritableDatabase();

    }

    public static FavouritesBase get(Context context) {
        if (favouritesBase == null) {
            favouritesBase = new FavouritesBase(context);
        }

        return favouritesBase;
    }

    private FavouritesCursorWrapper queryFavouriteItems(String whereClause, String[] whereArgs) {
        Cursor cursor = db.query(
                FavouritesTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new FavouritesCursorWrapper(cursor);
    }

    public List<MenuItem> getFavouriteMenuItems() {
        List<MenuItem> items = new ArrayList<>();
        FavouritesCursorWrapper cursor = queryFavouriteItems(null, null);
        MenuInventory inventory = MenuInventory.get(current_context);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int favouriteitemid = cursor.getFavouriteItem().getItemid();
                MenuItem favouriteitem = inventory.getMenuItem(favouriteitemid);
                items.add(favouriteitem);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return items;
    }

    public void addFavourite(int itemid){
        db.execSQL("INSERT INTO " + FavouritesTable.NAME  + " ('" + FavouritesTable.Cols.ITEMID + "') VALUES (" + itemid + ");");
    }

    public void deleteFavourite(int itemid){
        db.execSQL("DELETE FROM " + FavouritesTable.NAME + " WHERE " + FavouritesTable.Cols.ITEMID + " = " + itemid + ";");
    }

}
