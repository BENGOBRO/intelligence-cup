package ru.bengobro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class UserInfoProcessingApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserInfoProcessingApplication.class, args);
	}

}
