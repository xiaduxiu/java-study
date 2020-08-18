package com.xcr.strategy.demo03;

public class PayContextWhitAccount extends PayContext {

    private String account;

    public PayContextWhitAccount(String username, double money, String account, PayStrategy payStrategy) {
        super(username, money, payStrategy);
        this.account = account;
    }

    public String getAccount() {
        return account;
    }
}
