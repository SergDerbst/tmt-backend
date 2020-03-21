package com.toomanythoughts.tmt.web.layers.controller.auth;

import javax.annotation.security.PermitAll;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.toomanythoughts.tmt.web.layers.logic.model.auth.User;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@RequestMapping(path="/register", method = RequestMethod.POST)
	@PermitAll
	public void register(final User user) {

	}
}
