package com.toomanythoughts.tmt.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ServletComponentScan
@SpringBootApplication
@EnableJpaAuditing
public class TMTWebApplication {

	public static void main(String[] args)  {
		SpringApplication.run(TMTWebApplication.class, args);
	}
}
