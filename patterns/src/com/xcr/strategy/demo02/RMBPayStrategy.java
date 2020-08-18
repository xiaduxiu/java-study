package com.xcr.strategy.demo02;

public class RMBPayStrategy implements PayStrategy {

    @Override
    public void pay(PayContext ctx) {
        System.out.println("现在给："+ctx.getUsername()+" 支付人民币 "+ctx.getMoney()+"元！");

    }
}
