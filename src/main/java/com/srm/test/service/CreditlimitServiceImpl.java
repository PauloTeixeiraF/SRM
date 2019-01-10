package com.srm.test.service;

import java.math.BigDecimal;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srm.test.domain.orm.CreditLimit;
import com.srm.test.domain.repository.CreditLimitRepository;
import com.srm.test.enums.RiskEnum;
import com.srm.test.util.MathUtils;

@Service("creditLimitService")
@Transactional
public class CreditlimitServiceImpl implements CreditLimitService {

	@Autowired
	private CreditLimitRepository creditLimitRepository;
	
	@Autowired
	private MathUtils mathUtils;
	
	@Override
	public void createCreditLimit(CreditLimit entity) {
		CreditLimit dup  = this.findByName(entity.getName());
		if(dup == null) {
			int interestRate = 0;
			switch (entity.getRisk()) {
				case "B":	interestRate = RiskEnum.B.getInterestRate();
							break;
				case "C":	interestRate = RiskEnum.C.getInterestRate();
							break;
				default :	interestRate = RiskEnum.A.getInterestRate();
							break;
			}
			entity.setInterestRate(interestRate);
			BigDecimal limit = entity.getCreditLimit();
			entity.setTotal(limit.add(this.calculateInterestRate(entity)));
			creditLimitRepository.save(entity);
			creditLimitRepository.flush();
		}
	}

	@Override
	public CreditLimit findByName(String name) {
		return creditLimitRepository.findByName(name);
	}

	@Override
	public CreditLimit findById(Long id) {
        return creditLimitRepository.getOne(id);
    }
 
	@Override
    public void updateCreditLimit(CreditLimit entity){
    	createCreditLimit(entity);
    }
 
	@Override
    public void deleteCreditLimitById(Long id){
    	creditLimitRepository.deleteById(id);
    }
 
	@Override
    public void deleteAllCreditLimits(){
    	creditLimitRepository.deleteAll();
    }
 
	@Override
    public List<CreditLimit> findAllCreditLimits(){
        return creditLimitRepository.findAll();
    }
 
	@Override
    public boolean isCreditLimitExist(CreditLimit entity) {
        return findByName(entity.getName()) != null;
    }
    
	private BigDecimal calculateInterestRate(CreditLimit entity) {
		BigDecimal value = entity.getCreditLimit();
		BigDecimal rate = new BigDecimal(entity.getInterestRate());
		return mathUtils.calculateInterestRate(value, rate);
	}

}
