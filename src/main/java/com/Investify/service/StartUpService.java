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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Investify.model.AddStartUp;
import com.Investify.model.InvestorInfo;
import com.Investify.model.StartUpInfo;
import com.Investify.model.StartUpRegistraion;
import com.Investify.repository.AddStartUpRepository;
import com.Investify.repository.InvestorInfoRepository;
import com.Investify.repository.StartUpRegistraionRepository;
import com.Investify.repository.StartUpRepository;
import com.bcryptPasswordEncoder.BCryptPasswordEncoderCustom;
import com.exceptionHandling.InvalidInvestmentAmountException;
import com.validationCheck.RegexPatterns;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;

//import java.util.regex.Matcher;
//import java.util.regex.Pattern;


@Service
public class StartUpService implements RegexPatterns{

	@Autowired
	StartUpRepository startUpRepository;
	
	@Autowired
	InvestorInfoRepository investorInfoRepository;
	
	@Autowired
	AddStartUpRepository addStartUpRepository;
	
	@Autowired
	StartUpRegistraionRepository startUpRegistraionRepository;
	
	
	@Autowired
	JavaMailSender sender;
	
	
	
	// ----------- Acknowledgement Mail Sender API ---------//
	
	public void SendMail(String name,String to,String startupname,String investmentAmount) throws MessagingException,IOException{
		
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        
        String subject="Your Investment Acknowledgement!";
        
        simpleMailMessage.setSubject(subject);
        
        String body="Hi "+name +", \n\nCongratulations!!! on your investment in "+startupname+"\nYour Invested Amount : "+investmentAmount+" INR"+
        		". \n\nThanks For Choosing Us, Keep Expand Your Portfolio and Diversify Your Investments!!! " 
        		+"\n\nBest regards,\nTeam InvestifyHub.in \n\n\n*** Please note that this is an automatically generated email that cannot receive replies ***";
        

        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body);
        
        sender.send(mimeMessage);

    }
	
	
	
	
	//----- startup- registration mail -----//
	
	public void startUpMail(String founderName, String to, String companyName) throws MessagingException,IOException {
		
		 SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		 simpleMailMessage.setTo(to);
		 
		 String subject = "Registration on investifyHub Successfully!!!";
		 
		 simpleMailMessage.setSubject(subject);
		 
		 String body = "Dear "+founderName+","
				 +"\n\nThank!!! you for Registration of "+companyName+" on InvestifyHub.in "
				 +"\n\nWe are delighted to welcome "+companyName +" to InvestifyHub! Your registration marks "
				 + "the beginning of an exciting journey, and we look forward to supporting your growth and success. "
				 + "\n\nShould you need any assistance, please feel free to reach out."
				 +"\n\n\nBest regards,"
				 + "\nTeam InvestifyHub.in "
			 	 +"\n\n\n*** Please note that this is an automatically generated email that cannot receive replies ***";
		 
		 MimeMessage mime = sender.createMimeMessage();
		 MimeMessageHelper mimeHelper = new MimeMessageHelper(mime);
		 mimeHelper.setTo(to);
		 mimeHelper.setSubject(subject);
		 mimeHelper.setText(body);
		 
		 sender.send(mime);
		
	}
	
	
	
	private final BCryptPasswordEncoderCustom passwordEncoder;

