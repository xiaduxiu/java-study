package com.xcr.strategy.demo01;

import java.math.BigDecimal;

public interface IStrategy {

    BigDecimal quote(BigDecimal originalPrice);

}
