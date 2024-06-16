package com.Investify.controller;

import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Base64;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Investify.model.AddStartUp;
import com.Investify.model.AddStartupRequest;
import com.Investify.model.InvestorInfo;
import com.Investify.model.StartUpInfo;
import com.Investify.model.StartUpRegistraion;
import com.Investify.repository.AddStartUpRepository;
import com.Investify.repository.InvestorInfoRepository;
import com.Investify.repository.StartUpRepository;
import com.Investify.service.StartUpService;
import com.exceptionHandling.InvalidInvestmentAmountException;

import ch.qos.logback.core.status.Status;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;


//@WebMvcTest(StartUpController.class)
@RestController
@CrossOrigin("*")
public class StartUpController {
	
	
	@Autowired
	StartUpService startUpService;
	
	@Autowired
	StartUpRepository startUpRepository;
	
	@Autowired
	InvestorInfoRepository investorInfoRepository;
	
	@Autowired  
	AddStartUpRepository  addStartUpRepository;
	

	private final BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
    public StartUpController(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	
	@PostMapping("/saveData")
    public String saveData(
            @RequestParam("companyName") String companyName,
            @RequestParam("title") String title,
            @RequestParam("desc") String desc,
            @RequestParam("pitch") String pitch,
            @RequestParam("wti") String wti,
            @RequestParam("ceoName") String ceoName,
            @RequestParam("ctoName") String ctoName,
            @RequestParam("boardMemberName") String boardMemberName,
            @RequestParam("ceoInfo") String ceoInfo,
            @RequestParam("ctoInfo") String ctoInfo,
            @RequestParam("boardInfo") String boardInfo,
            @RequestParam("ppr") String ppr,
            @RequestParam("valuation") String valuation,
            @RequestParam("fundingGoal") String fundingGoal,
            @RequestParam("deadline") String deadline,
            @RequestParam("minInvest") String minInvest,
            @RequestParam("maxInvest") String maxInvest,
            @RequestParam("nofS") String nofS,
            @RequestParam("oft") String oft,
            @RequestParam("ast") String ast,
            @RequestParam("shareOf") String shareOf,
            @RequestParam("raised") String raised,
            @RequestParam("investor") String investor,
            @RequestParam("pitchImage") MultipartFile pitchImage,
            @RequestParam("wtiImage") MultipartFile wtiImage,
            @RequestParam("ceoImage") MultipartFile ceoImage,
            @RequestParam("ctoImage") MultipartFile ctoImage,
            @RequestParam("boardImage") MultipartFile boardImage,
            @RequestParam("ceolink") String ceoLink,
            @RequestParam("ctoLink") String ctoLink,
            @RequestParam("boardLink") String boardLink,
//            @RequestParam("industry") String industry,
            @RequestParam("cmImage") MultipartFile companyImage) {

        // Create a Startup object
       return startUpService.saveData(companyName.trim(), title, desc, pitch, wti, ceoName, ctoName, boardMemberName, ceoInfo, ctoInfo, boardInfo, ppr, valuation, fundingGoal, deadline, minInvest, maxInvest, nofS, oft, ast, shareOf, raised, investor, pitchImage, wtiImage, ceoImage, ctoImage, boardImage,ceoLink,ctoLink,boardLink,companyImage);
    }
	
	
	
	@GetMapping("/get-all")
	public List<StartUpInfo> getAll(){
		return startUpService.getAll();
	}
	
	
	@GetMapping("/getbyName")
	public List<StartUpInfo> getByName(@RequestParam("cname") String sname){
		return startUpService.getbyName(sname);
	}	
	
	
//	@GetMapping("/company-name")
//	public void getCompanyName(String companyName) {
//		 startUpService.getCompanyNamee(companyName);
//	}
	
	
	//industry category api
	
//	@GetMapping("/getByIndustry")
//	public List<StartUpInfo> getByIndustryName(@RequestParam("ind") String industry){
//		return startUpService.getByIndustryName(industry);
//	}
	
	
	
//	@DeleteMapping("/delete-startup")
//	public ResponseEntity<?> deleteStartup(@RequestParam("username") String username, @RequestParam("password") String password){
//		startUpService.deleteByUsername(username,password);
//		return ResponseEntity.ok().build();
//	}
	
	@DeleteMapping("/delete-startup")
    public ResponseEntity<String> deleteStartUp(
            @RequestParam("startupname") String startupname,
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
		
        boolean isDeleted = startUpService.deleteStartUpByDetails(startupname, username, password);
        
        if (isDeleted) {
            return ResponseEntity.ok("StartUp deleted successfully.");
        } 
        else {
            return ResponseEntity.status(403).body("Invalid credentials or StartUp not found.");
        }
    }
	
	
	
	
	 //--------------- Start-Up Registarion API ---------------------//
	 
	 @PostMapping(value = "/StartUp-Registration", consumes = {"multipart/form-data"})
	 public StartUpRegistraion startUpRegister( @RequestPart("form") StartUpRegistraion request,
	            @RequestPart("companyPdf") MultipartFile companyPdf ) throws MessagingException, IOException {
		 
		 
		 startUpService.startUpMail(request.getFounderName(), request.getEmail(),request.getCompanyName());
		 
		 return startUpService.saveStartUpRegister(
				 request.getFounderName(),
				 request.getMobileNo(), 
				 request.getEmail(), 
				 request.getLinkedInUrl(),
				 request.getCompanyName(),
				 request.getCompanyUrl(),
				 companyPdf
				 );
		 
	 }	 	 
	 
	 
	 // accessing api of stored pdf file of registered startup 
	 
	 @GetMapping("/pdf")
	    public ResponseEntity<byte[]> getPDF(@RequestParam Long id) {
	        StartUpRegistraion startup = startUpService.findById(id);

	        if (startup == null || startup.getCompanyPdf() == null) {
	            return ResponseEntity.notFound().build();
	        }

	        // Decode Base64 to byte array
	        byte[] pdfBytes = Base64.getDecoder().decode(startup.getCompanyPdf());

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_PDF);
	        headers.setContentLength(pdfBytes.length);
//	        headers.setContentDispositionFormData("attachment", "startup-pdf.pdf"); // Optionally, you can force download instead of display

	        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	    }
	 
	 
	 //--------------------------------------------------------//
	
	
	
	
								//--------------------------  Investors APIs ------------------------------//  
	
		
//	@PostMapping("/saveInvestorInfo")
//	public ResponseEntity<String> saveInvestor(@RequestBody InvestorInfo info) {
//		
//	    try {
//	        startUpService.saveInvestor(info);
//	        startUpService.signUpMail(info.getName(), info.getMailId(), info.getUsername());
//	        
//	        return ResponseEntity.ok("Investor information saved successfully.");
//	    } 
//	    catch (Exception e) {
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error!!!");
//	    }
//	}
	
	
	 @PostMapping("/saveInvestorInfo")
	    public ResponseEntity<String> saveInvestor(@RequestBody InvestorInfo info) {	
		 
	        try {
	        	startUpService.saveInvestor(info);
	            startUpService.signUpMail(info.getName(), info.getMailId(), info.getUsername());

	            return ResponseEntity.ok("Investor information saved successfully.");
	            
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	            
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error!!!");
	        }
	    }
	
	
	//----- old code with img ----//
	
//	@PostMapping("/saveInvestorInfo")
//    public String saveInvestor(@RequestPart("info") InvestorInfo info,
//                               @RequestPart("image") MultipartFile image) {
//        return startUpService.saveInvestor(info, image);
//    }
	
	
	
