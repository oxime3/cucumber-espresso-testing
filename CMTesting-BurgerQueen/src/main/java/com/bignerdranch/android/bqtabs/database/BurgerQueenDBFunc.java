package com.bignerdranch.android.bqtabs.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bignerdranch.android.bqtabs.database.BurgerQueenDBSchema.*;

public class BurgerQueenDBFunc {

    public static void createDummyUser(SQLiteDatabase db){

        db.execSQL("INSERT INTO user  ('"+ UserTable.Cols.USERNAME +"', '"+ UserTable.Cols.PASSWORD +"','"+ UserTable.Cols.EMAIL +"','"+ UserTable.Cols.HOME_RESTAURANT +"','"+ UserTable.Cols.DATE_REGISTERED +"') VALUES('John Doe', '123', 'john@123.com','Suzhou','2018-11-12');");

    }


    public static void populateMenuTable(SQLiteDatabase db){

        db.execSQL("INSERT INTO menu ('" + MenuTable.Cols.ITEMID + "', '" + MenuTable.Cols.ITEMNAME +"','" + MenuTable.Cols.ITEMIMAGE + "','" + MenuTable.Cols.ITEMCATEGORY + "','" + MenuTable.Cols.ITEMDESCRIPTION +"','"+ MenuTable.Cols.ITEMPRICE +"') VALUES(1, 'Vanilla Shake','onions_rings','Desserts','Cool down with our creamy Vanilla Shake. Velvety Vanilla Soft Serve and vanilla sauce are blended to perfection and finished with sweet whipped topping just for you.',20 );");
        db.execSQL("INSERT INTO menu ('" + MenuTable.Cols.ITEMID + "', '" + MenuTable.Cols.ITEMNAME + "','" + MenuTable.Cols.ITEMIMAGE + "','" + MenuTable.Cols.ITEMCATEGORY + "','" + MenuTable.Cols.ITEMDESCRIPTION +"','"+ MenuTable.Cols.ITEMPRICE +"') VALUES(2, 'Royal Court Pie','onions_rings','Desserts','Say hello to our Royal Court Pie. One part crunchy chocolate crust and one part chocolate crème filling, garnished with a delicious topping of fudge drizzle and chocolate chips.',30);");
        db.execSQL("INSERT INTO menu ('" + MenuTable.Cols.ITEMID + "', '" + MenuTable.Cols.ITEMNAME + "','" + MenuTable.Cols.ITEMIMAGE + "','" + MenuTable.Cols.ITEMCATEGORY + "','" + MenuTable.Cols.ITEMDESCRIPTION +"','"+ MenuTable.Cols.ITEMPRICE +"') VALUES(3, 'Chocolate Chip Cookie','chocolate_chip','Desserts','A rich cookie sprinkled with sweet chocolate chips.',5);");
        db.execSQL("INSERT INTO menu ('" + MenuTable.Cols.ITEMID + "', '" + MenuTable.Cols.ITEMNAME +"','" + MenuTable.Cols.ITEMIMAGE + "','" + MenuTable.Cols.ITEMCATEGORY + "','" + MenuTable.Cols.ITEMDESCRIPTION +"','"+ MenuTable.Cols.ITEMPRICE +"')VALUES(4, 'Royal Fries','royal_fries','Sides','More delicious than ever, our piping hot, thick cut Royal Fries are golden on the outside and fluffy on the inside.',10);");
        db.execSQL("INSERT INTO menu ('" + MenuTable.Cols.ITEMID + "', '" + MenuTable.Cols.ITEMNAME +"','" + MenuTable.Cols.ITEMIMAGE + "','" + MenuTable.Cols.ITEMCATEGORY + "','" + MenuTable.Cols.ITEMDESCRIPTION +"','"+ MenuTable.Cols.ITEMPRICE +"') VALUES(5, 'Courtly Side Salad','onions_rings','Sides','Our Courtly Side Salad offers fresh lettuce garnished with juicy tomatoes, and shredded 3 cheese medley.  Add your choice of salad dressing: Ranch, Italian, Creamy Caesar, or Balsamic Vinaigrette.',15);");
        db.execSQL("INSERT INTO menu ('" + MenuTable.Cols.ITEMID + "', '" + MenuTable.Cols.ITEMNAME +"','" + MenuTable.Cols.ITEMIMAGE + "','" + MenuTable.Cols.ITEMCATEGORY + "','" + MenuTable.Cols.ITEMDESCRIPTION +"','"+ MenuTable.Cols.ITEMPRICE +"') VALUES(6, 'Onion Rings','onion_rings','Sides','Served hot and crispy, our golden Onion Rings are the perfect treat for plunging into one of our bold or classic sauces.',7);");
        db.execSQL("INSERT INTO menu ('" + MenuTable.Cols.ITEMID + "', '" + MenuTable.Cols.ITEMNAME +"','" + MenuTable.Cols.ITEMIMAGE + "','" + MenuTable.Cols.ITEMCATEGORY + "','" + MenuTable.Cols.ITEMDESCRIPTION +"','"+ MenuTable.Cols.ITEMPRICE +"') VALUES(7, 'Hash Browns','hashbrowns','Sides','Make your day sizzle with a side of our golden Hash Browns.',7);");
        db.execSQL("INSERT INTO menu ('" + MenuTable.Cols.ITEMID + "', '" + MenuTable.Cols.ITEMNAME +"','" + MenuTable.Cols.ITEMIMAGE + "','" + MenuTable.Cols.ITEMCATEGORY + "','" + MenuTable.Cols.ITEMDESCRIPTION +"','"+ MenuTable.Cols.ITEMPRICE +"') VALUES(8, 'Chicken Fries','chicken_fries','Sides','Chicken Fries are shaped like fries and are perfect to dip in any of our delicious dipping sauces. Our Chicken Fries are coated in a light crispy breading seasoned with savoury spices and herbs.',15);");
        db.execSQL("INSERT INTO menu ('" + MenuTable.Cols.ITEMID + "', '" + MenuTable.Cols.ITEMNAME +"','" + MenuTable.Cols.ITEMIMAGE + "','" + MenuTable.Cols.ITEMCATEGORY + "','" + MenuTable.Cols.ITEMDESCRIPTION +"','"+ MenuTable.Cols.ITEMPRICE +"') VALUES(9, 'Crispy Chicken Wrap','chicken_wrap','Sides','Get our Crispy Chicken Wrap with crispy chicken topped with sweet Thai sauce, shredded lettuce, crisp cucumber slices and creamy mayonnaise-style sauce all wrapped up in tortilla.',12);");
        db.execSQL("INSERT INTO menu ('" + MenuTable.Cols.ITEMID + "', '" + MenuTable.Cols.ITEMNAME +"','" + MenuTable.Cols.ITEMIMAGE + "','" + MenuTable.Cols.ITEMCATEGORY + "','" + MenuTable.Cols.ITEMDESCRIPTION +"','"+ MenuTable.Cols.ITEMPRICE +"') VALUES(10, 'Quarter Pound Queen','onions_rings','Burgers','Our Quarter Pound Queen Sandwich features a ¼ lb savory flame-grilled beef patty, topped with all of our classic favorites: melted cheese, freshly sliced onions, zesty pickles, ketchup, & mustard all on a toasted sesame seed bun.',25);");
        db.execSQL("INSERT INTO menu ('" + MenuTable.Cols.ITEMID + "', '" + MenuTable.Cols.ITEMNAME +"','" + MenuTable.Cols.ITEMIMAGE + "','" + MenuTable.Cols.ITEMCATEGORY + "','" + MenuTable.Cols.ITEMDESCRIPTION +"','"+ MenuTable.Cols.ITEMPRICE +"') VALUES(11, 'Double Quarter Pound Queen','dblqtrpndqueen','Burgers','Our Double Quarter Pound Queen Sandwich features two ¼ lb savory flame-grilled beef patties, topped with all of our classic favorites: melted cheese, freshly sliced onions, zesty pick.',30);");
        db.execSQL("INSERT INTO menu ('" + MenuTable.Cols.ITEMID + "', '" + MenuTable.Cols.ITEMNAME +"','" + MenuTable.Cols.ITEMIMAGE + "','" + MenuTable.Cols.ITEMCATEGORY + "','" + MenuTable.Cols.ITEMDESCRIPTION +"','"+ MenuTable.Cols.ITEMPRICE +"') VALUES(12, 'Triple Burger','onions_rings','Burgers','Our Triple Burger is stacked with three ¼ lb* savoury flame-grilled beef patties topped with juicy tomatoes, fresh cut lettuce, creamy mayonnaise, crunchy pickles, and sliced white onions on a toasted sesame seed bun.',28);");
        db.execSQL("INSERT INTO menu ('" + MenuTable.Cols.ITEMID + "', '" + MenuTable.Cols.ITEMNAME +"','" + MenuTable.Cols.ITEMIMAGE + "','" + MenuTable.Cols.ITEMCATEGORY + "','" + MenuTable.Cols.ITEMDESCRIPTION +"','"+ MenuTable.Cols.ITEMPRICE +"') VALUES(13, 'Bacon Queen','onions_rings','Burgers','Our Bacon Queen Sandwich features two ¼ lb savory flame-grilled beef patties, topped a with hearty portion of thick-cut smoked bacon, melted American cheese and topped with ketchup and creamy mayonnaise all on a soft sesame seed bun.',28);");
        db.execSQL("INSERT INTO menu ('" + MenuTable.Cols.ITEMID + "', '" + MenuTable.Cols.ITEMNAME +"','" + MenuTable.Cols.ITEMIMAGE + "','" + MenuTable.Cols.ITEMCATEGORY + "','" + MenuTable.Cols.ITEMDESCRIPTION +"','"+ MenuTable.Cols.ITEMPRICE +"') VALUES(14, 'Spicy Crispy Chicken','spicy_chicken','Burgers','Introducing the Spicy Crispy Chicken Sandwich made with white meat chicken filet, seasoned and breaded with bold flavors and just the right amount of heat; topped with fresh lettuce, a ripe tomato, and creamy mayonnaise.',21);");
        db.execSQL("INSERT INTO menu ('" + MenuTable.Cols.ITEMID + "', '" + MenuTable.Cols.ITEMNAME +"','" + MenuTable.Cols.ITEMIMAGE + "','" + MenuTable.Cols.ITEMCATEGORY + "','" + MenuTable.Cols.ITEMDESCRIPTION +"','"+ MenuTable.Cols.ITEMPRICE +"') VALUES(15, 'Tea of Nobility','onions_rings','Beverages','An aromatic brew of sweet tea.',11);");
        db.execSQL("INSERT INTO menu ('" + MenuTable.Cols.ITEMID + "', '" + MenuTable.Cols.ITEMNAME +"','" + MenuTable.Cols.ITEMIMAGE + "','" + MenuTable.Cols.ITEMCATEGORY + "','" + MenuTable.Cols.ITEMDESCRIPTION +"','"+ MenuTable.Cols.ITEMPRICE +"') VALUES(16, 'Coca Cola','coca_cola','Beverages','12oz of chilled coca cola.',9);");
        db.execSQL("INSERT INTO menu ('" + MenuTable.Cols.ITEMID + "', '" + MenuTable.Cols.ITEMNAME +"','" + MenuTable.Cols.ITEMIMAGE + "','" + MenuTable.Cols.ITEMCATEGORY + "','" + MenuTable.Cols.ITEMDESCRIPTION +"','"+ MenuTable.Cols.ITEMPRICE +"') VALUES(17, 'Sprite','onions_rings','Beverages','12oz of chilled sprite.',9);");
        db.execSQL("INSERT INTO menu ('" + MenuTable.Cols.ITEMID + "', '" + MenuTable.Cols.ITEMNAME +"','" + MenuTable.Cols.ITEMIMAGE + "','" + MenuTable.Cols.ITEMCATEGORY + "','" + MenuTable.Cols.ITEMDESCRIPTION +"','"+ MenuTable.Cols.ITEMPRICE +"') VALUES(18, 'Water','onions_rings','Beverages', 'A 330ml bottle of delicious Fiji water.',7);");
        db.execSQL("INSERT INTO menu ('" + MenuTable.Cols.ITEMID + "', '" + MenuTable.Cols.ITEMNAME +"','" + MenuTable.Cols.ITEMIMAGE + "','" + MenuTable.Cols.ITEMCATEGORY + "','" + MenuTable.Cols.ITEMDESCRIPTION +"','"+ MenuTable.Cols.ITEMPRICE +"') VALUES(19, 'Special1','smnelse','Special', 'Burger Combo.',4);");
        db.execSQL("INSERT INTO menu ('" + MenuTable.Cols.ITEMID + "', '" + MenuTable.Cols.ITEMNAME +"','" + MenuTable.Cols.ITEMIMAGE + "','" + MenuTable.Cols.ITEMCATEGORY + "','" + MenuTable.Cols.ITEMDESCRIPTION +"','"+ MenuTable.Cols.ITEMPRICE +"') VALUES(20, 'Special2','special2','Special', 'Raining tomatoes.',3);");



    }

