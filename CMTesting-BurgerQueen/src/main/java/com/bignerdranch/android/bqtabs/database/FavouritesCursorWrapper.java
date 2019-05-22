package com.bignerdranch.android.bqtabs.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.bqtabs.Favourite;

public class FavouritesCursorWrapper extends CursorWrapper {

    public FavouritesCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Favourite getFavouriteItem(){
        String itemid = getString(getColumnIndex(BurgerQueenDBSchema.FavouritesTable.Cols.ITEMID));

        int itemID = Integer.parseInt(itemid);

        Favourite favourite = new Favourite(itemID);

        return favourite;

    }

}