	@GetMapping("/get-all-investors")
	public List<InvestorInfo> getInvestorsData(){
		return startUpService.getAllInvestors();
	}
	
	@GetMapping("/get-investor")
	public Optional<InvestorInfo> getCredentials(@RequestParam("username") String username, @RequestParam("password") String password) {
		return startUpService.getCredentials(username,password);
	}
	
	
	@DeleteMapping("/delete-investor")
	public ResponseEntity<?> deleteInvestor(@RequestParam("username") String username, @RequestParam("password") String password){
		startUpService.deleteByUsername(username,password);
		return ResponseEntity.ok().build();
	}
	
	
//----------------------------------------------------------------//
	
	@PutMapping("/add-startup")
	public ResponseEntity<?> updateStartupName(@RequestBody AddStartupRequest request) {
		
	    String username = request.getUsername();
	    String password = request.getPassword();

	    // Verify user
	    InvestorInfo investorInfo = startUpService.verifyUser(username, password);
	    
	    if (investorInfo == null) {
	    	
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }

	    try {
	        // Add startup details
	        startUpService.addStartupName(request.getStartupname(), request.getInvestmentAmount(), investorInfo);

	        // Send mail
	        startUpService.SendMail(investorInfo.getName(), investorInfo.getMailId(), request.getStartupname(), request.getInvestmentAmount());

	    } catch (InvalidInvestmentAmountException ex) {
	    	
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	    } 
	    catch (Exception e) {
	    	
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }

	    return ResponseEntity.ok().build();
	}

	
	
	
	
