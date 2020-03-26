package com.toomanythoughts.tmt.web.layers.logic.auth.model;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.UserSex;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.UserTitle;

public class PersonalData extends EpicPojo {
	private UserTitle title;
	private String firstName;
	private String middleNames;
	private String lastName;
	private DayOfBirth dayOfBirth;
	private UserSex sex;

	public UserTitle getTitle() {
		return this.title;
	}

	public void setTitle(UserTitle title) {
		this.title = title;
	}

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

	public DayOfBirth getDayOfBirth() {
		return this.dayOfBirth;
	}

	public void setDayOfBirth(DayOfBirth dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public UserSex getSex() {
		return this.sex;
	}

	public void setSex(UserSex sex) {
		this.sex = sex;
	}

	public static class DayOfBirth extends EpicPojo {
		private Integer day;
		private Integer month;
		private Integer year;

		public Integer getDay() {
			return this.day;
		}

		public void setDay(Integer day) {
			this.day = day;
		}

		public Integer getMonth() {
			return this.month;
		}

		public void setMonth(Integer month) {
			this.month = month;
		}

		public Integer getYear() {
			return this.year;
		}

		public void setYear(Integer year) {
			this.year = year;
		}
	}
}
