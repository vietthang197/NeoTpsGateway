package com.neo.gw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NeovasapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeovasapiApplication.class, args);
	}

}
