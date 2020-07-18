package com.toomanythoughts.tmt.web.logic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.toomanythoughts.tmt.web.logic.security.authorization.model.SimpleUserModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.model.UserModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.security.UserService;
import com.toomanythoughts.tmt.web.persistence.entities.security.UserEntity;

@Component
public class CurrentUserService {

	@Autowired
	UserService userService;

	public UserEntity entity() {
		return this.userService.entityByUsername(this.getUsername());
	}

	public UserModel model() {
		return this.userService.toModel(this.userService.entityByUsername(this.getUsername()));
	}

	public SimpleUserModel simpleModel() {
		return this.userService.toSimpleModel(this.userService.entityByUsername(this.getUsername()));
	}

	private String getUsername() {
		final String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return username;
	}
}
