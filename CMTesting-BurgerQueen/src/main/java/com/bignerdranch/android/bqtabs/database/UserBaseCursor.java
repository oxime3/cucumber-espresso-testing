package com.bignerdranch.android.bqtabs.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.bqtabs.User;
import com.bignerdranch.android.bqtabs.database.BurgerQueenDBSchema.UserTable;

public class UserBaseCursor extends CursorWrapper{

    public UserBaseCursor(Cursor cursor){
        super(cursor);
    }

    public User getUser(){
        String username = getString(getColumnIndex(UserTable.Cols.USERNAME));
        String password = getString(getColumnIndex(UserTable.Cols.PASSWORD));
        String email = getString(getColumnIndex(UserTable.Cols.EMAIL));
        String home_restaurant = getString(getColumnIndex(UserTable.Cols.HOME_RESTAURANT));
        String date_registered = getString(getColumnIndex(UserTable.Cols.DATE_REGISTERED));
        String first_login = getString(getColumnIndex(UserTable.Cols.FIRST_LOGIN_DATE));

        String id = getString(getColumnIndex("_id"));
        int int_id = Integer.parseInt(id);

        User user = new User(username, password, email, home_restaurant, date_registered,first_login);
        user.setId(int_id);

        return user;
    }

}

