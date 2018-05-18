package com.eight.eightapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//@SpringBootApplication
public class ApiCoreApp extends WebMvcConfigurationSupport{
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
//        configurer.setUseSuffixPatternMatch(false).setUseTrailingSlashMatch(true);
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiCoreApp.class, args);
    }

}
