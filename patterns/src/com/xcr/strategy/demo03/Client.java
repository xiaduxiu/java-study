package com.xcr.strategy.demo03;

public class Client {


    public static void main(String[] args) {
        RMBPayStrategy rmbPayStrategy = new RMBPayStrategy();
        DollarPayStrategy dollarPayStrategy = new DollarPayStrategy();
        PayContext payContext = new PayContext("jack", 1000, dollarPayStrategy);
        payContext.pay();

        payContext = new PayContext("xia", 10000, rmbPayStrategy);
        payContext.pay();

        PayStrategy accountPay = new AccountPay();
        payContext = new PayContextWhitAccount("小张", 40000,"1234567890", accountPay);
        payContext.pay();
    }
}
