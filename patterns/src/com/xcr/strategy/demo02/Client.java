package com.xcr.strategy.demo02;

public class Client {


    public static void main(String[] args) {
        RMBPayStrategy rmbPayStrategy = new RMBPayStrategy();
        DollarPayStrategy dollarPayStrategy = new DollarPayStrategy();
        PayContext payContext = new PayContext("jack", 100, dollarPayStrategy);
        payContext.pay();
    }
}
