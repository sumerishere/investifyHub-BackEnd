package com.Investify.model;

import java.util.List;

public class InvestorResponse {
	
    private InvestorInfo investorInfo;
    private List<AddStartUp> startups;

    public InvestorResponse(InvestorInfo investorInfo, List<AddStartUp> startups) {
        this.investorInfo = investorInfo;
        this.startups = startups;
    }

    public InvestorInfo getInvestorInfo() {
        return investorInfo;
    }

    public List<AddStartUp> getStartups() {
        return startups;
    }

	public void setInvestorInfo(InvestorInfo investorInfo) {
		this.investorInfo = investorInfo;
	}

	public void setStartups(List<AddStartUp> startups) {
		this.startups = startups;
	}
    
    
}
