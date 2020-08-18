package com.xcr.strategy.demo03;

public class AccountPay implements PayStrategy {

    @Override
    public void pay(PayContext context) {
        PayContextWhitAccount ctxAccount = (PayContextWhitAccount) context;
        System.out.println("现在给："+ ctxAccount.getUsername()+"的账户："+ctxAccount.getAccount()+" 支付工资："+ctxAccount.getMoney()+" 元！");
    }
}
