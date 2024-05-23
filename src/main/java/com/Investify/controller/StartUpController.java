package com.Investify.controller;

import java.util.Base64;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.Investify.model.InvestorInfo;
import com.Investify.model.StartUpInfo;
import com.Investify.repository.AddStartUpRepository;
import com.Investify.repository.InvestorInfoRepository;
import com.Investify.repository.StartUpRepository;
import com.Investify.service.StartUpService;
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
            @RequestParam("industry") String industry,
            @RequestParam("cmImage") MultipartFile companyImage) {

        // Create a Startup object
       return startUpService.saveData(companyName, title, desc, pitch, wti, ceoName, ctoName, boardMemberName, ceoInfo, ctoInfo, boardInfo, ppr, valuation, fundingGoal, deadline, minInvest, maxInvest, nofS, oft, ast, shareOf, raised, investor, pitchImage, wtiImage, ceoImage, ctoImage, boardImage,ceoLink,ctoLink,boardLink,industry,companyImage);
    }
	
	
	
	@GetMapping("/get-all")
	public List<StartUpInfo> getAll(){
		return startUpService.getAll();
	}
	
	
	@GetMapping("/getbyName")
	public List<StartUpInfo> getByName(@RequestParam("cname") String sname){
		
		return startUpService.getByName(sname);
	}	
	
	@GetMapping("/getByIndustry")
	public List<StartUpInfo> getByIndustryName(@RequestParam("ind") String industry){
		return startUpService.getByIndustryName(industry);
	}
	
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
	
	//--------------  Investors APIs ---------//  
	
	
//	@PostMapping("/saveInvestorInfo")
//	public String saveInvestor(@RequestBody InvestorInfo info, @RequestPart("image") MultipartFile image) {
//		return startUpService.saveInvestor(info,image);
//	}
	
	@PostMapping("/saveInvestorInfo")
    public String saveInvestor(@RequestPart("info") InvestorInfo info,
                               @RequestPart("image") MultipartFile image) {
        return startUpService.saveInvestor(info, image);
    }
	
//	@PostMapping("/saveInvestorInfo")
//	public String saveInvestor(@ModelAttribute InvestorInfo info,
//	                           @RequestParam("image") MultipartFile image) {
//	    return startUpService.saveInvestor(info, image);
//	}
	
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
	
	
//	@PutMapping("/add-startup")
//    public ResponseEntity<?> addStartupName(@RequestBody AddStartUp request) {
//        String startupName = request.getStartupName();
//        String investmentAmount = request.getInvestmentAmount();
//        String username = request.getUsername();
//        String password = request.getPassword();
//
//        // Find investor by username and validate password
//        Optional<InvestorInfo> investorInfoOptional = investorInfoRepository.findByUsername(username);
//        if (investorInfoOptional.isPresent()) {
//            InvestorInfo investorInfo = investorInfoOptional.get();
//            if (investorInfo.getPassword().equals(password)) {
//                // Check if startupName already exists for the investor
//                boolean startupExists = addStartUpRepository.existsByStartupnameAndInvestorInfo(startupName, investorInfo);
//                
//                if (!startupExists) {
//                    // Create new AddStartUp entity
//                    AddStartUp addStartUp = new AddStartUp(startupName, investmentAmount, investorInfo);
//                    try {
//                        addStartUpRepository.save(addStartUp);
//                        return ResponseEntity.ok().build();
//                    } 
//                    catch (Exception e) {
//                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//                    }
//                } else {
//                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//                }
//            } else {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }

	@PutMapping("/add-startup")
    public ResponseEntity<?> updateStartupName(
    		@RequestParam("startupname") String startupname,
    		@RequestParam("investmentAmount") String investmentAmount,
    		@RequestParam("username") String username, 
    		@RequestParam("password")String password ){
		
        
        InvestorInfo info = startUpService.addStartupName(startupname, investmentAmount, username, password); 
        System.out.println(info);
        
        try {
        	
        	startUpService.SendMail(info.getName(),info.getMailId(),startupname, investmentAmount);
        }
        	catch(Exception e){
        	
        }
        return ResponseEntity.ok().build();
    }
	
	
	
	
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

