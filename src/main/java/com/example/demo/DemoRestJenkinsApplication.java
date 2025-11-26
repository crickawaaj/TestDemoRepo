package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@SpringBootApplication
@EnableWebMvc
@EnableAutoConfiguration
@ComponentScan("com.test.rest")
public class DemoRestJenkinsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoRestJenkinsApplication.class, args);
	}

}
