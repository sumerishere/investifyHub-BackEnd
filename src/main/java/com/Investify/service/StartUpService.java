package com.Investify.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Investify.model.InvestorInfo;
import com.Investify.model.StartUpInfo;
import com.Investify.repository.InvestorInfoRepository;
import com.Investify.repository.StartUpRepository;

import jakarta.transaction.Transactional;


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
	
	
	public void deleteByUsername(String username) {
		investorInfoRepository.deleteByUsername(username);
	}
	
	
	
//	public void addStartupName(String username, String password, String startupName) {
//        
//        Optional<InvestorInfo> investorInfoOptional = investorInfoRepository.findByUsernameAndPassword(username, password);
//
//        
//        if (investorInfoOptional.isPresent()) {
//            InvestorInfo investorInfo = investorInfoOptional.get();
//            
//            List<String> startupNames = investorInfo.getStartupname();
//            
//           
//            if (startupNames == null || !startupNames.contains(startupName)) {
//            	
//                // Initialize the list if null
//                if (startupNames == null) {
//                    startupNames = new ArrayList<>();
//                }
//                
//                startupNames.add(startupName);
//                
//                // Set the updated list of startup names to the InvestorInfo entity
//                investorInfo.setStartupname(startupNames);
// 
//                
//                // Save the updated InvestorInfo
//                try {
//                	
//                    investorInfoRepository.save(investorInfo);
//                    System.out.println("Startup name added successfully.");
//                }
//                catch (Exception e) {
//                    System.out.println("Failed to save InvestorInfo: " + e.getMessage());
//                }
//                
//            } else {
//             
//                System.out.println("Startup name already exists in the list.");
//            }
//        } 
//        else {
//       
//        	System.out.println("InvestorInfo not found for the given username and password.");
//        }
//        
//        System.out.println("startup saved Successfully!");
//        
//	}
	
	
	@Transactional
	public ResponseEntity<String> addStartupName(String username, String password, String startupName) {
	    Optional<InvestorInfo> investorInfoOptional = investorInfoRepository.findByUsername(username);

	    if (investorInfoOptional.isPresent()) {
	        InvestorInfo investorInfo = investorInfoOptional.get();
	        
	        // Check if provided password matches the hashed password stored in the database
	        if (passwordEncoder.matches(password, investorInfo.getPassword())) {
	            List<String> startupNames = investorInfo.getStartupname();
	            
	            if (startupNames == null || !startupNames.contains(startupName)) {
	                if (startupNames == null) {
	                    startupNames = new ArrayList<>();
	                }
	                
	                startupNames.add(startupName);
	                investorInfo.setStartupname(startupNames);
	                
	                try {
	                    investorInfoRepository.save(investorInfo);
	                    System.out.println("Startup name added successfully.");
	                    return new ResponseEntity<>("Startup name added successfully.", HttpStatus.OK);
	                } catch (Exception e) {
	                    System.out.println("Failed to save InvestorInfo: " + e.getMessage());
	                    return new ResponseEntity<>("Failed to save InvestorInfo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	                }
	            } else {
	                System.out.println("Startup name already exists in the list.");
	                return new ResponseEntity<>("Startup name already exists in the list.", HttpStatus.BAD_REQUEST);
	            }
	        } else {
	            System.out.println("Incorrect password.");
	            return new ResponseEntity<>("Incorrect password.", HttpStatus.UNAUTHORIZED);
	        }
	    } else {
	        System.out.println("InvestorInfo not found for the given username.");
	        return new ResponseEntity<>("InvestorInfo not found for the given username.", HttpStatus.NOT_FOUND);
	    }
	}
	
	 
	 
	             //---------------Authentication------------------//
	
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
