package com.bignerdranch.android.bqtabs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.bqtabs.database.BurgerQueenDBHelper;
import com.bignerdranch.android.bqtabs.database.BurgerQueenDBSchema;
import com.bignerdranch.android.bqtabs.database.SpecialCursorWrapper;

import java.util.ArrayList;
import java.util.List;

public class SpecialsBase {

    private static SpecialsBase specialsBase;

    private SQLiteDatabase db;
    private Context current_context;

    private SpecialsBase(Context context){

        current_context = context.getApplicationContext();
        db = new BurgerQueenDBHelper(current_context).getWritableDatabase();

    }

    public static SpecialsBase get(Context context) {
        if (specialsBase == null) {
            specialsBase = new SpecialsBase(context);
        }

        return specialsBase;
    }

    private SpecialCursorWrapper querySpecialItems(String whereClause, String[] whereArgs) {
        Cursor cursor = db.query(
                BurgerQueenDBSchema.SpecialsTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new SpecialCursorWrapper(cursor);
    }

    public List<MenuItem> getSpecialMenuItems() {
        List<MenuItem> items = new ArrayList<>();
        SpecialCursorWrapper cursor = querySpecialItems(null, null);
        MenuInventory inventory = MenuInventory.get(current_context);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int specialitemid = cursor.getSpecialItem().getItemid();
                MenuItem specialitem = inventory.getMenuItem(specialitemid);
                items.add(specialitem);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return items;
    }

}

