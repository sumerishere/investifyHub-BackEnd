package com.Investify.model;


//DTO class
public class AddStartupRequest {
	
	
    private String startupname;
    private String investmentAmount;
    private String username;
    private String password;
    
    
    public AddStartupRequest() {}
    
	public AddStartupRequest(String startupname, String investmentAmount, String username, String password) {
		
		this.startupname = startupname;
		this.investmentAmount = investmentAmount;
		this.username = username;
		this.password = password;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    // Getters and setters
    
    
}

