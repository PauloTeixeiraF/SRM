package com.srm.test.util;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class MathUtils {

	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
	
	public BigDecimal calculateInterestRate(BigDecimal value, BigDecimal rate) {
		return value.multiply(rate).divide(ONE_HUNDRED);
	}
	
}
