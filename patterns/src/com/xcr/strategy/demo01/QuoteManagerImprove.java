package com.xcr.strategy.demo01;

import java.math.BigDecimal;

/**
 * 为每种类型的客户单独写方法, 缺点： 没有遵循对对象/方法扩展开放，对修改关闭原则。
 */
public class QuoteManagerImprove {

    public BigDecimal quote(BigDecimal originalPrice, String customType) {
        if ("新客户".equals(customType)) {
            System.out.println("新客户没有折扣");
            return this.quoteNewCustomer(originalPrice);
        } else if ("老客户".equals(customType)) {
            System.out.println("老客户有折扣");
            return this.quoteOldCustomer(originalPrice);
        } else if("VIP客户".equals(customType)) {
            System.out.println("VIP客户打8折！");
            return this.quoteVIPCustomer(originalPrice);
        }
        return originalPrice;
    }

    private BigDecimal quoteOldCustomer(BigDecimal originalPrice) {
        System.out.println("老客户打9折");
        originalPrice = originalPrice.multiply(new BigDecimal(0.9)).setScale(2,BigDecimal.ROUND_HALF_UP);
        return originalPrice;
    }

    private BigDecimal quoteVIPCustomer(BigDecimal originalPrice) {
        System.out.println("VIP客户打8折");
        originalPrice = originalPrice.multiply(new BigDecimal(0.8)).setScale(2,BigDecimal.ROUND_HALF_UP);
        return originalPrice;
    }

    private BigDecimal quoteNewCustomer(BigDecimal originalPrice) {
        System.out.println("新客户没有折扣！");
        return originalPrice;
    }
}
