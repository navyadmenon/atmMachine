package com.atm.service;

import java.math.BigDecimal;
import java.util.List;

import com.atm.model.Denomination;

public interface DenominationService {

	/**
	 * calculates the total amount available in ATM based on the denomination
	 * and count of each denomination
	 * 
	 * @return total amount as BigDecimal
	 */
	public BigDecimal totalAmountInAtm();

	/**
	 * Returns list of all Denomination and its quantity available in the ATM
	 * machine
	 * 
	 * @return
	 */
	public List<Denomination> getAllDenominationDesc();

	/**
	 * Update the count of each denomination after each withdrawal
	 * 
	 * @param denomination
	 * @return
	 */
	public Denomination saveOrUpdate(Denomination denomination);
}
