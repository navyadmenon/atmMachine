package com.atm.util;

import java.math.BigDecimal;

import com.atm.enumeration.DenominationEnum;
import com.atm.exception.AtmException;
import com.atm.model.Account;
import com.atm.model.RequestData;

public class ValidationUtil {

	public static void validateAccDetails(Account ac) throws AtmException {

		if (null == ac) {
			throw new AtmException(AtmConstant.INVALID_PIN);
		}

	}

	/**
	 * The method performs below validation: 
	 * 1. If the requested amount is valid amount and multiple of lowest denomination 
	 * 2. If the atm holds enough money to dispense the requested amount 
	 * 3. If the account holder has enough balance in opening balance and overdraft limit
	 * 
	 * @param ac
	 * @param data
	 * @param totalamt
	 * @throws AtmException
	 */
	public static void validateAmount(Account ac, RequestData data, BigDecimal totalamt) throws AtmException {
		try {
			if (0 == data.getAmount() || data.getAmount() % DenominationEnum.FIVE.getAmountValue() != 0) {
				throw new AtmException(AtmConstant.INVALID_AMOUNT);
			}
			BigDecimal requestedAmt = new BigDecimal(data.getAmount());
			if (totalamt.compareTo(requestedAmt) < 0) {
				throw new AtmException(AtmConstant.INSUFFICIENT_AMT);
			}
			if (requestedAmt.compareTo(ac.getBalance().add(ac.getOverdraft())) > 0) {
				throw new AtmException(AtmConstant.INSUFFICIENT_BALANCE);
			}
		} catch (Exception ex) {
			throw ex;
		}

	}
}
