package com.bignerdranch.android.bqtabs.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.bqtabs.MenuItem;
import com.bignerdranch.android.bqtabs.database.BurgerQueenDBSchema.*;


public class MenuCursorWrapper extends CursorWrapper {

    public MenuCursorWrapper(Cursor cursor) {
        super(cursor);
    }

        public MenuItem getMenuItem(){
            String itemid = getString(getColumnIndex(MenuTable.Cols.ITEMID));
            String itemname = getString(getColumnIndex(MenuTable.Cols.ITEMNAME));
            String itemprice = getString(getColumnIndex(MenuTable.Cols.ITEMPRICE));
            String itemimage = getString(getColumnIndex(MenuTable.Cols.ITEMIMAGE));
            String itemcategory = getString(getColumnIndex(MenuTable.Cols.ITEMCATEGORY));
            String itemdescription = getString(getColumnIndex(MenuTable.Cols.ITEMDESCRIPTION));

            //int itemID = Integer.parseInt(itemid);
            //int itemPrice = Integer.parseInt(itemprice);

            MenuItem item = new MenuItem(itemid, itemprice, itemname, itemimage, itemcategory, itemdescription);

                return item;

        }

    }

