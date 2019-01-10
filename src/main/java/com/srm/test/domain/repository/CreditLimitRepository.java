package com.srm.test.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srm.test.domain.orm.CreditLimit;

@Repository
public interface CreditLimitRepository extends JpaRepository<CreditLimit, Long> {
 
    public CreditLimit findByName(String name);
 
}