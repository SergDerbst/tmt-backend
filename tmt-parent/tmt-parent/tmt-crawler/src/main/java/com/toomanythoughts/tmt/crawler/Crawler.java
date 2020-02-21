package com.toomanythoughts.tmt.crawler;

import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public abstract class Crawler {

	private final URL root;
	private final Set<URL> crawled = new HashSet<>();
	private final List<URL> toCrawl = new LinkedList<>();

	protected Crawler(final URL root) {
		this.root = root;
		this.toCrawl.add(root);
	}

	protected abstract Scraper scraper();

	public void crawl() {
		while (!this.toCrawl.isEmpty()) {
			final URL currentUrl = this.nextUrl();
			this.toCrawl.addAll(this.scraper().scrape(currentUrl));
			this.crawl();
		}
		System.out.println("################## ----- I'm done crawling, BITCH!!! ------ ####################");
	}

	private URL nextUrl() {
		URL nextUrl;

		do {
			nextUrl = this.toCrawl.remove(0);
		} while (this.crawled.contains(nextUrl));
		this.crawled.add(nextUrl);
		return nextUrl;
	}

	public URL getRoot() {
		return this.root;
	}
}
