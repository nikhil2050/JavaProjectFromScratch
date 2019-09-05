package com.nikhil.nyx.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @purpose Spring boot startup file for NyxCommon 
 */ 
@SpringBootApplication
public class NyxCommonApplication {
	
	/**
	 * @purpose The purpose of the method is to deploy the NyxCommon services 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(NyxCommonApplication.class, args);
	}
}
