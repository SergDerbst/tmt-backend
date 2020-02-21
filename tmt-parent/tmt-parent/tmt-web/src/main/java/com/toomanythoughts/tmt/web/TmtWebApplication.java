package com.toomanythoughts.tmt.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TmtWebApplication {

	public static void main(String[] args)  {
		SpringApplication.run(TmtWebApplication.class, args);
	}
}
