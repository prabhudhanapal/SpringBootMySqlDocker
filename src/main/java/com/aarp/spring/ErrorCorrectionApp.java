package com.aarp.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@SpringBootApplication
public class ErrorCorrectionApp {

	
	@RequestMapping("/")
	public String home(){
		return "AARP Error Correction Application";
	}
	public static void main(String[] args) {
		SpringApplication.run(ErrorCorrectionApp.class, args);
	}
}
