package com.Investify.service;

import java.io.IOException;
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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Investify.model.AddStartUp;
import com.Investify.model.InvestorInfo;
import com.Investify.model.StartUpInfo;
import com.Investify.repository.AddStartUpRepository;
import com.Investify.repository.InvestorInfoRepository;
import com.Investify.repository.StartUpRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;


@Service
public class StartUpService {

	@Autowired
	StartUpRepository startUpRepository;
	
	@Autowired
	InvestorInfoRepository investorInfoRepository;
	
	@Autowired
	AddStartUpRepository addStartUpRepository;
	
	
	@Autowired
	JavaMailSender sender;
	
//	public void SendMail(String to, String startName, String investmentAmount ) {
//		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setTo(to);
//        String subject = ""
//        simpleMailMessage.setSubject();
//        simpleMailMessage.setText("text");
//        sender.send(simpleMailMessage);
//	}
	
	
	// ----------- Mail Sender API ---------//
	
	public void SendMail(String name,String to,String startupname,String investmentAmount) throws MessagingException,IOException{
		
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        
        String subject="Your Investment Acknowledgement!";
        
        simpleMailMessage.setSubject(subject);
        
        String body="Hi "+name +", \n\nCongratulations!!! on your investment in "+startupname+"\nInvested Amount : "+investmentAmount+
        		". \n\nThanks For Choosing Us, Keep Expand Your Portfolio and Diversify Your Investments!!! " 
        		+"\n\nBest regards,\nTeam InvestifyHub.in \n\n\n*** Please note that this is an automatically generated email that cannot receive replies ***";
        

        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body);
        
        sender.send(mimeMessage);

    }
	
	
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
			return "image required!!";
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
	
	
	
	    //----------GET API of Investor --------//
	
	public List<InvestorInfo> getAllInvestors(){
		return investorInfoRepository.findAll();
	}
	
	
         //---------POST API of Investor----------//
	
	
	public String saveInvestor(InvestorInfo info, MultipartFile image) {
		
	    Optional<InvestorInfo> investorUsername = investorInfoRepository.findByUsername(info.getUsername());
	    Optional<InvestorInfo> investorMailId = investorInfoRepository.findByMailId(info.getMailId());

	    if (investorMailId.isPresent()) {
	        return "Already Exist Mail-Id";
	    }

	    if (investorUsername.isPresent()) {
	        return "Already Exist Username.";
	    }

	    try {
	        InvestorInfo newInfo = new InvestorInfo();
	        newInfo.setName(info.getName());
	        newInfo.setMobileNo(info.getMobileNo());
	        newInfo.setMailId(info.getMailId());
	        newInfo.setUsername(info.getUsername());
	        newInfo.setPassword(info.getPassword());

	        // Convert image to byte array
	        byte[] imageBytes = image.getBytes();
	        newInfo.setImage(imageBytes); 

	        // Encode the investor password before saving
	        String encodedPassword = passwordEncoder.encode(newInfo.getPassword());
	        newInfo.setPassword(encodedPassword);

	        investorInfoRepository.save(newInfo);
	        return "save successfully";
	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	        return "internal server error.";
	    }
	}
	
	
	//-------------------- without multipartfile -------------------------//
	
//		InvestorInfo info = new InvestorInfo();
//		info.setName(name);
//		info.setMobileNo(mobileNo);
//		info.setMailId(mailId);
//		info.setUsername(username);
//		info.setPassword(password);
//		
//		info.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
//	
//		// Encode the investor password before saving
//        String encodedPassword = passwordEncoder.encode(info.getPassword());
//        info.setPassword(encodedPassword);
//		
//		investorInfoRepository.save(info);
//		return "save succesfully";
	
//---------------------------------------------//
	
	
	
	//------Delete API of investor----//
	
	public void deleteByUsername(String username, String password) {
		
		 Optional<InvestorInfo> investorDetails = investorInfoRepository.findByUsername(username);

		 if (investorDetails.isPresent()) {
			 
	            InvestorInfo investor = investorDetails.get();
	            
	            if (passwordEncoder.matches(password, investor.getPassword())) {
	            	
	                investorInfoRepository.delete(investor);
	                
	                System.out.println("Delete Investor successfully!");
	                
	            }
	            else {
	                // Throw an exception or handle the case where the password doesn't match
	                throw new IllegalArgumentException("Invalid password");
	            }
	        }
		 else {
	            // Throw an exception or handle the case where the investor is not found
	            throw new IllegalArgumentException("Investor not found");
		 }
	}
	
	
	//-------------PUT API of Investor ---------------//
	
	  @Transactional
	  public InvestorInfo addStartupName(String startupName, String investmentAmount, String username, String password) {

	        Optional<InvestorInfo> investorInfoOptional = investorInfoRepository.findByUsername(username);

	        if (investorInfoOptional.isPresent()) {
	            InvestorInfo investorInfo = investorInfoOptional.get();

	            // Check if provided password matches the hashed password stored in the database
	            if (passwordEncoder.matches(password, investorInfo.getPassword())) {
  	
	                // Check if the startup name already exists for the investor
	                if (!addStartUpRepository.existsByStartupnameAndInvestorInfo(startupName, investorInfo)) {

	                    // Check if the startup name exists in StartUpInfo table
	                    List<StartUpInfo> startupInfoList = startUpRepository.findByCompanyName(startupName);
	                    
	                    if (!startupInfoList.isEmpty()) {
	                        AddStartUp addStartUp = new AddStartUp(startupName, investmentAmount, investorInfo);

	                        addStartUp.setInvestorInfo(investorInfo);

	                        try {
	                        	
	                            addStartUpRepository.save(addStartUp);
	                            
	                            System.out.println("Startup added successfully.");
	                        } 
	                        catch (Exception e) {
	                            System.out.println("Failed to save AddStartUp: " + e.getMessage());
	                        }
	                    }
	                    else {
	                        System.out.println("Invalid startup name.");
                    }
	                } 
	                else {
	                    System.out.println("Startup name already exists in the list.");   
	                }
	            }
	            else {
	                System.out.println("Incorrect password.");
	            }
	        }
	        else {
	            System.out.println("InvestorInfo not found for the given username.");
	        }
	        return investorInfoOptional.get();
	   }
	  
	  
	  
	  public List<AddStartUp> getAllStartUp(){
			return addStartUpRepository.findAll();
			
	  } 
	  
	  
//	  public List<AddStartUp> getAllStartupsByInvestorId(Long investorId) {
//	        return addStartUpRepository.findByInvestorInfoId(investorId);
//	    }
	  
	   
	  
	  //--------------- Login API -------------//
	  
	  public List<AddStartUp> getAllStartupsByUsernameAndPassword(String username, String password) {
	       
	        Optional<InvestorInfo> investorInfoOptional = investorInfoRepository.findByUsername(username);

	        if (investorInfoOptional.isPresent()){
	        	
	        	if(passwordEncoder.matches(password, investorInfoOptional.get().getPassword())) {
	        		return addStartUpRepository.findByInvestorInfo(investorInfoOptional.get());
	        	}
	        	else {
	        		System.out.println("Incorrect password!");
	        	}
	        }
	        else {
	        	System.out.println("Username Not Exist!! ");
	        }
	        return null; 
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
