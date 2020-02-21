package com.toomanythoughts.tmt.commons.utils.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class CustomCorsFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		((HttpServletResponse) response).setHeader("Access-Control-Allow-Origin", "*");
		((HttpServletResponse) response).setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, PATCH");
		((HttpServletResponse) response).setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
		((HttpServletResponse) response).setHeader("Access-Control-Max-Age", "3600");

		if(RequestMethod.OPTIONS.name().equals(((HttpServletRequest) request).getMethod())) {
			((HttpServletResponse) response).setStatus(HttpServletResponse.SC_OK);
		} else {
			filterChain.doFilter(request, response);
		}
	}
}
