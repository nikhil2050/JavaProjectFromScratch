package com.nikhil.nyx.common.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestController1 {
    
	@RequestMapping("/greeting")
	public GreetingPojo greeting(@RequestParam(name="name", defaultValue="World") String name){
		return new GreetingPojo(1, String.format("Hello %s", name));
	}
	
}
