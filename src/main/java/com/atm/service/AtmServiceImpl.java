package com.atm.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atm.exception.AtmException;
import com.atm.model.Account;
import com.atm.model.BalanceResponse;
import com.atm.model.Denomination;
import com.atm.model.DispenseResponse;
import com.atm.model.RequestData;
import com.atm.repository.AtmRepository;
import com.atm.util.AtmConstant;
import com.atm.util.ValidationUtil;

/**
 * Service implementation class for ATM operations
 * 
 * @author Navya
 *
 */
@Service
public class AtmServiceImpl implements AtmService {
	@Autowired
	AtmRepository atmRepository;

	@Autowired
	DenominationService denominationService;

	private static final String currency = " EURO";

	/**
	 * The method implementation to find the balance of the account holder. The
	 * method first validates if the provided account number and PIN are correct
	 * and proceed only if validated. If any validation error, proper error
	 * message is passed to user.
	 */
	public BalanceResponse getBalance(RequestData data) throws Exception {

		try {
			Account ac = atmRepository.findByAcnoAndPin(data.getAccountNo(), data.getPin());
			ValidationUtil.validateAccDetails(ac);
			return new BalanceResponse(ac.getAcno(), ac.getBalance(), AtmConstant.MAX_WITHDRAW_MONEY);
		} catch (AtmException e) {
			throw e;

		} catch (Exception ex) {
			throw ex;
		}

	}

	/**
	 * The method implementation to withdraw requested amount. The method
	 * performs validation to check if accountNo and pin is correct. The method
	 * also validates if requested amount is valid and is less than what ATM
	 * holds. If any validation error, then proper error message is passed to
	 * the user. If the requested amount is more than the balance in account,
	 * then it checks for overdraft limit and retrieves from that. If the
	 * overdraft limit also exceeded or less than needed, proper error message
	 * is shown to user for insufficient balance.
	 */
	public DispenseResponse withdraw(RequestData data) throws Exception {
		try {
			Account ac = atmRepository.findByAcnoAndPin(data.getAccountNo(), data.getPin());
			ValidationUtil.validateAccDetails(ac);
			ValidationUtil.validateAmount(ac, data, denominationService.totalAmountInAtm());

			Map<String, Integer> dispensedDenom = new HashMap<String, Integer>();

			countCurrency(data, dispensedDenom);
			updateAccountBalance(data, ac);

			DispenseResponse response = new DispenseResponse();
			response.setDispensedDenom(dispensedDenom);
			response.setBalance(ac.getBalance());
			response.setOverdraft(ac.getOverdraft());
			return response;
		} catch (AtmException e) {
			throw e;

		} catch (Exception ex) {
			throw ex;
		}

	}

	/*
	 * This method find the minimum number of notes available to dispense for
	 * the requested amount. It also updates the count of denomination or notes
	 * after successful withdrawal
	 * 
	 */
	private void countCurrency(RequestData data, Map<String, Integer> dispensedDenom) {
		List<Denomination> denomList = denominationService.getAllDenominationDesc();
		long amount = data.getAmount();
		for (Denomination denom : denomList) {
			int denomValue = denom.getDenomination();
			if (amount >= denomValue) {
				long noOfNotes = amount / denomValue;
				if (noOfNotes > denom.getQuantity()) {
					noOfNotes = denom.getQuantity();
				}
				amount = amount - (denomValue * noOfNotes);
				dispensedDenom.put(denomValue + currency, (int) noOfNotes);

				denom.setQuantity(denom.getQuantity() - (int) noOfNotes);
				denominationService.saveOrUpdate(denom);
			}
		}
	}

	/*
	 * Updates the account balance and overdraft(if needed), after the
	 * withdrawal
	 */
	private void updateAccountBalance(RequestData data, Account ac) {
		// update account balances
		if (data.getAmount() > ac.getBalance().longValue()) {
			long overdraftUsed = data.getAmount() - ac.getBalance().longValue();
			ac.setBalance(BigDecimal.ZERO);
			ac.setOverdraft(new BigDecimal(ac.getOverdraft().longValue() - overdraftUsed));
		} else {
			ac.setBalance(ac.getBalance().subtract(new BigDecimal(data.getAmount())));
		}
		atmRepository.save(ac);

	}

}