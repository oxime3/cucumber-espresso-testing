package com.bignerdranch.android.bqtabs;

public class User {

    private String username;
    private String password;
    private String email;
    private String home_restaurant;
    private String date_registered;
    private String first_date_login;
    private int id;

    public User(){

    }

    public User(String username, String password, String email, String home_restaurant, String date_registered, String first_date_login){
        this.username = username;
        this.password = password;
        this.email = email;
        this.home_restaurant = home_restaurant;
        this.date_registered = date_registered;
        this.first_date_login = first_date_login;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getHome_restaurant() {
        return home_restaurant;
    }

    public String getDate_registered() {
        return date_registered;
    }

    public String getPassword() {
        return password;
    }

    public String getFirst_date_login() {
        return first_date_login;
    }
}
