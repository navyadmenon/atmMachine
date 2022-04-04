package com.atm.model;

import java.math.BigDecimal;

public class BalanceResponse {
	
	private long acno;
	private BigDecimal balance;
	
	public BalanceResponse(long acno, BigDecimal balance) {
		this.acno  = acno;
		this.balance = balance;
	}
	
	public long getAcno() {
		return acno;
	}
	public void setAcno(long acno) {
		this.acno = acno;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	

}
