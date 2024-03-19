package com.Investify.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Investify.model.InvestorInfo;
import com.Investify.model.StartUpInfo;
import com.Investify.repository.InvestorInfoRepository;
import com.Investify.repository.StartUpRepository;


@Service
public class StartUpService {

	@Autowired
	StartUpRepository startUpRepository;
	
	@Autowired
	InvestorInfoRepository investorInfoRepository;
	
	
	private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public StartUpService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
	
	
	
	public String saveData(
            String companyName,
            String title,
            String desc,
            String pitch,
            String wti,
            String ceoName,
            String ctoName,
            String boardMemberName,
            String ceoInfo,
            String ctoInfo,
            String boardInfo,
            String ppr,
            String valuation,
            String fundingGoal,
            String deadline,
            String minInvest,
            String maxInvest,
            String nofS,
            String oft,
            String ast,
            String shareOf,
            String raised,
            String investor,
           	MultipartFile pitchImage,
           	MultipartFile wtiImage,
           	MultipartFile ceoImage,
           	MultipartFile ctoImage,
           	MultipartFile boardImage,
           	String ceoLink,
           	String ctoLink,
           	String boardLink,
           	String industry,
           	MultipartFile companyImage
           	) {

        // Create a Startup object
        StartUpInfo startup = new StartUpInfo();
        startup.setCompanyName(companyName);
        startup.setTitle(title);
        startup.setDesc(desc);
        startup.setPitch(pitch);
        startup.setWti(wti);
        startup.setCeoName(ceoName);
        startup.setCtoNAme(ctoName);
        startup.setBoardMemberNAme(boardMemberName);
        startup.setCeoinfo(ceoInfo);
        startup.setCtoinfo(ctoInfo);
        startup.setBoradinfo(boardInfo);
        startup.setPpr(ppr);
        startup.setValuation(valuation);
        startup.setFundinggoal(fundingGoal);
        startup.setDeadline(deadline);
        startup.setMininvest(minInvest);
        startup.setMaxInvest(maxInvest);
        startup.setNofS(nofS);
        startup.setOft(oft);
        startup.setAst(ast);
        startup.setShareof(shareOf);
        startup.setRaised(raised);
        startup.setInvestor(investor);
        startup.setCeoLink(ceoLink);
        startup.setCtoLink(ctoLink);
        startup.setBoardLink(boardLink);
        startup.setIndustry(industry);
        
        // Set non-LOB data using setters
        try {
            
            if (pitchImage != null) {
                startup.setPitchimage(Base64.getEncoder().encodeToString(pitchImage.getBytes()));
            }
            if (wtiImage != null) {
                startup.setWtiImage(Base64.getEncoder().encodeToString(wtiImage.getBytes()));
            }
            if (ceoImage != null) {
                startup.setCeoImage(Base64.getEncoder().encodeToString(ceoImage.getBytes()));
            }
            if (ctoImage != null) {
                startup.setCtoimage(Base64.getEncoder().encodeToString(ctoImage.getBytes()));
            }
            if (boardImage != null) {
                startup.setBoard(Base64.getEncoder().encodeToString(boardImage.getBytes()));
            }
            if(companyImage!= null) {
            	startup.setCompanyImage(Base64.getEncoder().encodeToString(companyImage.getBytes()));
            }
            
		} catch (Exception e) {
			// TODO: handle exception
		}
        
        startUpRepository.save(startup);
        return "succesfully data uploaded";
    }
	
	
	public List<StartUpInfo> getAll(){
		return startUpRepository.findAll();
		
	}
	
	
	public List<StartUpInfo> getByName(String name){
		
		return startUpRepository.findByCompanyName(name);
		
	}
	
	
	public List<StartUpInfo> getByIndustryName(String industry){
		return startUpRepository.findAllByindustry(industry);
		
	}
	
	
	
	
	//--------------  Investors APIs ---------//
	
	
	
	public List<InvestorInfo> getAllInvestors(){
		return investorInfoRepository.findAll();
		
	}
	

	public String saveInvestor(InvestorInfo info) {
		
		Optional<InvestorInfo> investorUsername = investorInfoRepository.findByUsername(info.getUsername());
		Optional<InvestorInfo> investorMailId = investorInfoRepository.findByMailId(info.getMailId());
		
		
		if(investorMailId.isPresent() ) {
			return "Already Exist Mail-Id";
		}
		
		if(investorUsername.isPresent() ) {
			return "Already Exist Username.";
			
		}
		
		 // Encode the investor password before saving
        String encodedPassword = passwordEncoder.encode(info.getPassword());
        info.setPassword(encodedPassword);
		
		//	if(info.getStartupname()!=null) {
		//		
		//		List<StartUpInfo> res=startUpRepository.findByCompanyName(info.getStartupname());
		//		info.setStartUpInfo(res.get(0));
		//	}
		
		investorInfoRepository.save(info);
		return "save succesfully";
	
	}
	
	
	public Optional<InvestorInfo> getCredentials(String username, String password){
		
		
	    Optional<InvestorInfo> investorUsername = investorInfoRepository.findByUsername(username);

	    if (investorUsername.isPresent()) {
	    
	        InvestorInfo investor = investorUsername.get();

	        if (passwordEncoder.matches(password, investor.getPassword())) {
	            
	            return Optional.of(investor);
	        }
	    }

	    // if investor not found or password does not match, return null
	    return Optional.empty();
		
	}
	
}
