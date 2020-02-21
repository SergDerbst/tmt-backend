package com.toomanythoughts.tmt.crawler.recipes.crawlers.allrecipes;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.toomanythoughts.tmt.crawler.Scraper;

@Component
public class AllRecipesScraper extends Scraper {

	public static final String Root = "https://www.allrecipes.com/";
	private static final String XPath_To_Section_Ingredients = "//section[contains(@class, 'recipe-ingredients')]";
	private static final String XPath_To_Section_Directions = "//section[contains(@class, 'recipe-directions')]";

	public AllRecipesScraper() throws Exception {
		super(Root);
	}

	@Override
	protected boolean isScrapable(HtmlPage page) {
		return page.getUrl().toString().contains("/recipe/");
	}

	@Override
	protected void scrape(HtmlPage page) {
		final List<Object> ingredientsSection = page.getByXPath(XPath_To_Section_Ingredients);
		final List<Object> directionsSection = page.getByXPath(XPath_To_Section_Directions);
	}
}
