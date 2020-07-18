package com.toomanythoughts.tmt.web.logic.security.authorization;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.toomanythoughts.tmt.web.logic.security.SecurityConstants;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(final HttpServletRequest request,
																	final HttpServletResponse response,
																	final FilterChain chain) throws IOException, ServletException {
		final String header = request.getHeader(SecurityConstants.Header_String);

		if (header == null || !header.startsWith(SecurityConstants.Token_Prefix)) {
			chain.doFilter(request, response);
			return;
		}

		final UsernamePasswordAuthenticationToken authentication = this.getAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(final HttpServletRequest request) {
		final String token = request.getHeader(SecurityConstants.Header_String);

		if (token != null) {
			final String username = JWT.require(Algorithm.HMAC512(SecurityConstants.Secret.getBytes()))
					.build()
					.verify(token.replace(SecurityConstants.Token_Prefix, ""))
					.getSubject();



			if (username != null) {
				return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
			}
		}
		return null;
	}
}
