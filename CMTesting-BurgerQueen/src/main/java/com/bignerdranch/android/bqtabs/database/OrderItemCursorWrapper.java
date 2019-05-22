package com.bignerdranch.android.bqtabs.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.bqtabs.OrderItem;

public class OrderItemCursorWrapper extends CursorWrapper {

    public OrderItemCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public OrderItem getOrderItem(){
        String itemid = getString(getColumnIndex(BurgerQueenDBSchema.SpecialsTable.Cols.ITEMID));

        int itemID = Integer.parseInt(itemid);

        OrderItem orderItem = new OrderItem(itemID);

        return orderItem;

    }


}
