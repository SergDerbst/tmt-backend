package com.toomanythoughts.tmt.crawler.utils;

import java.net.URL;

import com.gargoylesoftware.htmlunit.html.HTMLParserListener;

public class SilentHtmlParserListener implements HTMLParserListener {
	@Override
	public void error(String message, URL url, String html, int line, int column, String key) {}
	@Override
	public void warning(String message, URL url, String html, int line, int column, String key) {}
}
