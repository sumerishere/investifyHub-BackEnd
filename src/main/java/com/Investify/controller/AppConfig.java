package com.Investify.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bcryptPasswordEncoder.BCryptPasswordEncoderCustom;

@Configuration
public class AppConfig {

    @Bean
    BCryptPasswordEncoderCustom bCryptPasswordEncoder() {
        return new BCryptPasswordEncoderCustom();
    }
}