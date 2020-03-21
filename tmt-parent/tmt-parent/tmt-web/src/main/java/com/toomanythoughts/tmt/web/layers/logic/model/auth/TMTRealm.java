package com.toomanythoughts.tmt.web.layers.logic.model.auth;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.realm.Realm;

public class TMTRealm implements Realm {

	private static final String Too_Many_Thoughts = "TooManyThoughts";

	@Override
	public String getName() {
		return Too_Many_Thoughts;
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return false;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		return null;
	}
}
