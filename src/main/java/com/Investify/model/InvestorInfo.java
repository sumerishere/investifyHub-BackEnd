package com.Investify.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class InvestorInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String mobileNo;
	private String mailId;
	
	private String username;
	private String password;
		
	
	@OneToMany(mappedBy = "investorInfo", cascade = CascadeType.ALL)
    private List<AddStartUp> startUps = new ArrayList<>();
	

	public InvestorInfo() {}   
	
	public InvestorInfo(Long id, String name, String mobileNo, String mailId, 
		 String username, String password ) {  
		this.id = id;
		this.name = name;
		this.mobileNo = mobileNo;
		this.mailId = mailId;
		this.username = username;
		this.password = password;
		
	}


	public Long getInvestorId() {
		return id;
	}

	public void setInvestorId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
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
	
	
}
