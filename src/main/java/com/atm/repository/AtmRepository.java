package com.atm.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.atm.model.Account;
public interface AtmRepository extends JpaRepository<Account, Long>
{
	
		Account findByAcnoAndPin(long acno, int pin);
		
	
}
