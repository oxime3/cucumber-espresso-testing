package com.bignerdranch.android.bqtabs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bignerdranch.android.bqtabs.database.BurgerQueenDBHelper;
import com.bignerdranch.android.bqtabs.database.BurgerQueenDBSchema;
import com.bignerdranch.android.bqtabs.database.CouponCursorWrapper;

import java.util.ArrayList;
import java.util.List;

public class CouponList {
    private static CouponList couponList;
    private SQLiteDatabase db;
    private Context current_context;

    private CouponList(Context context) {

        current_context = context.getApplicationContext();
        db = new BurgerQueenDBHelper(current_context).getWritableDatabase();
    }

    public static CouponList get(Context context) {
        if (couponList == null) {
            couponList = new CouponList(context);
        }

        return couponList;
    }

    private CouponCursorWrapper queryCouponItems(String whereClause, String[] whereArgs) {
        Cursor cursor = db.query(
                BurgerQueenDBSchema.CouponsTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new CouponCursorWrapper(cursor);
    }

    public CouponItem getCouponItem(String couponId) {

        CouponCursorWrapper cursor = queryCouponItems(
                BurgerQueenDBSchema.CouponsTable.Cols.COUPONID + " = ?",
                new String[]{couponId}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCouponItem();
        } finally {
            cursor.close();
        }


    }
    public List<CouponItem> getAllCouponItems() {
        List<CouponItem> items = new ArrayList<>();
        CouponCursorWrapper cursor = queryCouponItems(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                items.add(cursor.getCouponItem());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return items;
    }

    public List<CouponItem> getCouponItems(int userid ) {
        List<CouponItem> items = new ArrayList<>();
        CouponCursorWrapper cursor = queryCouponItems("userid = ?", new String[]{String.valueOf(userid)});
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                items.add(cursor.getCouponItem());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return items;
    }

    public static void christmasCoupon(SQLiteDatabase db1, int user_id) {
        db1.execSQL("INSERT INTO coupons('" + BurgerQueenDBSchema.CouponsTable.Cols.COUPONNAME + "','" + BurgerQueenDBSchema.CouponsTable.Cols.COUPONID + "','" + BurgerQueenDBSchema.CouponsTable.Cols.DISCOUNT + "','" + BurgerQueenDBSchema.CouponsTable.Cols.USERID + "','" + BurgerQueenDBSchema.CouponsTable.Cols.COUPONUSED + "') VALUES ('Christmas','FESTIVE10',10, " + user_id + ", 'false');");

    }

    public static void awardFirstCoupon(SQLiteDatabase db1, int user_id) {
        Log.i("Waht is", String.valueOf(user_id));
        db1.execSQL("INSERT INTO coupons('" + BurgerQueenDBSchema.CouponsTable.Cols.COUPONNAME + "','" + BurgerQueenDBSchema.CouponsTable.Cols.COUPONID + "','" + BurgerQueenDBSchema.CouponsTable.Cols.DISCOUNT + "','" + BurgerQueenDBSchema.CouponsTable.Cols.USERID + "','" + BurgerQueenDBSchema.CouponsTable.Cols.COUPONUSED + "') VALUES ('New User','WELCOME15',15, " + user_id + ", 'false');");
    }


    public boolean couponAvailable(int user_id, String couponName) {
        CouponCursorWrapper cursor = queryCouponItems(
                BurgerQueenDBSchema.CouponsTable.Cols.COUPONNAME + " = ? AND " + "_id = ?", new String[]{couponName, String.valueOf(user_id)});
        try {

            if (cursor.getCount() == 0) {
                return true;
            } else {
                return false;
            }

        } finally {
            cursor.close();
        }

    }

}
