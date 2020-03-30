package com.toomanythoughts.tmt.web.layers.controller.auth;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toomanythoughts.tmt.web.layers.logic.auth.model.authentication.AuthenticatedModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authentication.AuthenticationModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authentication.EmailValidationModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authentication.RegistrationModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.services.authentication.AuthenticationService;
import com.toomanythoughts.tmt.web.layers.logic.auth.services.authentication.EmailValidationService;
import com.toomanythoughts.tmt.web.layers.logic.auth.services.authentication.RegistrationPreparatorService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	RegistrationPreparatorService registrationPreparatorService;
	@Autowired
	EmailValidationService validationService;
	@Autowired
	AuthenticationService authenticationService;

	@PostMapping(path="/register", produces="application/json")
	public EmailValidationModel register(@RequestBody final RegistrationModel user) {
		return this.registrationPreparatorService.register(user);
	}

	@GetMapping(path="/register/validate/{userId}/{validationKey}")
	public void validate(@PathVariable final Integer userId,
											 @PathVariable final String validationKey,
											 final HttpServletResponse response) throws Exception {
		this.validationService.validate(userId, validationKey);
		response.sendRedirect("http://localhost:4200/auth/login");
	}

	@PostMapping(path="/login", produces="application/json")
	public AuthenticatedModel UserModel(@RequestBody final AuthenticationModel authenticationModel) {
		return this.authenticationService.authenticate(authenticationModel);
	}
}
