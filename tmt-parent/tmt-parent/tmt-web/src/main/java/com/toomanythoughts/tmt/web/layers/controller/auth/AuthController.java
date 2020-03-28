package com.toomanythoughts.tmt.web.layers.controller.auth;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toomanythoughts.tmt.web.layers.logic.auth.model.user.UserForEmailValidationModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.user.UserForRegistrationModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.user.UserModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.services.UserEmailValidationService;
import com.toomanythoughts.tmt.web.layers.logic.auth.services.UserRegistrationPreparationService;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UserRegistrationPreparationService registrationService;
	@Autowired
	UserEmailValidationService validationService;

	@PostMapping(path="/register")
	@PermitAll
	public UserForEmailValidationModel register(@RequestBody final UserForRegistrationModel user) {
		return this.registrationService.register(user);
	}

	@GetMapping(path="/register/validate/{userId}/{validationKey}")
	public UserModel validate(@PathVariable Integer userId, @PathVariable String validationKey) {
		return this.validationService.validate(userId, validationKey);
	}
}
