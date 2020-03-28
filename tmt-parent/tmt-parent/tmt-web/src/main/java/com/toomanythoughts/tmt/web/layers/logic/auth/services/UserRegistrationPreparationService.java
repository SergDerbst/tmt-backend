package com.toomanythoughts.tmt.web.layers.logic.auth.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.toomanythoughts.tmt.commons.exceptions.logic.impl.DataInvalidRuntimeException;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.user.UserForEmailValidationModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.user.UserForRegistrationModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.user.UserPersonalDataModel.DayOfBirth;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.user.UserRoleModel;

/**
 * This service prepares the {@link UserForRegistrationModel} model to be registered as
 * a user. It performs necessary validity checks, handles dates, and encrypts the
 * password for storage before it passes the model on to the {@link UserRegistrationService}.
 *
 * @author Sergio Weigel
 *
 */
@Component
public class UserRegistrationPreparationService {

	@Autowired
	UserRegistrationService userService;
	@Autowired
	UserPasswordService passwordService;
	@Autowired
	RoleService roleService;

	public UserForEmailValidationModel register(final UserForRegistrationModel user) {
		this.ensureUser(user);
		return this.userService.create(user);
	}

	private void ensureUser(final UserForRegistrationModel user) {
		if (user.getId() != null) {
			throw DataInvalidRuntimeException.prepare("A new user cannot have an id.", "user.id", user.getId());
		}
		this.ensurePersonalData(user);
		this.ensureCredentials(user);
		this.ensureRoles(user);
		this.ensurePreferredLanguages(user);
	}

	private void ensurePreferredLanguages(UserForRegistrationModel user) {
		if (user.getPreferredLanguage() == null) {
			user.setPreferredLanguage("en-US");
		}
	}

	private void ensureRoles(UserForRegistrationModel user) {
		final Set<UserRoleModel> realRoles = new HashSet<>();
		if (user.getRoles() == null || user.getRoles().size() == 0) {
			realRoles.add(this.roleService.findDefaultRole());
		} else {
			for (final UserRoleModel role : user.getRoles()) {
				final UserRoleModel real = this.roleService.findByName(role.getName());
				if (real != null) {
					realRoles.add(real);
				} else {
					throw DataInvalidRuntimeException.prepare("Unkown role.", "user.roles", role.getName());
				}
			}
		}
		user.setRoles(realRoles);
	}


	private void ensureCredentials(UserForRegistrationModel user) {
		if (user.getCredentials() == null) {
			throw DataInvalidRuntimeException.prepare("A new user must have credentials.", "user.credentials", null);
		}
		this.ensureUsername(user);
		this.ensureEmail(user);
		this.ensurePassword(user);
	}

	private void ensureUsername(UserForRegistrationModel user) {
		if (user.getCredentials().getUsername() == null || user.getCredentials().getUsername().length() < 4) {
			throw DataInvalidRuntimeException.prepare("A new user must have a username with minimum length of four.", "user.credentials.username", user.getCredentials().getUsername());
		}
	}

	private void ensureEmail(UserForRegistrationModel user) {
		if (user.getCredentials().getEmail() == null) {
			throw DataInvalidRuntimeException.prepare("A new user must have an email address.", "user.credentials.email", null);
		}
		if (user.getCredentials().getEmailConfirm() == null) {
			throw DataInvalidRuntimeException.prepare("A new user must have a confirmation email address.", "user.credentials.emailConfirm", null);
		}
		if (!user.getCredentials().getEmail().equals(user.getCredentials().getEmailConfirm())) {
			throw DataInvalidRuntimeException.prepare("Email address must match the confirmation email address.", "user.credentials.email && user.credentials.emailConfirm", user.getCredentials().getEmail() + " && " + user.getCredentials().getEmailConfirm());
		}
		try {
			final InternetAddress email = new InternetAddress(user.getCredentials().getEmail());
			email.validate();
		} catch (final AddressException e) {
			throw DataInvalidRuntimeException.prepare("Email address must be a valid email address.", "user.credentials.email", user.getCredentials().getEmail(), e);
		}
		try {
			final InternetAddress emailConfirm = new InternetAddress(user.getCredentials().getEmailConfirm());
			emailConfirm.validate();
		} catch (final AddressException e) {
			throw DataInvalidRuntimeException.prepare("Confirmation email address must be a valid email address.", "user.credentials.emailConfirm", user.getCredentials().getEmailConfirm(), e);
		}
	}

