package com.atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atm.model.BalanceResponse;
import com.atm.model.DispenseResponse;
import com.atm.model.ErrorResponse;
import com.atm.model.RequestData;
import com.atm.service.AtmService;
import com.atm.util.AtmConstant;

/**
 * The controller class of the application to perform the ATM operations as
 * described by API requirements
 * 
 * @author Navya
 *
 */
@RestController
@RequestMapping("/atm")
public class AtmController {

	@Autowired
	AtmService atmService;

	/**
	 * The POST controller method to retrieve balance of the user. The balance
	 * excludes overdraft.
	 * 
	 * @param data
	 *            which is of type RequestData that contain {"accountNo", "pin"}
	 *            passed by the user
	 * @return ResponseEntity: On success it returns BalanceResponse object with
	 *         details like accountNo and balance. On error it returns
	 *         ErrorResponse object with proper error message
	 */
	@PostMapping("/balance")
	private ResponseEntity<Object> getBalance(@RequestBody RequestData data) {
		try {
			BalanceResponse ac = atmService.getBalance(data);
			return new ResponseEntity<>(ac, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			ErrorResponse er = new ErrorResponse(e.getMessage(), AtmConstant.FAILURE_MSG);
			return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * The POST controller method to withdraw money as requested by user.
	 * 
	 * @param data
	 *            which is of type RequestData that contain {"accountNo", "pin", "amount"} 
	 *            requested by the user
	 * @return ResponseEntity: On success it returns DispenseResponse object
	 *         that mention dispensed currency/notes details and balance amount.
	 *         On error it returns ErrorResponse object with proper error
	 *         message
	 */
	@PostMapping("/dispense")
	private ResponseEntity<Object> withdraw(@RequestBody RequestData data) {
		try {
			DispenseResponse ac = atmService.withdraw(data);
			return new ResponseEntity<>(ac, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			ErrorResponse er = new ErrorResponse(e.getMessage(), AtmConstant.FAILURE_MSG);
			return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
