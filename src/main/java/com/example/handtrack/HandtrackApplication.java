package com.example.handtrack;

import java.awt.GraphicsEnvironment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HandtrackApplication {

	public static void main(String[] args) {
		boolean isHeadless = GraphicsEnvironment.isHeadless();
		System.out.println("Running in headless mode: " + isHeadless);

		SpringApplication.run(HandtrackApplication.class, args);
	}

}
