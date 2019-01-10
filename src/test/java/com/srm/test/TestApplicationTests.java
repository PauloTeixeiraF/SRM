package com.srm.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.srm.test.domain.orm.CreditLimit;
import com.srm.test.domain.repository.CreditLimitRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TestApplicationTests {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private CreditLimitRepository creditLimitRepository;

	@Test
	public void whenFindByName_thenReturnCreditLimit() {
		
		//given
		CreditLimit entity = new CreditLimit("Paulo", new BigDecimal("1500"), "B", 10, new BigDecimal("150"));
		entityManager.persist(entity);
		entityManager.flush();
		
		//when
		CreditLimit found = creditLimitRepository.findByName(entity.getName());
		
		//then
		assertThat(found.getName()).isEqualTo(entity.getName());
		
	}

}

