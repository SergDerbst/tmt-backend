package com.toomanythoughts.tmt.web.layers.logic.auth.services;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.toomanythoughts.tmt.web.logic.security.DayOfBirthService;
import com.toomanythoughts.tmt.web.logic.security.authorization.model.PersonalDataModel.DayOfBirth;

@RunWith(SpringRunner.class)
public class DayOfBirthService_UnitTest {

	@TestConfiguration
	static class DayOfBirthServiceUnitTestConfiguration {
		@Bean
		public DayOfBirthService toTest() {
			return new DayOfBirthService();
		}
	}

	@Autowired
	DayOfBirthService toTest;
	@MockBean
	DayOfBirth dayOfBirth;

	private LocalDate today;

	@Before
	public void setUp() {
		this.today = LocalDate.now();
	}

	@Test
	public void toDate() {
		//given
		when(this.dayOfBirth.getDay()).thenReturn(this.today.getDayOfMonth());
		when(this.dayOfBirth.getMonth()).thenReturn(this.today.getMonthValue());
		when(this.dayOfBirth.getYear()).thenReturn(this.today.getYear());
		//when
		final Date date = this.toTest.toDate(this.dayOfBirth);
		//then
		final LocalDate localDate = new java.sql.Date(date.getTime()).toLocalDate();
		assertThat(localDate.getDayOfMonth(), equalTo(this.today.getDayOfMonth()));
		assertThat(localDate.getMonthValue(), equalTo(this.today.getMonthValue()));
		assertThat(localDate.getYear(), equalTo(this.today.getYear()));
	}

	@Test
	public void toDayOfBirth() {
		//when
		final DayOfBirth dayOfBirth = this.toTest.toDayOfBirth(new Date());
		//then
		assertThat(dayOfBirth.getYear(), equalTo(this.today.getYear()));
		assertThat(dayOfBirth.getMonth(), equalTo(this.today.getMonthValue()));
		assertThat(dayOfBirth.getDay(), equalTo(this.today.getDayOfMonth()));
	}
}
