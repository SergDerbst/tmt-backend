package com.toomanythoughts.tmt.crawler.utils;

import com.gargoylesoftware.htmlunit.WebConsole;

public class SilentWebConsoleLogger implements WebConsole.Logger {
	@Override
	public boolean isTraceEnabled() {
		return false;
	}
	@Override
	public void trace(Object message) {
	}
	@Override
	public boolean isDebugEnabled() {
		return false;
	}
	@Override
	public void debug(Object message) {
	}
	@Override
	public boolean isInfoEnabled() {
		return false;
	}
	@Override
	public void info(Object message) {
	}
	@Override
	public boolean isWarnEnabled() {
		return false;
	}
	@Override
	public void warn(Object message) {
	}
	@Override
	public boolean isErrorEnabled() {
		return false;
	}
	@Override
	public void error(Object message) {
	}
}
