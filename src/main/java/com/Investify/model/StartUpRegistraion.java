package com.Investify.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StartUpRegistraion {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String founderName;
	private String mobileNo;
	private String email;
	private String linkedInUrl;
	private String companyUrl;
	
	@Column(columnDefinition = "LongText")
	private String companyPdf;
	
	
	
	public StartUpRegistraion() {}
	
	public StartUpRegistraion(Long id, String founderName, String mobileNo, String email, String linkedInUrl,
			String companyUrl,String companyPdf) {
		
		this.id = id;
		this.founderName = founderName;
		this.mobileNo = mobileNo;
		this.email = email;
		this.linkedInUrl = linkedInUrl;
		this.companyUrl = companyUrl;
		this.companyPdf = companyPdf;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFounderName() {
		return founderName;
	}
	public void setFounderName(String founderName) {
		this.founderName = founderName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLinkedInUrl() {
		return linkedInUrl;
	}
	public void setLinkedInUrl(String linkedInUrl) {
		this.linkedInUrl = linkedInUrl;
	}
	public String getCompanyUrl() {
		return companyUrl;
	}
	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

	public String getCompanyPdf() {
		return companyPdf;
	}

	public void setCompanyPdf(String companyPdf) {
		this.companyPdf = companyPdf;
	}
	
	
	
	
}