	//--------without exception handle -----------//
	
//	@PutMapping("/add-startup")
//	public ResponseEntity<?> updateStartupName(@RequestBody AddStartupRequest request) {
//	    String username = request.getUsername();
//	    String password = request.getPassword();
//
//	    // Verify user
//	    InvestorInfo investorInfo = startUpService.verifyUser(username, password);
//	    if (investorInfo == null) {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//	    }
//
//	    // Add startup details
//	    startUpService.addStartupName(request.getStartupname(), request.getInvestmentAmount(), investorInfo);
//
//	    // Send mail
//	    try {
//	        startUpService.SendMail(investorInfo.getName(), investorInfo.getMailId(), request.getStartupname(), request.getInvestmentAmount());
//	    } catch (Exception e) {
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//	    }
//	    return ResponseEntity.ok().build();
//	}

	
	
	
//----------------------------------------------------------------//
	
//---old PUT API-------//

//	@PutMapping("/add-startup")
//    public ResponseEntity<?> updateStartupName(
//    		@RequestParam("startupname") String startupname,
//    		@RequestParam("investmentAmount") String investmentAmount,
//    		@RequestParam("username") String username, 
//    		@RequestParam("password")String password ){
//        
//        InvestorInfo info = startUpService.addStartupName(startupname, investmentAmount, username, password); 
//        System.out.println(info);
//        
//        try {
//        	
//        	startUpService.SendMail(info.getName(),info.getMailId(),startupname, investmentAmount);
//        }
//        catch(Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//        return ResponseEntity.ok().build();
//    }
	
	
	
	
	@GetMapping("/get-allStartup")
	public List<AddStartUp> getAllStartUp(){
		return startUpService.getAllStartUp();
	}
	
	
//	 @GetMapping("/id-startup")
//	    public ResponseEntity<?> getAllStartupsByInvestorId(@RequestParam("id") Long investorId) {
//		 
//	        List<AddStartUp> startups = startUpService.getAllStartupsByInvestorId(investorId);
//	        
//	        if (startups != null && !startups.isEmpty()) {
//	        	
//	            return ResponseEntity.ok(startups);
//	        } 
//	        else {
//	        	
//	            return ResponseEntity.notFound().build();
//	        }
//	    }
	
	
	//-------- Login - Status-----//
	
	 @PostMapping("/invested-startups")
	 public ResponseEntity<?> getAllStartupsByUsernameAndPassword(@RequestBody SignInRequest request) {
		 
//		 System.out.println(request.getUsername()+" "+request.getPassword());
		 
         List<AddStartUp> startups = startUpService.getAllStartupsByUsernameAndPassword(request.getUsername(), request.getPassword());
         
         if (startups != null && !startups.isEmpty()) {
        	 
        	 return ResponseEntity.ok(startups);
        	 
	     }
         else {
	            return ResponseEntity.notFound().build();
	     }
	 }
	 
	 
}



//----------------------------Deprecated code ------------//

//@PutMapping("/add-startup")
//public ResponseEntity<?> addStartupName(@RequestBody AddStartUp request) {
//  String startupName = request.getStartupName();
//  String investmentAmount = request.getInvestmentAmount();
//  String username = request.getUsername();
//  String password = request.getPassword();
//
//  // Find investor by username and validate password
//  Optional<InvestorInfo> investorInfoOptional = investorInfoRepository.findByUsername(username);
//  if (investorInfoOptional.isPresent()) {
//      InvestorInfo investorInfo = investorInfoOptional.get();
//      if (investorInfo.getPassword().equals(password)) {
//          // Check if startupName already exists for the investor
//          boolean startupExists = addStartUpRepository.existsByStartupnameAndInvestorInfo(startupName, investorInfo);
//          
//          if (!startupExists) {
//              // Create new AddStartUp entity
//              AddStartUp addStartUp = new AddStartUp(startupName, investmentAmount, investorInfo);
//              try {
//                  addStartUpRepository.save(addStartUp);
//                  return ResponseEntity.ok().build();
//              } 
//              catch (Exception e) {
//                  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//              }
//          } else {
//              return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//          }
//      } else {
//          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//      }
//  } else {
//      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//  }
//}
