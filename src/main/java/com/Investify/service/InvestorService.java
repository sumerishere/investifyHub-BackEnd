package com.Investify.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.Investify.model.AddStartUp;
import com.Investify.model.InvestorInfo;
import com.Investify.repository.AddStartUpRepository;
import com.Investify.repository.InvestorInfoRepository;
import com.Investify.repository.StartUpRegistraionRepository;
import com.Investify.repository.StartUpRepository;
import com.bcryptPasswordEncoder.BCryptPasswordEncoderCustom;
import com.exceptionHandling.InvalidInvestmentAmountException;
import com.validationCheck.RegexPatterns;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class InvestorService implements RegexPatterns{
		 
		
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
	
		
	private final BCryptPasswordEncoderCustom passwordEncoder;

	
    public InvestorService(BCryptPasswordEncoderCustom passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	
		//--------------Sign-up mail sender ---------//
	
		public void signUpMail(String name, String to, String username) throws MessagingException,IOException {
			
			 SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			 simpleMailMessage.setTo(to);
			 
			 String subject = "Successfully!!! Sign-Up";
			 
			 simpleMailMessage.setSubject(subject);
			 
			 String body = "Hi "+name +","
			 		+"\n\nThank!!! you for signing up on InvestifyHub.in "
					+"\nYour Username : "+username+"."
			 		+"\n\nAs an investor on our platform, you gain access to a diverse "
			 		+"portfolio of high-growth opportunities curated by industry experts."
			 		+"\n\nOur advanced analytics and personalized investment strategies ensure your money is working efficiently towards your financial goals. "
			 		+"\n\nJoin us and take the next step in securing a "
			 		+"prosperous financial future with InvestifyHub.in"
			 		+"\nHappy :)"+"  "+"investing!!! "
			 		+"\n\nBest regards,\nTeam InvestifyHub.in "
			 		+"\n\n\n*** Please note that this is an automatically generated email that cannot receive replies ***";
			 
			 MimeMessage mime = sender.createMimeMessage();
			 MimeMessageHelper mimeHelper = new MimeMessageHelper(mime);
			 mimeHelper.setTo(to);
			 mimeHelper.setSubject(subject);
			 mimeHelper.setText(body);
			 
			 sender.send(mime);
			 
		}
		
	    
		
	 							//  ------------------  investor APIs -------------------------//



	
	 
	//----------GET API of Investor --------//
	
	public List<InvestorInfo> getAllInvestors(){
		return investorInfoRepository.findAll();
	}
	
	//---search investor api----//
	
	public List<InvestorInfo> searchByCInvestorName(String name){
		return investorInfoRepository.findByInvestorNameContaining(name);
	}
	
	
	//------------Delete investor base on ID --------//
	
	public void deleteInvestor(Long id) {
		investorInfoRepository.deleteById(id);
	}
	
	
	//---------POST API of Investor----------//
	
	
	//public String saveInvestor(InvestorInfo info, MultipartFile image) {
	
	//
	//Optional<InvestorInfo> investorUsername = investorInfoRepository.findByUsername(info.getUsername());
	//Optional<InvestorInfo> investorMailId = investorInfoRepository.findByMailId(info.getMailId());
	//
	//if (investorMailId.isPresent()) {
	//return "Already Exist Mail-Id";
	//}
	//
	//if (investorUsername.isPresent()) {
	//return "Already Exist Username.";
	//}
	//
	//try {
	//InvestorInfo newInfo = new InvestorInfo();
	//newInfo.setName(info.getName());
	//newInfo.setMobileNo(info.getMobileNo());
	//newInfo.setMailId(info.getMailId());
	//newInfo.setUsername(info.getUsername());
	//newInfo.setPassword(info.getPassword());
	//
	//// Convert image to byte array
	//byte[] imageBytes = image.getBytes();
	//newInfo.setImage(imageBytes); 
	//
	//// Encode the investor password before saving
	//String encodedPassword = passwordEncoder.encode(newInfo.getPassword());
	//newInfo.setPassword(encodedPassword);
	//
	//investorInfoRepository.save(newInfo);
	//return "save successfully";
	//} 
	//catch (IOException e) {
	//e.printStackTrace();
	//return "internal server error.";
	//}
	//}
	//
	
	//-------------------- without image Sign-Up -------------------------//
	
	public InvestorInfo saveInvestor(InvestorInfo info) {
	
		String mobilNoChecker = info.getMobileNo();
		
		// Check if the mobile number contains only digits
		if (!mobilNoChecker.matches(MOBILE_NUMBER_PATTERN)) {
			System.out.println("Mobile number contains invalid characters. Only digits are allowed.");
			throw new IllegalArgumentException("Mobile number contains invalid characters. Only digits are allowed.");
		}
		
		// Validate the email format with lowercase letters only
		//String emailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$";
		String email = info.getMailId();
		
		
		if (!email.matches(EMAIL_PATTERN)) {
			System.out.println("Invalid email format. Email must contain only lowercase letters and proper format..");
			throw new IllegalArgumentException("Invalid email format. Email must contain only lowercase letters and proper format.");
		}
		
		// Check if the username is already taken
		if (investorInfoRepository.findByUsername(info.getUsername()).isPresent()) {
			System.out.println("Username is already taken. Please choose another username.");
			throw new IllegalArgumentException("Username is already taken. Please choose another username.");
		
		}
		
		String userName = info.getUsername();
		char firstChar = userName.charAt(0);
		
		// Check if the first character is an uppercase letter
		if (!Character.isUpperCase(firstChar)|| (Character.isDigit(firstChar))) {
			System.out.println("Username must start with a capital letter.");
			throw new IllegalArgumentException("Username must start with a capital letter.");
		}
		
		// Validate the password
		String password = info.getPassword();
		//String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		
		if (!password.matches(PASSWORD_PATTERN)) {
			System.out.println("Password must be at least 8 characters ...");
			throw new IllegalArgumentException("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
		}
		
		// Encode the investor password before saving
		String encodedPassword = passwordEncoder.encode(info.getPassword());
		info.setPassword(encodedPassword);
		
		return investorInfoRepository.save(info);
	}
	
	
	
	
	
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
	
	//------------------------------------------------------------------//
	
	
	
	
	
	
	
	//------------------------------------------------------------------//
	
	
	
	//-------------  deprecated OLD PUT API of Investor ---------------//
	
	//public InvestorInfo addStartupName(String startupName, String investmentAmount, String username, String password) {
	//
	//Optional<InvestorInfo> investorInfoOptional = investorInfoRepository.findByUsername(username);
	//
	//if (investorInfoOptional.isPresent()) {
	//InvestorInfo investorInfo = investorInfoOptional.get();
	//
	//// Check if provided password matches the hashed password stored in the database
	//if (passwordEncoder.matches(password, investorInfo.getPassword())) {
	//
	//// Check if the startup name exists in StartUpInfo table
	//List<StartUpInfo> startupInfoList = startUpRepository.findByCompanyName(startupName);
	//
	//if (!startupInfoList.isEmpty()) {
	//
	//AddStartUp addStartUp = new AddStartUp(startupName, investmentAmount, investorInfo);
	//
	//addStartUp.setInvestorInfo(investorInfo);
	//
	//String amount = addStartUp.getInvestmentAmount();
	//
	//boolean flag = true; // non-digit checker flag
	//
	//for (int i = 0; i < amount.length(); i++) {
	//
	//char ch = amount.charAt(i);
	//
	//if (!Character.isDigit(ch)) {
	//	flag = false;
	//	break;
	//}
	//}
	//
	//try {
	//
	//if (!addStartUpRepository.existsByStartupnameAndInvestorInfo(startupName, investorInfo)) {
	//
	//	if (flag) {
	//
	//		addStartUpRepository.save(addStartUp);
	//		System.out.println("Startup added successfully.");
	//		System.out.println("input is fine");
	//	} else {
	//		System.out.println("letter or symbol is present");
	//	}
	//} 
	//else {
	//
	//	if (flag) {
	//
	//		Optional<AddStartUp> addStartUpAmountOptional = addStartUpRepository
	//				.findByStartupnameAndInvestorInfo(startupName, investorInfo);
	//
	//		if (addStartUpAmountOptional.isPresent()) {
	//
	//			AddStartUp addStartUpAmount = addStartUpAmountOptional.get();
	//
	//			// Parse the existing investment amount and the new investment amount
	//			double existingAmount = Double.parseDouble(addStartUpAmount.getInvestmentAmount().replace(",", ""));
	//			
	//			// Parse the new investment amount and replace "," .
	//			double newAmount = Double.parseDouble(investmentAmount.replace(",", ""));
	//
	//			// Add the new amount to the existing amount
	//			double updatedAmount = existingAmount + newAmount;
	//
	//			// Set the updated amount as the new investment amount
	//			addStartUpAmount.setInvestmentAmount(Double.toString(updatedAmount));
	//
	//			// Save the updated entity
	//			addStartUpRepository.save(addStartUpAmount);
	//			
	//			System.out.println("Amount added Successfully !!!");	
	//		}
	//		else {
	//			
	//			System.out.println("amount adding failed!!!!");
	//		}
	//
	//	} 
	//	else {
	//		System.out.println("letter or symbol is present");
	//	}
	//}
	//}
	//catch (Exception e) {
	//System.out.println("Failed to save AddStartUp: " + e.getMessage());
	//}
	//} 
	//else {
	//System.out.println("Invalid startup name.");
	//}
	//}
	//else {
	//System.out.println("Incorrect password.");
	//}
	//} 
	//else {
	//System.out.println("InvestorInfo not found for the given username.");
	//}
	//return investorInfoOptional.get(); // for getting mail from investorInfo object.
	//}
	
	
	
	
	
	
	//public List<AddStartUp> getAllStartupsByInvestorId(Long investorId) {
	//return addStartUpRepository.findByInvestorInfoId(investorId);
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
