package com.bignerdranch.android.bqtabs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.bignerdranch.android.bqtabs.database.BurgerQueenDBHelper;
import com.bignerdranch.android.bqtabs.database.BurgerQueenDBSchema.*;
import com.bignerdranch.android.bqtabs.database.OrderItemCursorWrapper;

public class OrderList {

    private static OrderList orderList;

    private SQLiteDatabase db;
    private Context current_context;

    private OrderList(Context context){

        current_context = context.getApplicationContext();
        db = new BurgerQueenDBHelper(current_context).getWritableDatabase();

    }

    public static OrderList get(Context context) {
        if (orderList == null) {
            orderList = new OrderList(context);
        }

        return orderList;
    }

    private OrderItemCursorWrapper queryOrderItems(String whereClause, String[] whereArgs) {
        Cursor cursor = db.query(
                 OrderTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new OrderItemCursorWrapper(cursor);
    }

    public List<MenuItem> getOrderMenuItems() {
        List<MenuItem> items = new ArrayList<>();
        OrderItemCursorWrapper cursor = queryOrderItems(null, null);
        MenuInventory inventory = MenuInventory.get(current_context);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int orderitemid = cursor.getOrderItem().getItemid();
                MenuItem orderitem = inventory.getMenuItem(orderitemid);
                items.add(orderitem);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return items;
    }

    public int getTotalOrderItems() {
        OrderItemCursorWrapper cursor = queryOrderItems(null, null);
        int total_items = 0;

        try {

                cursor.moveToNext();
                    total_items = cursor.getCount();

        } finally {
            cursor.close();
        }
        return total_items;
    }

    public void addOrderItem(int itemid){
        db.execSQL("INSERT INTO " + OrderTable.NAME  + " ('" + OrderTable.Cols.ITEMID + "') VALUES (" + itemid + ");");
    }

    public void removeOrderItem(int itemid){
        db.execSQL("DELETE FROM " + OrderTable.NAME + " WHERE " + OrderTable.Cols.ITEMID + " = " + itemid + ";");
    }

}
