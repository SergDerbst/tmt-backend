package com.toomanythoughts.tmt.crawler.utils;

import com.gargoylesoftware.htmlunit.IncorrectnessListener;

public class SilentIncorrectnessListener implements IncorrectnessListener {

	@Override
	public void notify(String message, Object origin) {}
}
