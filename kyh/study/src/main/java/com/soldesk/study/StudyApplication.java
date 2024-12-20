package com.soldesk.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudyApplication {

	public static void main(String[] args) {
		System.out.println("Goodbye 윤석열!");
		SpringApplication.run(StudyApplication.class, args);
	}

}
