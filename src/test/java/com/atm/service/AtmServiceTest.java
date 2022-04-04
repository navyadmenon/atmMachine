package com.atm.service;



import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.atm.exception.AtmException;
import com.atm.model.Account;
import com.atm.model.BalanceResponse;
import com.atm.model.Denomination;
import com.atm.model.DispenseResponse;
import com.atm.model.RequestData;
import com.atm.repository.AtmRepository;
import com.atm.util.AtmConstant;





@RunWith(SpringRunner.class)
@SpringBootTest
public class AtmServiceTest {
	
	@Mock
	AtmRepository atmRepo;
	
	@Mock
	DenominationService denominationService;
	
	@InjectMocks
	AtmServiceImpl atmService ;
	
	@Before
	  public void setUp() {
		//atmRepo = Mockito.mock(AtmRepository.class);
		MockitoAnnotations.initMocks(this);
		
	    
	  }

	/*
	 * Test the method of balance retrieve- success scenario
	 */
	@Test
	public void testGetBalance() {
		try {
				
		
			when(atmRepo.findByAcnoAndPin(Mockito.anyLong(), Mockito.anyInt())).thenReturn(getAccount());
			BalanceResponse s=atmService.getBalance(getData());
			assertNotNull(s);
			assertEquals(s.getBalance(),new BigDecimal(800));
			//
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Test for Validation failure on invalid pin
	 */
	@Test
	public void testGetBalanceInvalidPin() throws Exception{
	
			RequestData data = getData();
			
			when(atmRepo.findByAcnoAndPin(Mockito.anyLong(), Mockito.anyInt())).thenReturn(null);
						
			 assertThatThrownBy(() -> atmService.getBalance(data))
			 .isInstanceOf(AtmException.class)
			 .hasMessage(AtmConstant.INVALID_PIN);
			// bal=;
		
		
	}
	
	/*
	 * Test for withdrawal method with invalid amount requested
	 */
	@Test
	public void testWithdraw_invalidAmt() {
		when(atmRepo.findByAcnoAndPin(Mockito.anyLong(), Mockito.anyInt())).thenReturn(getAccount());
		when(denominationService.totalAmountInAtm()).thenReturn(new BigDecimal(1500));
		
		RequestData data = getData();
		data.setAmount(234);
		 assertThatThrownBy(() -> atmService.withdraw(data))
		 .isInstanceOf(AtmException.class)
		 .hasMessage(AtmConstant.INVALID_AMOUNT);

	}
	
	/*
	 * Test for validation check on insufficient balance in user account
	 */
	@Test
	public void testWithdraw_insufficientBal() {
		Account ac = getAccount();
		ac.setBalance(new BigDecimal(150));
		when(atmRepo.findByAcnoAndPin(Mockito.anyLong(), Mockito.anyInt())).thenReturn(ac);
		when(denominationService.totalAmountInAtm()).thenReturn(new BigDecimal(1500));
		
		RequestData data = getData();
		data.setAmount(450);
		 assertThatThrownBy(() -> atmService.withdraw(data))
		 .isInstanceOf(AtmException.class)
		 .hasMessage(AtmConstant.INSUFFICIENT_BALANCE);

	}
	
	/*
	 * Testing validation check on insufficient amount in ATM
	 */
	@Test
	public void testWithdraw_insufficientAmtinAtm() {
		when(atmRepo.findByAcnoAndPin(Mockito.anyLong(), Mockito.anyInt())).thenReturn(getAccount());
		when(denominationService.totalAmountInAtm()).thenReturn(new BigDecimal(400));
		
		RequestData data = getData();
		data.setAmount(450);
		 assertThatThrownBy(() -> atmService.withdraw(data))
		 .isInstanceOf(AtmException.class)
		 .hasMessage(AtmConstant.INSUFFICIENT_AMT);

	}
	
	/*
	 * Test dispensing - success scenario
	 */
	@Test
	public void testWithdraw_success() {
		when(atmRepo.findByAcnoAndPin(Mockito.anyLong(), Mockito.anyInt())).thenReturn(getAccount());
		when(denominationService.totalAmountInAtm()).thenReturn(new BigDecimal(1500));
		when(denominationService.getAllDenominationDesc()).thenReturn(getAllDenom());
		when(denominationService.saveOrUpdate(Mockito.any(Denomination.class))).thenReturn(new Denomination());
		when(atmRepo.save(Mockito.any(Account.class))).thenReturn(new Account());
		DispenseResponse response = new DispenseResponse();
		try {
			 response = atmService.withdraw(getData());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		assertEquals(new BigDecimal(400),response.getBalance() );
	}
	
	
	
	
	private RequestData getData() {
		RequestData data = new RequestData();
		data.setAccountNo(123456789);
		data.setPin(1234);
		data.setAmount(400);
		return data;
		
	}
	
	private Account getAccount() {
		Account ac = new Account();
		ac.setBalance(new BigDecimal(800));
		ac.setOverdraft(new BigDecimal(200));
		return ac;
		
	}
	
	private List<Denomination> getAllDenom(){
		
		List<Denomination> denomList = new ArrayList<Denomination>();
		Denomination denom1 = new Denomination();
		denom1.setDenomination(50);
		denom1.setQuantity(10);
		denomList.add(denom1);
		
		Denomination denom2 = new Denomination();
		denom2.setDenomination(20);
		denom2.setQuantity(30);
		denomList.add(denom2);
		
		Denomination denom3 = new Denomination();
		denom3.setDenomination(10);
		denom3.setQuantity(30);
		denomList.add(denom3);
		
		Denomination denom4 = new Denomination();
		denom4.setDenomination(5);
		denom4.setQuantity(20);
		denomList.add(denom4);
		
		return denomList;
	}

}
