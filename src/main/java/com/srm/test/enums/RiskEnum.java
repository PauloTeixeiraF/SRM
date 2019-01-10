package com.srm.test.enums;

public enum RiskEnum {
	
	A(0),
	B(10),
	C(20);

	public int interestRate;
	
	RiskEnum(int value){
		interestRate = value;
	}
	
	public int getInterestRate() {
		return interestRate;
	}
}
