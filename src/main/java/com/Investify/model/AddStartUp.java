package com.Investify.model;

import org.hibernate.annotations.Formula;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AddStartUp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String startupname;
	private String investmentAmount;
		
	@ManyToOne
	@JoinColumn(name = "investor_id")
	private InvestorInfo investorInfo;
	
	
	public AddStartUp() {}
	
	public AddStartUp(String startupname,String investmentAmount, InvestorInfo investorInfo) {
		this.startupname = startupname;
		this.investmentAmount = investmentAmount;
		this.investorInfo = investorInfo;
	}
	
	
	public String getStartupname() {
		return startupname;
	}
	public void setStartupname(String startupname) {
		this.startupname = startupname;
	}
	public String getInvestmentAmount() {
		return investmentAmount;
	}
	public void setInvestmentAmount(String investmentAmount) {
		this.investmentAmount = investmentAmount;
	}
	
	public InvestorInfo getInvestorInfo() {
        return investorInfo;
    }

    public void setInvestorInfo(InvestorInfo investorInfo) {
        this.investorInfo = investorInfo;
    }

    
	
}
