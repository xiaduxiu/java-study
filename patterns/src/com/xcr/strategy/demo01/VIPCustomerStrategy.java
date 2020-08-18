package com.xcr.strategy.demo01;

import java.math.BigDecimal;

public class VIPCustomerStrategy implements IStrategy {
    @Override
    public BigDecimal quote(BigDecimal originalPrice) {
        System.out.println("VIP客户打8折");
        originalPrice = originalPrice.multiply(new BigDecimal(0.8)).setScale(2,BigDecimal.ROUND_HALF_UP);
        return originalPrice;
    }
}
