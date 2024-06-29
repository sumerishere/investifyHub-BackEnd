//package com.SpringSecurtiy;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoderCustom;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig{
//	
//	@Autowired
//	UserDetailsService userDetails;
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    	
//        http
//            
//            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
//                .requestMatchers("/", "/home").permitAll()
//                .anyRequest().authenticated()
//            )
//            
//            .formLogin(formLogin -> formLogin
//                .loginPage("/login")
//                .permitAll()
//            )
//            
//            .logout(logout -> logout
//                .permitAll()
//            )
//            
//            .csrf(csrf -> csrf.disable()) // Disable CSRF
//        
//        	.userDetailsService(userDetails);
//        
//
//        return http.build();
//    }
//
////    @Bean
////    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
////    	
////        User.UserBuilder users = User.builder().passwordEncoder(passwordEncoder::encode);
////        
////        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
////        
////        manager.createUser(users.username("user").password("password").roles("USER").build());
////        manager.createUser(users.username("admin").password("admin").roles("ADMIN").build());
////        
////        return manager;
////    }
//
//    @Bean
//    BCryptPasswordEncoderCustom passwordEncoder() {
//        return new BCryptPasswordEncoderCustom();
//    }
//}
