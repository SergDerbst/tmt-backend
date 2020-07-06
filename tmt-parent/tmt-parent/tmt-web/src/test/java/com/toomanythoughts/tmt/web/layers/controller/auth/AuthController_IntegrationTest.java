package com.toomanythoughts.tmt.web.layers.controller.auth;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toomanythoughts.tmt.web.config.PersistenceAuditConfiguration;
import com.toomanythoughts.tmt.web.logic.auth.authentication.model.RegistrationCredentialsModel;
import com.toomanythoughts.tmt.web.logic.auth.authentication.model.RegistrationModel;
import com.toomanythoughts.tmt.web.logic.auth.authorization.model.PermissionModel;
import com.toomanythoughts.tmt.web.logic.auth.authorization.model.PersonalDataModel;
import com.toomanythoughts.tmt.web.logic.auth.authorization.model.RoleModel;
import com.toomanythoughts.tmt.web.logic.auth.authorization.model.PersonalDataModel.DayOfBirth;
import com.toomanythoughts.tmt.web.persistence.entities.auth.UserSex;
import com.toomanythoughts.tmt.web.persistence.entities.auth.UserTitle;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureEmbeddedDatabase
@FlywayTest
@Import(PersistenceAuditConfiguration.class)
public class AuthController_IntegrationTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;


	@Before
	public void setUp() {
		System.out.println("############ - Arsch Pupulu Bamm Butsch: ");
	}

	@Test
	public void thatRegisterWorksOnNewUser() throws Exception {
		//given
		final RegistrationModel registrationModel = this.registrationModel();
		//when
		this.mockMvc.perform(post("/auth/register")
				.contentType("application/json")
				.content(this.objectMapper.writeValueAsString(registrationModel)))
		//then
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	private RegistrationModel registrationModel() {
		final RegistrationModel model = new RegistrationModel();
		model.setCredentials(this.registrationCredentialsModel());
		//model.setId(666); make sure this is tested both ways, bitch!!
		model.setPersonalData(this.personalDataModel());
		model.setPreferredLanguage("en-US");
		model.setRoles(this.roles());
		return model;
	}

	private Set<RoleModel> roles() {
		final HashSet<RoleModel> set = new HashSet<>();
		set.add(this.roleModel());
		return set;
	}

	private RoleModel roleModel() {
		final RoleModel model = new RoleModel();
		model.setId(666);
		model.setName("Fettrole");
		model.setPermissions(this.permissions());
		return model;
	}

	private Set<PermissionModel> permissions() {
		final HashSet<PermissionModel> set = new HashSet<>();
		return set;
	}

	private RegistrationCredentialsModel registrationCredentialsModel() {
		final RegistrationCredentialsModel model = new RegistrationCredentialsModel();
		model.setEmail("email@server.domain");
		model.setEmailConfirm("email@server.domain");
		model.setPassword("Password_666");
		model.setPasswordConfirm("Password_666");
		model.setUsername("supernanny");
		return model;
	}

	private PersonalDataModel personalDataModel() {
		final PersonalDataModel model = new PersonalDataModel();
		model.setDayOfBirth(this.dayOfBirth());
		model.setFirstName("Nannie");
		model.setLastName("Hazel");
		model.setMiddleNames("Doss");
		model.setSex(UserSex.Female);
		model.setTitle(UserTitle.FemaleTitle);
		return model;
	}

	private DayOfBirth dayOfBirth() {
		final DayOfBirth dayOfBirth = new DayOfBirth();
		dayOfBirth.setDay(11);
		dayOfBirth.setMonth(9);
		dayOfBirth.setYear(2001);
		return dayOfBirth;
	}
}
