package com.atm.model;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


//mark class as an Entity 
@Entity
//defining class name as Table name
@Table(name = "DENOMINATION")
public class Denomination 
{
	//mark id as primary key
	@Id
	@Column
	private int denomination;
	
	@Column
	private int quantity;

	
	public int getDenomination() {
		return denomination;
	}

	public void setDenomination(int denomination) {
		this.denomination = denomination;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	

}