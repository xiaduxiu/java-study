package com.xcr.strategy.demo03;

public class PayContext {

    private String username;

    private double money;

    private PayStrategy payStrategy;

    public PayContext(String username, double money, PayStrategy payStrategy) {
        this.username = username;
        this.money = money;
        this.payStrategy = payStrategy;
    }

    public void pay() {
        payStrategy.pay(this);
    }

    public String getUsername() {
        return username;
    }

    public double getMoney() {
        return money;
    }

}
