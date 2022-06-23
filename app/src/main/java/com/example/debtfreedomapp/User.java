package com.example.debtfreedomapp;

class User {

    public String name;
    public String starting_balance;
    public String minimum_payment, apr;
    public String remdate,paydate,category;

    public User(String name, String starting_balance, String minimum_payment, String apr, String remdate, String paydate, String category) {
        this.name = name;
        this.starting_balance = starting_balance;
        this.minimum_payment = minimum_payment;
        this.apr = apr;
        this.remdate = remdate;
        this.paydate = paydate;
        this.category = category;
    }
    public String gname;
    public String gstarting_balance;
    public String gremdate;

    public User(String gname, String gstarting_balance, String gremdate) {
        this.gname = gname;
        this.gstarting_balance = gstarting_balance;
        this.gremdate = gremdate;
    }


    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGstarting_balance() {
        return gstarting_balance;
    }

    public void setGstarting_balance(String gstarting_balance) {
        this.gstarting_balance = gstarting_balance;
    }

    public String getGremdate() {
        return gremdate;
    }

    public void setGremdate(String gremdate) {
        this.gremdate = gremdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStarting_balance() {
        return starting_balance;
    }

    public void setStarting_balance(String starting_balance) {
        this.starting_balance = starting_balance;
    }

    public String getMinimum_payment() {
        return minimum_payment;
    }

    public void setMinimum_payment(String minimum_payment) {
        this.minimum_payment = minimum_payment;
    }

    public String getApr() {
        return apr;
    }

    public void setApr(String apr) {
        this.apr = apr;
    }

    public String getRemdate() {
        return remdate;
    }

    public void setRemdate(String remdate) {
        this.remdate = remdate;
    }

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
}