package com.toomanythoughts.tmt.web.layers.controller.auth;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.toomanythoughts.tmt.web.layers.exceptions.auth.EmailAlreadyExistsException;
import com.toomanythoughts.tmt.web.layers.exceptions.auth.UsernameAlreadyExistsException;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authentication.AuthenticatedModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authentication.AuthenticationModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authentication.EmailValidationModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authentication.EmailVerificationModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authentication.RegistrationModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authentication.UsernameValidationModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.services.authentication.AuthenticationService;
import com.toomanythoughts.tmt.web.layers.logic.auth.services.authentication.EmailVerificationService;
import com.toomanythoughts.tmt.web.layers.logic.auth.services.authentication.RegistrationDataPreppingService;
import com.toomanythoughts.tmt.web.layers.logic.auth.services.authentication.RegistrationDataValidationService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	EmailVerificationService emailVerificationService;
	@Autowired
	RegistrationDataPreppingService registrationDataPreparationService;
	@Autowired
	RegistrationDataValidationService registrationDataValidationService;

	@PostMapping(path="/register", produces="application/json")
	public EmailVerificationModel register(@RequestBody final RegistrationModel user) {
		return this.registrationDataPreparationService.prep(user);
	}

	@PostMapping(path="/register/email/validate")
	public void validateEmail(@RequestBody final EmailValidationModel toValidate) throws EmailAlreadyExistsException {
		this.registrationDataValidationService.validateEmail(toValidate.getEmail());
	}

	@PostMapping(path="/register/username/validate")
	public void validateUsername(@RequestBody final UsernameValidationModel toValidate) throws UsernameAlreadyExistsException {
		this.registrationDataValidationService.validateUsername(toValidate.getUsername());
	}

	@GetMapping(path="/register/email/verify/{userId}/{validationKey}")
	public void verifyEmail(@PathVariable final Integer userId,
																							 @PathVariable final String validationKey,
																							 @RequestParam final List<String> send,
																							 @RequestParam final String verified,
																							 final HttpServletResponse response) throws Exception {
		this.emailVerificationService.verifyEmail(userId, validationKey);
		response.sendRedirect(this.redirectUrl(send, verified));
	}

	@PostMapping(path="/login", produces="application/json")
	public AuthenticatedModel UserModel(@RequestBody final AuthenticationModel authenticationModel) {
		return this.authenticationService.authenticate(authenticationModel);
	}

	private String redirectUrl(List<String> send, String verified) {
		final StringBuilder s = new StringBuilder("http://localhost:4200");
		for (final String element : send) {
			s.append("/");
			s.append(element);
		}
		s.append("?verified=");
		s.append(verified);
		return s.toString();
	}
}
