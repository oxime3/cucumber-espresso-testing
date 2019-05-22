package com.bignerdranch.android.bqtabs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.bqtabs.database.BurgerQueenDBHelper;
import com.bignerdranch.android.bqtabs.database.BurgerQueenDBSchema.*;
import com.bignerdranch.android.bqtabs.database.MenuCursorWrapper;

import java.util.ArrayList;
import java.util.List;


public class MenuInventory {

    private static MenuInventory mMenuInventory;
    private SQLiteDatabase db;
    private Context mContext;

    public MenuInventory(Context context){

            mContext = context.getApplicationContext();
            db = new BurgerQueenDBHelper(mContext).getWritableDatabase();

    }

    public static MenuInventory get(Context context) {
        if (mMenuInventory == null) {
            mMenuInventory = new MenuInventory(context);
        }

        return mMenuInventory;
    }

    private MenuCursorWrapper queryMenuItems(String whereClause, String[] whereArgs) {
        Cursor cursor = db.query(
                MenuTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new MenuCursorWrapper(cursor);
    }

    public MenuItem getMenuItem(int itemid){

        String id = Integer.toString(itemid);

        MenuCursorWrapper cursor = queryMenuItems(
                MenuTable.Cols.ITEMID + " = ?",
                new String[]{id}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getMenuItem();
        } finally {
            cursor.close();
        }


    }

    public List<MenuItem> getMenuItems() {
        List<MenuItem> items = new ArrayList<>();
        MenuCursorWrapper cursor = queryMenuItems(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                items.add(cursor.getMenuItem());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return items;
    }

    public List<MenuItem> getCategoryMenuItems(String category) {
        List<MenuItem> items = new ArrayList<>();
        MenuCursorWrapper cursor = queryMenuItems(MenuTable.Cols.ITEMCATEGORY + " = ?" , new String[]{category});
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                    items.add(cursor.getMenuItem());

                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return items;
    }


}
