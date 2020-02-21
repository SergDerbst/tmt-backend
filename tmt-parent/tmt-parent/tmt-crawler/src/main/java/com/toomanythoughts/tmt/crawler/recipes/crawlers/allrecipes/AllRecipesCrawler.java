package com.toomanythoughts.tmt.crawler.recipes.crawlers.allrecipes;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.toomanythoughts.tmt.crawler.Crawler;
import com.toomanythoughts.tmt.crawler.Scraper;

@Component
public class AllRecipesCrawler extends Crawler {

	@Autowired
	AllRecipesScraper scraper;

	public AllRecipesCrawler() throws Exception {
		super(new URL(AllRecipesScraper.Root));
	}

	@Override
	protected Scraper scraper() {
		return this.scraper;
	}
}
