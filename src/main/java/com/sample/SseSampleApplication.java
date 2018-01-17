package com.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 
 * @author fmoriguchi
 *
 */
@EnableScheduling
@SpringBootApplication
public class SseSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SseSampleApplication.class, args);
	}
}
