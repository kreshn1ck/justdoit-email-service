package com.ubt.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class JustDoItEmailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JustDoItEmailServiceApplication.class, args);
	}

}
