package com.xcr.strategy.demo03;

public class DollarPayStrategy implements PayStrategy {

    @Override
    public void pay(PayContext ctx) {
        System.out.println("现在给："+ctx.getUsername()+" 支付美元 "+ctx.getMoney()+"！");

    }
}
