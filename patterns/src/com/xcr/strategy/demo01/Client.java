package com.xcr.strategy.demo01;

import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) {
        OldCustomerStrategy newCustomerStrategy = new OldCustomerStrategy();
        StrategyContext strategyContext = new StrategyContext(newCustomerStrategy);
        System.out.println(strategyContext.quote(new BigDecimal(10)));
    }
}
