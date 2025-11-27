/**
 * 
 */
package com.test.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 */
@RestController
@RequestMapping("/api")
public class HelloService {
	
	 @GetMapping("/hello")
		public String testService() {
	    	
	    	return "my service invoke";
			
			
		}

}
