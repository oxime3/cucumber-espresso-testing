package com.bignerdranch.android.bqtabs;

public class MenuItem {

    private String menuID;
    private  String price;
    private String name;
    private String image_path;
    private String category;
    private String description;

    public MenuItem(String menuID, String price, String name, String image_path, String category, String description){
        this.menuID = menuID;
        this.price = price;
        this.name = name;
        this.image_path = image_path;
        this.category = category;
        this.description = description;

    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMenuID() {
        return menuID;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getImage_path(){
        return image_path;
    }

    public String getDescription() {
        return description;
    }

    public int getPhoto() {
        return R.drawable.ic_local_drink_black_24dp;
    }



}
