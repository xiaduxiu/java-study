package com.xcr.strategy.demo01;

import java.math.BigDecimal;

public class NewCustomerStrategy implements IStrategy {
    @Override
    public BigDecimal quote(BigDecimal originalPrice) {
        System.out.println("新客户");
        return originalPrice;
    }
}
