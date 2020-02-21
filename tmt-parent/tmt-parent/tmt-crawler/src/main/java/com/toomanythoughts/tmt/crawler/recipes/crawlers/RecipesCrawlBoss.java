package com.toomanythoughts.tmt.crawler.recipes.crawlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.toomanythoughts.tmt.crawler.recipes.crawlers.allrecipes.AllRecipesCrawler;

@Component
public class RecipesCrawlBoss {

	@Autowired
	AllRecipesCrawler allRecipes;

	public void crawl() {
		this.allRecipes.crawl();
	}
}
