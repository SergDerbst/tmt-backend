package com.toomanythoughts.tmt.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TmtCrawlingSpiderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmtCrawlingSpiderApplication.class, args);
	}
}
