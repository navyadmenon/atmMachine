package com.atm.model;

import java.math.BigDecimal;
import java.util.Map;

public class DispenseResponse {
	

	
	Map<String,Integer> dispensedDenom  ;
	BigDecimal balance;
	BigDecimal overdraft;
	
	
	
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public Map<String, Integer> getDispensedDenom() {
		return dispensedDenom;
	}
	public void setDispensedDenom(Map<String, Integer> dispensedDenom) {
		this.dispensedDenom = dispensedDenom;
	}
	public BigDecimal getOverdraft() {
		return overdraft;
	}
	public void setOverdraft(BigDecimal overdraft) {
		this.overdraft = overdraft;
	}
	
	
	

}
