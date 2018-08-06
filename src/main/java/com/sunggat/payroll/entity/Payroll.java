package com.sunggat.payroll.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="payroll")
public class Payroll extends BaseEntity {

	@Column(name="annual_salary")
	public BigDecimal annualSalary;
	
	@Column(name="final_salary")
	public BigDecimal finalSalary;
	
	@Column(name="currency")
	public String currency;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;
	
	public Payroll() {
		
	}

	public Payroll(BigDecimal annualSalary, BigDecimal finalSalary, String currency, User user) {
		this.annualSalary = annualSalary;
		this.finalSalary = finalSalary;
		this.currency = currency;
		this.user = user;
	}

	public BigDecimal getAnnualSalary() {
		return annualSalary;
	}

	public void setAnnualSalary(BigDecimal annualSalary) {
		this.annualSalary = annualSalary;
	}

	public BigDecimal getFinalSalary() {
		return finalSalary;
	}

	public void setFinalSalary(BigDecimal finalSalary) {
		this.finalSalary = finalSalary;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
