package com.toomanythoughts.tmt.web.logic.auth.authentication.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.toomanythoughts.tmt.commons.exceptions.logic.impl.FormDataInvalidRuntimeException;
import com.toomanythoughts.tmt.web.logic.auth.authentication.model.EmailVerificationModel;
import com.toomanythoughts.tmt.web.logic.auth.authentication.model.RegistrationModel;
import com.toomanythoughts.tmt.web.logic.auth.authorization.model.RoleModel;
import com.toomanythoughts.tmt.web.logic.auth.authorization.model.PersonalDataModel.DayOfBirth;
import com.toomanythoughts.tmt.web.logic.auth.authorization.services.RoleService;

/**
 * This service prepares the {@link RegistrationModel} model to be registered as
 * a user. It performs necessary validity checks, handles dates, and encrypts the
 * password for storage before it passes the model on to the {@link RegistrationService}.
 *
 * @author Sergio Weigel
 *
 */
@Component
public class RegistrationDataPreppingService {

	@Autowired
	RegistrationService userService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	RoleService roleService;

	public EmailVerificationModel prepAndRegister(final RegistrationModel user) {
		this.ensureUser(user);
		return this.userService.create(user);
	}

	private void ensureUser(final RegistrationModel user) {
		if (user.getId() != null) {
			throw FormDataInvalidRuntimeException.prepare("error.message.register.user.with.id", "user.id", user.getId());
		}
		this.ensurePersonalData(user);
		this.ensureCredentials(user);
		this.ensureRoles(user);
		this.ensurePreferredLanguages(user);
	}

	private void ensurePreferredLanguages(RegistrationModel user) {
		if (user.getPreferredLanguage() == null ||
				user.getPreferredLanguage().isBlank()) {
			user.setPreferredLanguage("en-US");
		}
	}

	private void ensureRoles(RegistrationModel user) {
		final Set<RoleModel> realRoles = new HashSet<>();
		if (user.getRoles() == null || user.getRoles().size() == 0) {
			realRoles.add(this.roleService.findDefaultRole());
		} else {
			for (final RoleModel role : user.getRoles()) {
				final RoleModel real = this.roleService.findByName(role.getName());
				if (real != null) {
					realRoles.add(real);
				} else {
					throw FormDataInvalidRuntimeException.prepare("error.message.register.user.role.unknown", "user.roles", role.getName());
				}
			}
		}
		user.setRoles(realRoles);
	}


	private void ensureCredentials(RegistrationModel user) {
		if (user.getCredentials() == null) {
			throw FormDataInvalidRuntimeException.prepare("error.message.register.user.without.credentials", "user.credentials", null);
		}
		this.ensureUsername(user);
		this.ensureEmail(user);
		this.ensurePassword(user);
	}

	private void ensureUsername(RegistrationModel user) {
		if (user.getCredentials().getUsername() == null) {
			throw FormDataInvalidRuntimeException.prepare("error.message.register.user.credentials.without.username", "user.credentials.username", user.getCredentials().getUsername());
		}
		if (user.getCredentials().getUsername().length() < 4) {
			throw FormDataInvalidRuntimeException.prepare("error.message.register.user.credentials.username.minLength", "user.credentials.username", user.getCredentials().getUsername());
		}
	}

	private void ensureEmail(RegistrationModel user) {
		if (user.getCredentials().getEmail() == null) {
			throw FormDataInvalidRuntimeException.prepare("error.message.register.user.credentials.without.email.address", "user.credentials.email", null);
		}
		if (user.getCredentials().getEmailConfirm() == null) {
			throw FormDataInvalidRuntimeException.prepare("error.message.register.user.credentials.without.email.address.confirmation", "user.credentials.emailConfirm", null);
		}
		if (!user.getCredentials().getEmail().equals(user.getCredentials().getEmailConfirm())) {
			throw FormDataInvalidRuntimeException.prepare("error.message.register.user.credentials.with.email.address.confirmation.not.matching", "user.credentials.email && user.credentials.emailConfirm", user.getCredentials().getEmail() + " && " + user.getCredentials().getEmailConfirm());
		}
		try {
			final InternetAddress email = new InternetAddress(user.getCredentials().getEmail());
			email.validate();
		} catch (final AddressException e) {
			throw FormDataInvalidRuntimeException.prepare("error.message.register.user.credentials.with.email.address.invalid", "user.credentials.email", user.getCredentials().getEmail(), e);
		}
	}

