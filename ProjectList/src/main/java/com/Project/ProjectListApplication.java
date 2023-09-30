package com.Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ProjectListApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(ProjectListApplication.class, args);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
