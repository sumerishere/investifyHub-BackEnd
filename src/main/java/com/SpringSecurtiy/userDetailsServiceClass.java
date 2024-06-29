//package com.SpringSecurtiy;
//import com.Investify.model.InvestorInfo;
//import com.Investify.repository.InvestorInfoRepository;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.User.UserBuilder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class userDetailsServiceClass implements UserDetailsService{
//	
//	
//	
//	@Autowired
//	InvestorInfoRepository investorInfoRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		
//		Optional<InvestorInfo> user = investorInfoRepository.findByUsername(username);
//		
//		if(user.isPresent()) {
//			
//			
//			InvestorInfo invetorinfo = user.get();
//			
//			return User.builder()
//	
//			.username(invetorinfo.getUsername())
//			.password(invetorinfo.getPassword())
//			.roles("USER")
//			.build();
//			
//		}else {
//			
//			throw new UsernameNotFoundException("user not found with username "+username);
//		}
//	}
//
//}
