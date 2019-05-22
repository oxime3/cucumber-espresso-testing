package com.bignerdranch.android.bqtabs.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.bqtabs.Special;


public class SpecialCursorWrapper extends CursorWrapper {

    public SpecialCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Special getSpecialItem(){
        String itemid = getString(getColumnIndex(BurgerQueenDBSchema.SpecialsTable.Cols.ITEMID));

        int itemID = Integer.parseInt(itemid);

        Special special = new Special(itemID);

        return special;

    }


}
