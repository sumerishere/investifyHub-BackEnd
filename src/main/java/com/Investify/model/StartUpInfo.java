package com.Investify.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
public class StartUpInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String companyName;
	@Column(columnDefinition = "LongText")
	private String title;
	@Column(columnDefinition = "LongText")
	private String descprition;
	@Column(columnDefinition = "LongText")
	private String pitch;
	@Column(columnDefinition = "LongText")
	private String wti;
	//About
	private String ceoName;
	private String ctoNAme;
	private String boardMemberNAme;
	@Column(columnDefinition = "LongText")
	private String ceoinfo;
	@Column(columnDefinition = "LongText")
	private String ctoinfo;
	@Column(columnDefinition = "LongText")
	private String boradinfo;
	//images
	private String ppr;
	private String valuation;
	private String fundinggoal;
	private String deadline;
	private String mininvest;
	private String maxInvest;
	private String nofS;
	private String oft;
	private String ast;
	private String shareof;
	private String raised;
	private String investor;
	@Column(columnDefinition = "LongText")
	private String pitchimage;
	@Column(columnDefinition = "LongText")
	private String wtiImage;
	@Column(columnDefinition = "LongText")
	private String ceoImage;
	@Column(columnDefinition = "LongText")
	private String ctoimage;
	@Column(columnDefinition = "LongText")
	private String board;
	@Column(columnDefinition = "LongText")
	private String companyImage;
	
	//links
	
	@Column(columnDefinition = "LongText")
	private String ceoLink;
	@Column(columnDefinition = "LongText")
	private String ctoLink;
	@Column(columnDefinition = "LongText")
	private String boardLink;
	
	//industry
	private String industry;
	
	
	
	public String getCompanyImage() {
		return companyImage;
	}
	
	public void setCompanyImage(String companyImage) {
		this.companyImage = companyImage;
	}
	
	public String getIndustry() {
		return industry;
	}
	
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	public String getCeoLink() {
		return ceoLink;
	}
	
	public void setCeoLink(String ceoLink) {
		this.ceoLink = ceoLink;
	}
	
	public String getCtoLink() {
		return ctoLink;
	}
	
	public void setCtoLink(String ctoLink) {
		this.ctoLink = ctoLink;
	}
	public String getBoardLink() {
		return boardLink;
	}
	public void setBoardLink(String boardLink) {
		this.boardLink = boardLink;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return descprition;
	}
	public void setDesc(String descprition) {
		this.descprition = descprition;
	}
	public String getPitch() {
		return pitch;
	}
	public void setPitch(String pitch) {
		this.pitch = pitch;
	}
	public String getWti() {
		return wti;
	}
	public void setWti(String wti) {
		this.wti = wti;
	}
	public String getCeoName() {
		return ceoName;
	}
	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}
	public String getCtoNAme() {
		return ctoNAme;
	}
	public void setCtoNAme(String ctoNAme) {
		this.ctoNAme = ctoNAme;
	}
	public String getBoardMemberNAme() {
		return boardMemberNAme;
	}
	public void setBoardMemberNAme(String boardMemberNAme) {
		this.boardMemberNAme = boardMemberNAme;
	}
	public String getCeoinfo() {
		return ceoinfo;
	}
	public void setCeoinfo(String ceoinfo) {
		this.ceoinfo = ceoinfo;
	}
	public String getCtoinfo() {
		return ctoinfo;
	}
	public void setCtoinfo(String ctoinfo) {
		this.ctoinfo = ctoinfo;
	}
	public String getBoradinfo() {
		return boradinfo;
	}
	public void setBoradinfo(String boradinfo) {
		this.boradinfo = boradinfo;
	}
	public String getPpr() {
		return ppr;
	}
	public void setPpr(String ppr) {
		this.ppr = ppr;
	}
	public String getValuation() {
		return valuation;
	}
	public void setValuation(String valuation) {
		this.valuation = valuation;
	}
	public String getFundinggoal() {
		return fundinggoal;
	}
	public void setFundinggoal(String fundinggoal) {
		this.fundinggoal = fundinggoal;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getMininvest() {
		return mininvest;
	}
	public void setMininvest(String mininvest) {
		this.mininvest = mininvest;
	}
	public String getMaxInvest() {
		return maxInvest;
	}
	public void setMaxInvest(String maxInvest) {
		this.maxInvest = maxInvest;
	}
	public String getNofS() {
		return nofS;
	}
	public void setNofS(String nofS) {
		this.nofS = nofS;
	}
	public String getOft() {
		return oft;
	}
	public void setOft(String oft) {
		this.oft = oft;
	}
	public String getAst() {
		return ast;
	}
	public void setAst(String ast) {
		this.ast = ast;
	}
	public String getShareof() {
		return shareof;
	}
	public void setShareof(String shareof) {
		this.shareof = shareof;
	}
	public String getRaised() {
		return raised;
	}
	public void setRaised(String raised) {
		this.raised = raised;
	}
	public String getInvestor() {
		return investor;
	}
	public void setInvestor(String investor) {
		this.investor = investor;
	}
	public String getPitchimage() {
		return pitchimage;
	}
	public void setPitchimage(String pitchimage) {
		this.pitchimage = pitchimage;
	}
	public String getWtiImage() {
		return wtiImage;
	}
	public void setWtiImage(String wtiImage) {
		this.wtiImage = wtiImage;
	}
	public String getCeoImage() {
		return ceoImage;
	}
	public void setCeoImage(String ceoImage) {
		this.ceoImage = ceoImage;
	}
	public String getCtoimage() {
		return ctoimage;
	}
	public void setCtoimage(String ctoimage) {
		this.ctoimage = ctoimage;
	}
	public String getBoard() {
		return board;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	public StartUpInfo(Long id, String companyName, String title, String descprition, String pitch, String wti, String ceoName,
			String ctoNAme, String boardMemberNAme, String ceoinfo, String ctoinfo, String boradinfo, String ppr,
			String valuation, String fundinggoal, String deadline, String mininvest, String maxInvest, String nofS,
			String oft, String ast, String shareof, String raised, String investor, String pitchimage, String wtiImage,
			String ceoImage, String ctoimage, String board,String ceoLink,String ctoLink,String boardLink,String industry,String companyImage) {
		this.id = id;
		this.companyName = companyName;
		this.title = title;
		this.descprition = descprition;
		this.pitch = pitch;
		this.wti = wti;
		this.ceoName = ceoName;
		this.ctoNAme = ctoNAme;
		this.boardMemberNAme = boardMemberNAme;
		this.ceoinfo = ceoinfo;
		this.ctoinfo = ctoinfo;
		this.boradinfo = boradinfo;
		this.ppr = ppr;
		this.valuation = valuation;
		this.fundinggoal = fundinggoal;
		this.deadline = deadline;
		this.mininvest = mininvest;
		this.maxInvest = maxInvest;
		this.nofS = nofS;
		this.oft = oft;
		this.ast = ast;
		this.shareof = shareof;
		this.raised = raised;
		this.investor = investor;
		this.pitchimage = pitchimage;
		this.wtiImage = wtiImage;
		this.ceoImage = ceoImage;
		this.ctoimage = ctoimage;
		this.board = board;
		this.ceoLink=ceoLink;
		this.ctoLink=ctoLink;
		this.boardLink=boardLink;
		this.industry=industry;
		this.companyImage=companyImage;
	}
	
	
	public StartUpInfo() {
		
	}
	
	
}
