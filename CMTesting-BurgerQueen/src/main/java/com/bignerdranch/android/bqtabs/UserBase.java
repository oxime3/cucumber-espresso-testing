package com.bignerdranch.android.bqtabs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import com.bignerdranch.android.bqtabs.database.BurgerQueenDBHelper;
import com.bignerdranch.android.bqtabs.database.BurgerQueenDBSchema;

import com.bignerdranch.android.bqtabs.database.BurgerQueenDBHelper;
import com.bignerdranch.android.bqtabs.database.BurgerQueenDBSchema.UserTable;
import com.bignerdranch.android.bqtabs.database.UserBaseCursor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserBase {

    private static UserBase cUserBase;
    private SQLiteDatabase db;
    private Context current_context;

    private UserBase(Context context){

        current_context = context.getApplicationContext();
        db = new BurgerQueenDBHelper(current_context).getWritableDatabase();

    }

    public static UserBase get(Context context) {
        if (cUserBase == null) {
            cUserBase = new UserBase(context);
        }

        return cUserBase;
    }

    public void registerNewUser(String username, String password, String email, String home_restaurant, String date_registered){

        db.execSQL("INSERT INTO " + BurgerQueenDBSchema.UserTable.NAME +" ('"+ BurgerQueenDBSchema.UserTable.Cols.USERNAME +"', '"+ BurgerQueenDBSchema.UserTable.Cols.PASSWORD +"','"+ BurgerQueenDBSchema.UserTable.Cols.EMAIL +"','"+ BurgerQueenDBSchema.UserTable.Cols.HOME_RESTAURANT +"','"+ BurgerQueenDBSchema.UserTable.Cols.DATE_REGISTERED +"') VALUES('"+ username + "', '" + password + "', '" + email + "','" + home_restaurant + "','"+ date_registered +"');");


    }

    public void updateFirstLoginDate(int user_id){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String first_login_date = dateFormat.format(new Date());
        db.execSQL("UPDATE " + UserTable.NAME + " SET "+ UserTable.Cols.FIRST_LOGIN_DATE +  "='" + first_login_date  + "'WHERE _id ='" + user_id +"';");


    }

    public void updateUser(int userId, String userName, String password,String email, String home_restaurant){
        db.execSQL("UPDATE " + BurgerQueenDBSchema.UserTable.NAME + " SET "+ BurgerQueenDBSchema.UserTable.Cols.USERNAME +  "='" + userName + "'," + BurgerQueenDBSchema.UserTable.Cols.PASSWORD + "='" + password + "'," + BurgerQueenDBSchema.UserTable.Cols.EMAIL + "='" + email +"'," + BurgerQueenDBSchema.UserTable.Cols.HOME_RESTAURANT + "='" + home_restaurant + "'WHERE _id ='" + userId +"';");
    }

    private UserBaseCursor queryUserTable(String whereClause, String[] whereArgs){

        Cursor cursor = db.query(
                BurgerQueenDBSchema.UserTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );

        return new UserBaseCursor(cursor);
    }

    public User tryLogin(String username, String password){

        UserBaseCursor cursor = queryUserTable(UserTable.Cols.USERNAME + " = ? AND " + UserTable.Cols.PASSWORD + " = ?", new String[]{username, password});

        try {
            if (cursor.getCount() == 0) {
                return new User();
            }
            else
            {

                cursor.moveToFirst();
                User userdata = cursor.getUser();
                Log.i("person id", String.valueOf(userdata.getId()));
                if (userdata.getFirst_date_login() == null){
                    CouponList.awardFirstCoupon(db, userdata.getId());
                    updateFirstLoginDate(userdata.getId());
                    Log.i("award", "New");

                }


                CouponList couponList = CouponList.get(current_context);
                if (couponList.couponAvailable(userdata.getId(),"Christmas")){
                    Log.i("coupon","Christmas");
                    DateFormat dateFormat = new SimpleDateFormat("MM");
                    Date date = new Date();

                    String christmas = dateFormat.format(date);
                    if(christmas.equals("12")){
                        Log.i("award", "Christ");
                        CouponList.christmasCoupon(db, userdata.getId());
                    }

                }



                return  userdata;
            }
        } finally {
            cursor.close();
        }


    }

    public User getUserData(int userid){

        UserBaseCursor cursor = queryUserTable("_id = ?", new String[]{String.valueOf(userid)});

        try {
            if (cursor.getCount() == 0) {
                return new User();
            }
            else
            {
                cursor.moveToFirst();
                return cursor.getUser();
            }
        } finally {
            cursor.close();
        }


    }


}