	private void ensurePassword(UserForRegistrationModel user) {
		if (user.getCredentials().getPassword() == null) {
			throw DataInvalidRuntimeException.prepare("A new user must have a password", "user.credentials.password", null);
		}
		if (user.getCredentials().getPasswordConfirm() == null) {
			throw DataInvalidRuntimeException.prepare("A new user must have a confirmation password.", "user.credentials.passwordConfirm", null);
		}
		if (!user.getCredentials().getPassword().equals(user.getCredentials().getPasswordConfirm())) {
			throw DataInvalidRuntimeException.prepare("Password and confirmation password must match.", "user.credentials.password && user.credentials.passwordConfirm", user.getCredentials().getPassword() + " && " + user.getCredentials().getPasswordConfirm());
		}
		final String strongMatch = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!_-]).{8,40})";
		if (!Pattern.compile(strongMatch).matcher(user.getCredentials().getPassword()).matches()) {
			throw DataInvalidRuntimeException.prepare("Password must be between 8 and 40 characters long, have at least one digit, one special, one upper, and one lower case character.", "user.credentials.password", user.getCredentials().getPassword());
		}
		if (!Pattern.compile(strongMatch).matcher(user.getCredentials().getPasswordConfirm()).matches()) {
			throw DataInvalidRuntimeException.prepare("Confirmation password must be between 8 and 40 characters long, have at least one digit, one special, one upper, and one lower case character.", "user.credentials.password", user.getCredentials().getPasswordConfirm());
		}
		user.getCredentials().setPassword(this.passwordService.encryptPassword(user.getCredentials().getPassword()));
		user.getCredentials().setPasswordConfirm(null);
	}

	private void ensurePersonalData(final UserForRegistrationModel user) {
		if (user.getPersonalData() == null) {
			throw DataInvalidRuntimeException.prepare("A new user must have personal data", "user.personalData", null);
		}
		this.ensureTitle(user);
		this.ensureFirstName(user);
		this.ensureMiddleNames(user);
		this.ensureLastName(user);
		this.ensureDayOfBirth(user);
		this.ensureSex(user);
	}

	private void ensureTitle(final UserForRegistrationModel user) {
		if (user.getPersonalData().getTitle() == null) {
			throw DataInvalidRuntimeException.prepare("A new user must have a title.", "user.personalData.title", null);
		}
	}

	private void ensureFirstName(final UserForRegistrationModel user) {
		if (user.getPersonalData().getTitle() == null) {
			throw DataInvalidRuntimeException.prepare("A new user must have a first name.", "user.personalData.firstName", null);
		}
	}

	private void ensureMiddleNames(final UserForRegistrationModel user) {
		//all is rosy and peachy here
	}

	private void ensureLastName(final UserForRegistrationModel user) {
		if (user.getPersonalData().getTitle() == null) {
			throw DataInvalidRuntimeException.prepare("A new user must have a last name.", "user.personalData.lastName", null);
		}
	}

	private void ensureDayOfBirth(final UserForRegistrationModel user) {
		if (user.getPersonalData().getTitle() == null) {
			throw DataInvalidRuntimeException.prepare("A new user must have day of birth.", "user.personalData.dayOfBirth", null);
		}
		final DayOfBirth dayOfBirth = user.getPersonalData().getDayOfBirth();
		final LocalDate birthday = LocalDate.of(dayOfBirth.getYear(), dayOfBirth.getMonth(), dayOfBirth.getDay());
		final LocalDate today = LocalDate.now();
		if (today.isBefore(birthday)) {
			throw DataInvalidRuntimeException.prepare("A new user's day of birth must not be in the future.", "user.personalData.dayOfBirth", dayOfBirth);
		}
		final Period age = Period.between(birthday, today);
		if (age.getYears() < 14)  {
			throw DataInvalidRuntimeException.prepare("A new user must be at least 14 years old.", "user.personalData.dayOfBirth", dayOfBirth);
		}
		if (age.getYears() > 120) {
			throw DataInvalidRuntimeException.prepare("A new user cannot be older than 120 years.", "user.personalData.dayOfBirth", dayOfBirth);
		}
	}

	private void ensureSex(final UserForRegistrationModel user) {
		if (user.getPersonalData().getTitle() == null) {
			throw DataInvalidRuntimeException.prepare("A new user must have sex.", "user.personalData.sex", null);
		}
	}
}
