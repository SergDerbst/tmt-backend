package com.toomanythoughts.tmt.web.layers.controller.auth;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toomanythoughts.tmt.web.layers.logic.auth.model.UserForEmailValidationModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.UserForRegistrationModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.services.UserRegistrationPreparationService;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UserRegistrationPreparationService registrationService;

	@PostMapping(path="/register")
	@PermitAll
	public UserForEmailValidationModel register(@RequestBody final UserForRegistrationModel user) {
		return this.registrationService.register(user);
	}
}
