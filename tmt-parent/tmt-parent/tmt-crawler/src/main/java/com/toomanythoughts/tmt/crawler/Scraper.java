package com.toomanythoughts.tmt.crawler;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.SilentJavaScriptErrorListener;
import com.toomanythoughts.tmt.crawler.exceptions.scraper.ScrapingFailedRuntimeException;
import com.toomanythoughts.tmt.crawler.utils.SilentHtmlParserListener;
import com.toomanythoughts.tmt.crawler.utils.SilentIncorrectnessListener;
import com.toomanythoughts.tmt.crawler.utils.SilentWebConsoleLogger;

public abstract class Scraper {

	public static final int Page_Render_Timeout = 3000;

	private final URL rootUrl;

	protected Scraper(final String root) throws Exception {
		this.rootUrl = new URL(root);
	}

	protected abstract boolean isScrapable(final HtmlPage page);

	protected abstract void scrape(final HtmlPage page);

	public List<URL> scrape(URL url) {
		try {
			final HtmlPage page = this.webClient().getPage(url.toString());
			if (this.isScrapable(page)) {
				this.scrape(page);
			}
			return this.fetchUrls(page);
		} catch (final Exception e) {
			throw ScrapingFailedRuntimeException.prepare(url.toString(), e);
		}
	}

	protected List<URL> fetchUrls(HtmlPage page) {
		final List<URL> urls = new ArrayList<>();
		for (final HtmlAnchor htmlAnchor : page.getAnchors()) {
			final URL hrefUrl = this.validAnchor(htmlAnchor.getHrefAttribute());
			if (hrefUrl == null) {
				continue;
			}
			if (hrefUrl.getHost().equals(this.rootUrl.getHost())) {
				urls.add(hrefUrl);
			}
		}
		return urls;
	}

	protected URL validAnchor(final String href) {
		try {
			return new URL(href);
		} catch (final Exception e) {
			return null;
		}
	}

	protected WebClient webClient() {
		final WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getWebConsole().setLogger(new SilentWebConsoleLogger());
		webClient.setCssErrorHandler(new SilentCssErrorHandler());
		webClient.setHTMLParserListener(new SilentHtmlParserListener());
		webClient.setIncorrectnessListener(new SilentIncorrectnessListener());
		webClient.setJavaScriptErrorListener(new SilentJavaScriptErrorListener());
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.waitForBackgroundJavaScript(Scraper.Page_Render_Timeout);
		return webClient;
	}
}
