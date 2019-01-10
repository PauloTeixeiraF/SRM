package com.srm.test.service;

import java.util.List;
import com.srm.test.domain.orm.CreditLimit;

public interface CreditLimitService {
	
	void createCreditLimit(CreditLimit entity);
	
	CreditLimit findByName(String name);
	
	CreditLimit findById(Long id);
	
	List<CreditLimit> findAllCreditLimits();
 
    void updateCreditLimit(CreditLimit entity);
 
    void deleteCreditLimitById(Long id);
 
    void deleteAllCreditLimits();
 
    boolean isCreditLimitExist(CreditLimit entity);
	
}
