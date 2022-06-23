package com.example.debtfreedomapp.models;


public class Users {

    String userName;
    String mail;
    String password;
    String id;

    public Users(String userName, String mail, String password, String id) {
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




    String name,sbalance,mpayment,apr,category,paydate,remdate,userid;

    public Users(String name, String sbalance, String mpayment, String apr, String category, String paydate, String remdate, String userid) {
        this.name = name;
        this.sbalance = sbalance;
        this.mpayment = mpayment;
        this.apr = apr;
        this.category = category;
        this.paydate = paydate;
        this.remdate = remdate;
        this.id = userid;
    }
}
