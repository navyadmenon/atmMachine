package com.atm.service;



import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.atm.model.Denomination;
import com.atm.repository.DenominationRepository;





@RunWith(SpringRunner.class)
@SpringBootTest
public class DenominationServiceTest {
	
	@Mock
	DenominationRepository denomRepo;
	
	@InjectMocks
	DenominationServiceImpl denominationService;
	
	
	AtmServiceImpl atmService ;
	
	@Before
	  public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
	    
	  }

	@Test
	public void testTotalAmountAtm() {
		when(denomRepo.findAll()).thenReturn(getAllDenom());
		BigDecimal total = denominationService.totalAmountInAtm();
		assertEquals(1500,total.intValue());
	}
	
	@Test
	public void getAllDenomDescTest() {
		when(denomRepo.findAllByOrderByDenominationDesc()).thenReturn(getAllDenom());
		List<Denomination> denomList = denominationService.getAllDenominationDesc();
		assertEquals(50,denomList.get(0).getDenomination());
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