	private void ensurePassword(RegistrationModel user) {
		if (user.getCredentials().getPassword() == null || user.getCredentials().getPassword().isBlank()) {
			throw FormDataInvalidRuntimeException.prepare("error.message.register.user.credentials.without.password", "user.credentials.password", null);
		}
		if (!user.getCredentials().getPassword().equals(user.getCredentials().getPasswordConfirm())) {
			throw FormDataInvalidRuntimeException.prepare("error.message.register.user.credentials.with.password.confirmation.not.matching", "user.credentials.password && user.credentials.passwordConfirm", user.getCredentials().getPassword() + " && " + user.getCredentials().getPasswordConfirm());
		}
		final String strongMatch = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!_-]).{8,40})";
		if (!Pattern.compile(strongMatch).matcher(user.getCredentials().getPassword()).matches()) {
			//Password must be between 8 and 40 characters long, have at least one digit, one special, one upper, and one lower case character.
			throw FormDataInvalidRuntimeException.prepare("error.message.register.user.credentials.with.password.invalid", "user.credentials.password", user.getCredentials().getPassword());
		}
		user.getCredentials().setPassword(this.passwordEncoder.encode(user.getCredentials().getPassword()));
		user.getCredentials().setPasswordConfirm(null);
	}

	private void ensurePersonalData(final RegistrationModel user) {
		if (user.getPersonalData() == null) {
			throw FormDataInvalidRuntimeException.prepare("error.message.register.user.without.personalData", "user.personalData", null);
		}
		this.ensureTitle(user);
		this.ensureFirstName(user);
		this.ensureMiddleNames(user);
		this.ensureLastName(user);
		this.ensureDayOfBirth(user);
		this.ensureSex(user);
	}

	private void ensureTitle(final RegistrationModel user) {
		if (user.getPersonalData().getTitle() == null) {
			throw FormDataInvalidRuntimeException.prepare("error.message.register.user.personalData.without.title", "user.personalData.title", null);
		}
	}

	private void ensureFirstName(final RegistrationModel user) {
		if (user.getPersonalData().getFirstName() == null) {
			throw FormDataInvalidRuntimeException.prepare("error.message.register.user.personalData.without.firstName", "user.personalData.firstName", null);
		}
		if (user.getPersonalData().getFirstName().length() == 0) {
			throw FormDataInvalidRuntimeException.prepare("error.message.register.user.personalData.without.firstName", "user.personalData.firstName", "");
		}
	}

	private void ensureMiddleNames(final RegistrationModel user) {
		//all is rosy and peachy here
	}

	private void ensureLastName(final RegistrationModel user) {
		if (user.getPersonalData().getLastName() == null) {
			throw FormDataInvalidRuntimeException.prepare("error.message.register.user.personalData.without.lastName", "user.personalData.lastName", null);
		}
		if (user.getPersonalData().getLastName().length() == 0) {
			throw FormDataInvalidRuntimeException.prepare("error.message.register.user.personalData.without.lastName", "user.personalData.lastName", "");
		}
	}

	private void ensureDayOfBirth(final RegistrationModel user) {
		if (user.getPersonalData().getDayOfBirth() == null) {
			throw FormDataInvalidRuntimeException.prepare("error.message.register.user.personalData.without.dayOfBirth", "user.personalData.dayOfBirth", null);
		}
		final DayOfBirth dayOfBirth = user.getPersonalData().getDayOfBirth();
		final LocalDate birthday = LocalDate.of(dayOfBirth.getYear(), dayOfBirth.getMonth(), dayOfBirth.getDay());
		final LocalDate today = LocalDate.now();
		if (today.isBefore(birthday)) {
			throw FormDataInvalidRuntimeException.prepare("error.message.register.user.personalData.with.dayOfBirth.future", "user.personalData.dayOfBirth", dayOfBirth);
		}
		final Period age = Period.between(birthday, today);
		if (age.getYears() < 14)  {
			throw FormDataInvalidRuntimeException.prepare("error.message.register.user.personalData.with.dayOfBirth.tooYoung", "user.personalData.dayOfBirth", dayOfBirth);
		}
		if (age.getYears() > 120) {
			throw FormDataInvalidRuntimeException.prepare("error.message.register.user.personalData.with.dayOfBirth.tooOld", "user.personalData.dayOfBirth", dayOfBirth);
		}
	}

	private void ensureSex(final RegistrationModel user) {
		if (user.getPersonalData().getSex() == null) {
			throw FormDataInvalidRuntimeException.prepare("error.message.register.user.personalData.without.sex", "user.personalData.sex", null);
		}
	}
}
