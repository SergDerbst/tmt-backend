package com.toomanythoughts.tmt.web.layers.logic.auth.services;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.toomanythoughts.tmt.web.layers.logic.auth.model.authorization.PersonalDataModel.DayOfBirth;

@Component
public class DayOfBirthService {

	public DayOfBirth toDayOfBirth(final Date date) {
		final DayOfBirth dayOfBirth = new DayOfBirth();
		final LocalDate localDate = new java.sql.Date(date.getTime()).toLocalDate();
		dayOfBirth.setDay(localDate.getDayOfMonth());
		dayOfBirth.setMonth(localDate.getMonthValue());
		dayOfBirth.setYear(localDate.getYear());
		return dayOfBirth;
	}

	public Date toDate(DayOfBirth dayOfBirth) {
		return java.sql.Date.valueOf(LocalDate.of(dayOfBirth.getYear(), dayOfBirth.getMonth(), dayOfBirth.getDay()));
	}
}
