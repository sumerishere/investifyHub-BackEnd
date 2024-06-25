package com.Investify.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoderCustom;

@Configuration
public class AppConfig {

    @Bean
    public BCryptPasswordEncoderCustom bCryptPasswordEncoder() {
        return new BCryptPasswordEncoderCustom();
    }
}