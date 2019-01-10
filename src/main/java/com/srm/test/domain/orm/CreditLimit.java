package com.srm.test.domain.orm;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "credit_limit")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CreditLimit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
 
	@Size(min = 5, max = 40, message = "{user.name.invalid}")
    @NotEmpty(message = "Por favor informe o nome")
	@Column(name="NAME", nullable=false)
	private String name;

	@NotNull(message = "Por favor informe o limite de crédito")
	@Column(name="CREDIT_LIMIT")
	private BigDecimal creditLimit;
	
	@NotEmpty(message = "Por favor informe o risco")
	@Column(name="RISK", nullable=false)
	private String risk;
	
	@NotNull(message = "Por favor informe a taxa de juros")
	@Column(name="INTEREST_RATE")
	private int interestRate;
	
	@Column(name="TOTAL")
	private BigDecimal total;
	
	
	/**
	 * 
	 */
	public CreditLimit() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param creditLimit
	 * @param risk
	 * @param interestRate
	 * @param total
	 */
	public CreditLimit(
			@Size(min = 5, max = 40, message = "{user.name.invalid}") @NotEmpty(message = "Por favor informe o nome") String name,
			@NotNull(message = "Por favor informe o limite de crédito") BigDecimal creditLimit,
			@NotEmpty(message = "Por favor informe o risco") String risk,
			@NotNull(message = "Por favor informe a taxa de juros") int interestRate, BigDecimal total) {
		this.name = name;
		this.creditLimit = creditLimit;
		this.risk = risk;
		this.interestRate = interestRate;
		this.total = total;
	}

	@Override
	public String toString() {
		return "CreditLimit [id=" + id + ", name=" + name + ", creditLimit=" + creditLimit + ", risk=" + risk
				+ ", interestRate=" + interestRate + ", total=" + total + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public int getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(int interestRate) {
		this.interestRate = interestRate;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditLimit other = (CreditLimit) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

		 
}
