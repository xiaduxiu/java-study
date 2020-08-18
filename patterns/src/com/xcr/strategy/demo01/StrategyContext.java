package com.xcr.strategy.demo01;

import java.math.BigDecimal;

public class StrategyContext {

    private IStrategy strategy;

    public StrategyContext(IStrategy strategy) {
        this.strategy = strategy;
    }

    public BigDecimal quote(BigDecimal originalPrice) {
        return strategy.quote(originalPrice);
    }

}