//    @Autowired
    public StartUpService(BCryptPasswordEncoderCustom passwordEncoder) {
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
           //String industry,
           	MultipartFile companyImage
           	){

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
        // startup.setIndustry(industry);
        
        
        
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
	
	
	//---search company keyword based-----//
	 public List<StartUpInfo> searchByCompanyName(String name) {
	        return startUpRepository.findByCompanyNameContaining(name);
	    }
	
	
//	public void getCompanyNamee(String CompanyName) {
//		startUpRepository.getByCompanyName(CompanyName);
//	}
	
	public List<StartUpInfo> getAll(){
		return startUpRepository.findAll();
		
	}
	
	
	public List<StartUpInfo> getbyName(String name){
		
		return startUpRepository.findByCompanyName(name);
	}
	
	
	
//	public List<StartUpInfo> getByIndustryName(String industry){
//		return startUpRepository.findAllByindustry(industry);
//		
//	}

	
	//-------------delete APi of invested startup -----------//
	
	 @Transactional
	    public boolean deleteStartUpByDetails(String startupname, String username, String password) {
	        Optional<InvestorInfo> investorInfoOpt = investorInfoRepository.findByUsername(username);
	        
	        if (investorInfoOpt.isPresent()) {
	            InvestorInfo investorInfo = investorInfoOpt.get();
	            
	            if (passwordEncoder.matches(password, investorInfo.getPassword())) {
	                Optional<AddStartUp> startUpOpt = addStartUpRepository.findByStartupnameAndInvestorInfo(startupname, investorInfo);
	                
	                if (startUpOpt.isPresent()) {
	                    addStartUpRepository.delete(startUpOpt.get());
	                    return true;
	                }
	            }
	            else {
	                // Throw an exception or handle the case where the password doesn't match
               	    // throw new IllegalArgumentException("Invalid password");
	            	System.out.println("Invalid password");
	            }
	        }
		 else {
	            // Throw an exception or handle the case where the investor is not found
                // throw new IllegalArgumentException("startup not found");
			    System.out.println("StartUp not found.");
	    	 }
	        return false;
	    }
	
	 
	 
	 
	 //--------------- Start-Up Registarion API ---------------------//
	 
	 // startUp registration API 
	 public StartUpRegistraion saveStartUpRegister(String founderName,String mobileNo, String email, String linkedlnUrl, String companyName,String companyUrl,MultipartFile companyPdf) {
		 
		 StartUpRegistraion startUpInfo = new StartUpRegistraion();
		 
		 startUpInfo.setFounderName(founderName);
		 startUpInfo.setEmail(email);
		 startUpInfo.setLinkedInUrl(linkedlnUrl);
		 startUpInfo.setCompanyName(companyName);
		 startUpInfo.setCompanyName(companyUrl);
		 startUpInfo.setCompanyUrl(companyUrl);
		 
		 try {
			 
			  //Mobile number validation
		      if (mobileNo.matches(MOBILE_NUMBER_PATTERN)) {
		    	  startUpInfo.setMobileNo(mobileNo);
		      }
		      
		      else {
		    	  
		    	  String getMobileNo = mobileNo; //getting mobile no. for range check. 
		    	  
		    	  if(getMobileNo.length()>12) {
		    		  //System.out.println("");
		    		  throw new IllegalArgumentException("mobile number should be in range of 12.");
		    	  }
		    	  else {
		    		  throw new IllegalArgumentException("Invalid mobile number format");
		    	  }
		      	}
		      
			  //handle for BLOB or large pdf file
		      startUpInfo.setCompanyPdf(companyPdf.getBytes());
		  } 
		 catch(IOException e) {
			 
			 e.printStackTrace();
		     throw new RuntimeException("Failed to process PDF file", e);
	 	 }
		 catch (IllegalArgumentException e) {
			 
			 e.printStackTrace();
			 throw new RuntimeException("Validation error: " + e.getMessage(), e);
	    }
		 
//		 try {
//	          // Convert the PDF file to Base64 and set it to the companyPdf field
//	          String companyPdfBase64 = Base64.getEncoder().encodeToString(companyPdf.getBytes());
//	          startUpInfo.setCompanyPdf(companyPdfBase64);
//	          
//	        } catch (IOException e) {
//	        	
//	        	e.printStackTrace();
//	        	throw new RuntimeException("Failed to process PDF file", e);
//	      }
		 
		 return startUpRegistraionRepository.save(startUpInfo);
	 }	
	 
	 
	 
	 
	   // registered startup id finding Api
	    public StartUpRegistraion findById(Long id) {
	        return startUpRegistraionRepository.findById(id).orElse(null);
	    }
	    
	    
	    public List<AddStartUp> getAllStartUp(){
	    	return addStartUpRepository.findAll();
	    	
	    	} 
	 
	 
	 
	 //--------------------------------------------------------//
	 
	 
	 
	

	  
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
	  
	  
	  
	  	//verify User
		public InvestorInfo verifyUser(String username, String password) {
		
			Optional<InvestorInfo> investorInfoOptional = investorInfoRepository.findByUsername(username);
			
			if (investorInfoOptional.isPresent()) {
			
				InvestorInfo investorInfo = investorInfoOptional.get();
			
				if (passwordEncoder.matches(password, investorInfo.getPassword())) {
					return investorInfo;
				}
			}
			return null;
		}
		
		
		//amount updated  
		public void addStartup(AddStartUp addStartUp) {
		
			String amount = addStartUp.getInvestmentAmount();
			
			// Use regular expression to check if amount contains only digits
			boolean isNumeric = amount.matches("\\d+");
			
			if (!isNumeric) {
			
				System.out.println("Investment amount contains non-digit characters.");
				
				return; // Exit early if the amount is not valid
			}
		
			// Check if the startup with the given name and investor info already exists
			if (!addStartUpRepository.existsByStartupnameAndInvestorInfo(addStartUp.getStartupname(), addStartUp.getInvestorInfo())) {
			
			addStartUpRepository.save(addStartUp);
			System.out.println("Startup added successfully.");
			
			} 
			else {
				// If the startup already exists, update the investment amount
				Optional<AddStartUp> addStartUpAmountOptional = addStartUpRepository.findByStartupnameAndInvestorInfo(addStartUp.getStartupname(), addStartUp.getInvestorInfo());
			
				if (addStartUpAmountOptional.isPresent()) {
					AddStartUp existingAddStartUp = addStartUpAmountOptional.get();
					
					double existingAmount = Double.parseDouble(existingAddStartUp.getInvestmentAmount().replace(",", ""));
					double newAmount = Double.parseDouble(addStartUp.getInvestmentAmount().replace(",", ""));
					
					double updatedAmount = existingAmount + newAmount;
					
					existingAddStartUp.setInvestmentAmount(Double.toString(updatedAmount));
					
					addStartUpRepository.save(existingAddStartUp);
					System.out.println("Amount added successfully.");
				
				} 
				else {
					System.out.println("Failed to update the amount.");
				}
			}
		}
		
		
	  

		//new startup adding 
		public void addStartupName(String startupname, String investmentAmount, InvestorInfo investorInfo) {
		
			// Validate the investment amount
			if (!investmentAmount.matches("\\d+")) {
			
				throw new InvalidInvestmentAmountException("Investment amount contains non-digit characters.");
			}
			
			// Add startup details
			if (!addStartUpRepository.existsByStartupnameAndInvestorInfo(startupname, investorInfo)) {
			
				AddStartUp addStartUp = new AddStartUp();
				
				addStartUp.setStartupname(startupname);
				addStartUp.setInvestmentAmount(investmentAmount);
				addStartUp.setInvestorInfo(investorInfo);
				
				addStartUpRepository.save(addStartUp);
				System.out.println("Startup added successfully.");
			
			} else {
			
				Optional<AddStartUp> addStartUpAmountOptional = addStartUpRepository.findByStartupnameAndInvestorInfo(startupname, investorInfo);
				
				if (addStartUpAmountOptional.isPresent()) {
				
					AddStartUp existingAddStartUp = addStartUpAmountOptional.get();
					double existingAmount = Double.parseDouble(existingAddStartUp.getInvestmentAmount().replace(",", ""));
					
					double newAmount = Double.parseDouble(investmentAmount.replace(",", ""));
					
					double updatedAmount = existingAmount + newAmount;
					
					existingAddStartUp.setInvestmentAmount(Double.toString(updatedAmount));
					
					addStartUpRepository.save(existingAddStartUp);
					System.out.println("Amount added successfully.");
				}
			}
		}
		
		
		
		
		//-------------------------old code without exception handling -----//
		
		//public void addStartupName(String startupName, String investmentAmount, InvestorInfo investorInfo) {
		//
		//// Check if the startup name exists in StartUpInfo table
		//
		//List<StartUpInfo> startupInfoList = startUpRepository.findByCompanyName(startupName);
		//
		//if (!startupInfoList.isEmpty()) {
		//
		//AddStartUp addStartUp = new AddStartUp(startupName, investmentAmount, investorInfo);
		//
		//addStartUp.setInvestorInfo(investorInfo);
		//
		//addStartup(addStartUp);
		//} 
		//else {
		//System.out.println("Invalid startup name.");
		//}
		//}
		
		

	  
	  
	 
	             //---------------Authentication API------------------//
	
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
