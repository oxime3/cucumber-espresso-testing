package com.bignerdranch.android.bqtabs;

public class CouponItem {
    private String couponID;
    private String couponName;
    private int discount;

    public CouponItem(String couponName, String couponID, int discount){
        this.couponName = couponName;
        this.couponID = couponID;
        this.discount = discount;

    }

    public String getCouponID() {
        return couponID;
    }

    public void setCouponID(String couponID) {
        this.couponID = couponID;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public int getdiscount() {
        return discount;
    }


}

