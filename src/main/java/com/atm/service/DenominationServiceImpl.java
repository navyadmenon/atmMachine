package com.atm.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atm.enumeration.DenominationEnum;
import com.atm.model.Denomination;
import com.atm.repository.DenominationRepository;

@Service
public class DenominationServiceImpl implements DenominationService{
	
	@Autowired
	DenominationRepository denoRepo;
	

	@Override
	public BigDecimal totalAmountInAtm() {
		try {
			BigDecimal total = new BigDecimal(0);
			List<Denomination> denoList = denoRepo.findAll();
			for(Denomination deno : denoList) {
				
				total = total.add(new BigDecimal(deno.getDenomination() * deno.getQuantity()));
			}
			return total;
			
		} catch(Exception ex) {
			throw ex;
		}
		
		
	}
	
	
	@Override
	public List<Denomination> getAllDenominationDesc(){
		List<Denomination> denoList = denoRepo.findAllByOrderByDenominationDesc();
		return denoList;
	}
	
	@Override
	public Denomination saveOrUpdate(Denomination denomination) {
		return denoRepo.save(denomination);
	}
}
