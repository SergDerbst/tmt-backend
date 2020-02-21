package com.toomanythoughts.tmt.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.toomanythoughts.tmt.crawler.recipes.crawlers.RecipesCrawlBoss;

@Component
public class CrawlBoss {

	@Autowired
	RecipesCrawlBoss recipes;

	@EventListener
	public void crawl(final ContextRefreshedEvent event) {
		this.recipes.crawl();
	}
}
