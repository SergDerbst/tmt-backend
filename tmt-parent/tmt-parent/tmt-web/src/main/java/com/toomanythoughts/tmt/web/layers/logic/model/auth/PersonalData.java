package com.toomanythoughts.tmt.web.layers.logic.model.auth;

import org.joda.time.DateTime;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;
import com.toomanythoughts.tmt.web.layers.logic.model.auth.enums.Sex;

public class PersonalData extends EpicPojo {
	private String firstName;
	private String middleNames;
	private String lastName;
	private DateTime dayOfBirth;
	private Sex sex;

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleNames() {
		return this.middleNames;
	}

	public void setMiddleNames(String middleNames) {
		this.middleNames = middleNames;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public DateTime getDayOfBirth() {
		return this.dayOfBirth;
	}

	public void setDayOfBirth(DateTime dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public Sex getSex() {
		return this.sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}
}
