package com.Investify.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Investify.model.AddStartUp;
import com.Investify.model.AddStartupRequest;
import com.Investify.model.InvestorInfo;
import com.Investify.repository.AddStartUpRepository;
import com.Investify.repository.InvestorInfoRepository;
import com.Investify.service.InvestorService;
import com.bcryptPasswordEncoder.BCryptPasswordEncoderCustom;
import com.exceptionHandling.InvalidInvestmentAmountException;

@RestController
@CrossOrigin("*")
public class InvestorController {
	
	
	@Autowired
	InvestorService investorService;
	
	@Autowired
	InvestorInfoRepository investorInfoRepository;
	
	@Autowired  
	AddStartUpRepository  addStartUpRepository;
	
	

	
	
	@PostMapping("/saveInvestorInfo")
    public ResponseEntity<String> saveInvestor(@RequestBody InvestorInfo info) {	
	 
        try {
        	investorService.saveInvestor(info);
        	investorService.signUpMail(info.getName(), info.getMailId(), info.getUsername());

            return ResponseEntity.ok("Investor information saved successfully.");
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error!!!");
        }
    }


	//----- old code with img ----//
	
	//@PostMapping("/saveInvestorInfo")
	//public String saveInvestor(@RequestPart("info") InvestorInfo info,
	//                           @RequestPart("image") MultipartFile image) {
	//    return startUpService.saveInvestor(info, image);
	//}

	
	


	@GetMapping("/get-all-investors")
	public List<InvestorInfo> getInvestorsData(){
		return investorService.getAllInvestors();
	}
	
	//----search investor by name api -----//
	@GetMapping("/investor-search")
	 public List<InvestorInfo> searchInvestor(@RequestParam("name") String name) {
	    return investorService.searchByCInvestorName(name);
	 }
	
	
	@GetMapping("/get-investor")
	public Optional<InvestorInfo> getCredentials(@RequestParam("username") String username, @RequestParam("password") String password) {
		return investorService.getCredentials(username,password);
	}
	
	
	@DeleteMapping("/delete-investor-id/{id}")
	public ResponseEntity<?> deleteInvestor(@PathVariable Long id) {
		investorService.deleteInvestor(id);
		return ResponseEntity.ok().build();
	}
	
	
	
	@DeleteMapping("/delete-investor")
	public ResponseEntity<?> deleteInvestor(@RequestParam("username") String username, @RequestParam("password") String password){
		investorService.deleteByUsername(username,password);
		return ResponseEntity.ok().build();
	}
	
	
	//----------------------------------------------------------------//
	
	
	
	
	//----------------------------Deprecated code ------------//
	
	//@PutMapping("/add-startup")
	//public ResponseEntity<?> addStartupName(@RequestBody AddStartUp request) {
	//String startupName = request.getStartupName();
	//String investmentAmount = request.getInvestmentAmount();
	//String username = request.getUsername();
	//String password = request.getPassword();
	//
	//// Find investor by username and validate password
	//Optional<InvestorInfo> investorInfoOptional = investorInfoRepository.findByUsername(username);
	//if (investorInfoOptional.isPresent()) {
	//  InvestorInfo investorInfo = investorInfoOptional.get();
	//  if (investorInfo.getPassword().equals(password)) {
	//      // Check if startupName already exists for the investor
	//      boolean startupExists = addStartUpRepository.existsByStartupnameAndInvestorInfo(startupName, investorInfo);
	//      
	//      if (!startupExists) {
	//          // Create new AddStartUp entity
	//          AddStartUp addStartUp = new AddStartUp(startupName, investmentAmount, investorInfo);
	//          try {
	//              addStartUpRepository.save(addStartUp);
	//              return ResponseEntity.ok().build();
	//          } 
	//          catch (Exception e) {
	//              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	//          }
	//      } else {
	//          return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	//      }
	//  } else {
	//      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	//  }
	//} else {
	//  return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	//}
	//}

	
}
