package com.atm.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atm.model.Account;
import com.atm.model.Denomination;
public interface DenominationRepository extends JpaRepository<Denomination, Long>
{
	
	List<Denomination> findAllByOrderByDenominationDesc();
	
}
