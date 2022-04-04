package com.atm.service;

import com.atm.model.BalanceResponse;
import com.atm.model.DispenseResponse;
import com.atm.model.RequestData;

public interface AtmService {

	/**
	 * The method retrieves balance amount of the account holder. The balance
	 * excludes overdraft
	 * 
	 * @param data  that contain accountNo and pin
	 * @return BalanceResponse with details like accountNo and balance
	 * @throws Exception
	 */
	public BalanceResponse getBalance(RequestData data) throws Exception;

	/**
	 * The method withdraw the requested money from account of the account
	 * holder after proper validation
	 * 
	 * @param data
	 *            that contain accountNo , pin and requested amount
	 * @return DispenseResponse with dispensed currency/notes details and
	 *         balance amount.
	 * @throws Exception
	 */
	public DispenseResponse withdraw(RequestData data) throws Exception;

}
