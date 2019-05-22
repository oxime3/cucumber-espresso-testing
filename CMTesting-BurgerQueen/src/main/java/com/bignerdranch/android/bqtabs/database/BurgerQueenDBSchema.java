package com.bignerdranch.android.bqtabs.database;

public class BurgerQueenDBSchema {

    public static final class MenuTable{
        public static final String NAME = "menu";

        public static final class Cols{

            public static final String ITEMID = "itemid";
            public static final String ITEMNAME = "itemname";
            public static final String ITEMPRICE = "itemprice";
            public static final String ITEMIMAGE = "itemimage";
            public static final String ITEMCATEGORY = "itemcategory";
            public static final String ITEMDESCRIPTION = "itemdescription";

        }

    }


    public static final class FavouritesTable{
        public static final String NAME = "favourites";

        public static final class Cols{

            public static final String ITEMID = "itemid";

        }

    }

    public static final class UserTable{
        public static final String NAME = "user";

        public static final class Cols{

            public static final String USERNAME = "username";
            public static final String PASSWORD = "password";
            public static final String EMAIL = "email";
            public static final String HOME_RESTAURANT = "home_restaurant";
            public static final String DATE_REGISTERED = "date_registered";
            public static final String FIRST_LOGIN_DATE = "first_login";

        }

    }

    public static final class SpecialsTable{
        public static final String NAME = "specials";

        public static final class Cols{

            public static final String ITEMID = "itemid";

        }

    }

    public static final class CouponsTable{
        public static final String NAME = "coupons";

        public static final class Cols{

            public static final String COUPONNAME = "couponname";
            public static final String COUPONID = "couponid";
            public static final String DISCOUNT = "discount";
            public static final String USERID = "userid";
            public static final String COUPONUSED = "couponused";

        }

    }

    public static final class OrderTable{
        public static final String NAME = "orderlist";

        public static final class Cols{

            public static final String ITEMID = "itemid";

        }

    }


}
