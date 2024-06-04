package com.Investify;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;

//this annotation useful for rapid annotation
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan({"com.example.demo", "controller", "service"})

@SpringBootApplication
public class InvetifyHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvetifyHubApplication.class, args);
	}

}
