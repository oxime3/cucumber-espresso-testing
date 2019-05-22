package com.bignerdranch.android.bqtabs.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.bqtabs.CouponItem;

public class CouponCursorWrapper extends CursorWrapper {

    public CouponCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public CouponItem getCouponItem(){

        String couponName = getString(getColumnIndex(BurgerQueenDBSchema.CouponsTable.Cols.COUPONNAME));
        String couponId = getString(getColumnIndex(BurgerQueenDBSchema.CouponsTable.Cols.COUPONID));
        String couponDis = getString(getColumnIndex(BurgerQueenDBSchema.CouponsTable.Cols.DISCOUNT));

        int discount = Integer.parseInt(couponDis);



        CouponItem item = new CouponItem(couponName, couponId, discount);
        return  item;
    }
}