    public static void saveSpecials(SQLiteDatabase db){
        db.execSQL("INSERT INTO specials('"+ SpecialsTable.Cols.ITEMID + "') VALUES (19)");
        db.execSQL("INSERT INTO specials('"+ SpecialsTable.Cols.ITEMID + "') VALUES (20)");
    }

    /*public static void populateData(SQLiteDatabase db){
        Log.i("coupon2","works");
        db.execSQL("INSERT INTO coupons('" + BurgerQueenDBSchema.CouponsTable.Cols.COUPONNAME + "','" + BurgerQueenDBSchema.CouponsTable.Cols.COUPONID + "','" + BurgerQueenDBSchema.CouponsTable.Cols.DISCOUNT + "','" + BurgerQueenDBSchema.CouponsTable.Cols.USERID + "','" + BurgerQueenDBSchema.CouponsTable.Cols.COUPONUSED + "') VALUES ('New User','WELCOME15',15,  2 , 'false');");
        db.execSQL("INSERT INTO coupons('" + BurgerQueenDBSchema.CouponsTable.Cols.COUPONNAME + "','" + BurgerQueenDBSchema.CouponsTable.Cols.COUPONID + "','" + BurgerQueenDBSchema.CouponsTable.Cols.DISCOUNT + "','" + BurgerQueenDBSchema.CouponsTable.Cols.USERID + "','" + BurgerQueenDBSchema.CouponsTable.Cols.COUPONUSED + "') VALUES ('Christmas','FESTIVE10',10, 2,'false');");

    }*/


}
