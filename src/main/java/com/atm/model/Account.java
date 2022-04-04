package com.atm.model;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


//mark class as an Entity 
@Entity
//defining class name as Table name
@Table(name = "ACCOUNT")
public class Account 
{
	//mark id as primary key
	@Id
	@Column
	private long acno;
	
	@Column
	private int pin;
	
	@Column
	private BigDecimal balance;
	
	@Column
	private BigDecimal overdraft;
	
	
	
	public long getAcno() {
		return acno;
	}
	public void setAcno(long acno) {
		this.acno = acno;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getOverdraft() {
		return overdraft;
	}
	public void setOverdraft(BigDecimal overdraft) {
		this.overdraft = overdraft;
	}



}