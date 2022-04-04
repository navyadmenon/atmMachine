package com.atm.model;

import java.math.BigDecimal;

public class BalanceResponse {
	
	private long acno;
	private BigDecimal balance;
	private int  maxWithdrawMoney;
	
	public BalanceResponse(long acno, BigDecimal balance,int maxWithdrawMoney) {
		this.acno  = acno;
		this.balance = balance;
		this.maxWithdrawMoney = maxWithdrawMoney;
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

	public int getMaxWithdrawMoney() {
		return maxWithdrawMoney;
	}

	public void setMaxWithdrawMoney(int maxWithdrawMoney) {
		this.maxWithdrawMoney = maxWithdrawMoney;
	}
	
	

}
