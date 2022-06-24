package com.example.debtfreedomapp;

class User {

    public String deptname;
    public String starting_balance;
    public String minimum_payment, apr;
    public String remdate,paydate,category;

    public String gname;
    public String gstarting_balance;
    public String gremdate;


    public User(String deptname, String starting_balance, String minimum_payment, String apr, String remdate, String paydate, String category) {
        this.deptname = deptname;
        this.starting_balance = starting_balance;
        this.minimum_payment = minimum_payment;
        this.apr = apr;
        this.remdate = remdate;
        this.paydate = paydate;
        this.category = category;
    }

    public String getName() {
        return deptname;
    }

    public void setName(String name) {
        this.deptname = name;
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