package com.atm.enumeration;

public enum DenominationEnum {

	FIFTY(50),
	TWENTY(20),
	TEN(10),
	FIVE(5);
	
	private final int amountValue;
	
	DenominationEnum(int amountValue){
		this.amountValue = amountValue;
	}
	
	public int getAmountValue() {
		return this.amountValue;
	}
}
