package com.xcr.strategy.demo01;

import java.math.BigDecimal;

public class OldCustomerStrategy implements IStrategy {
    @Override
    public BigDecimal quote(BigDecimal originalPrice) {
        System.out.println("老客户打9折");
        originalPrice = originalPrice.multiply(new BigDecimal(0.9)).setScale(2,BigDecimal.ROUND_HALF_UP);
        return originalPrice;
    }
}
