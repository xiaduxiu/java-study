package com.xcr.strategy.demo01;

import java.math.BigDecimal;

/**
 * 把所有的算法放在同一个方法中，会特别臃肿，而且不易于扩展
 */
public class QuoteManager {

    public BigDecimal quote(BigDecimal originalPrice, String customType) {
        if ("新客户".equals(customType)) {
            System.out.println("新客户没有折扣");
            return originalPrice;
        } else if ("老客户".equals(customType)) {
            System.out.println("老客户有折扣");
            originalPrice = originalPrice.multiply(new BigDecimal(0.9)).setScale(2,BigDecimal.ROUND_HALF_UP);
            return originalPrice;
        } else if("VIP客户".equals(customType)) {
            System.out.println("VIP客户打8折！");
            originalPrice = originalPrice.multiply(new BigDecimal(0.8)).setScale(2, BigDecimal.ROUND_HALF_UP);
            return originalPrice;
        }
        return originalPrice;
    }

}
