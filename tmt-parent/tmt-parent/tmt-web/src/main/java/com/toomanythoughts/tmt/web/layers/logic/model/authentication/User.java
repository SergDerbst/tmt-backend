package com.toomanythoughts.tmt.web.layers.logic.model.authentication;

import org.joda.time.DateTime;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;
import com.toomanythoughts.tmt.commons.layers.persistence.enums.UserRole;

public class User extends EpicPojo {

	private Integer userId;
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private Boolean isActive;
	private Boolean isLocked;
	private Boolean isEnabled;
	private UserRole role;
	private DateTime lastLogin;
	private DateTime expiresAt;

	public String getPassword() {
		return this.password;
	}

	public String getUsername() {
		return this.username;
	}

	public boolean isAccountNonExpired() {
		return new DateTime(System.currentTimeMillis()).isAfter(this.expiresAt.getMillis());
	}

	public boolean isAccountNonLocked() {
		return !this.isLocked;
	}

	public boolean isEnabled() {
		return this.isEnabled;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsLocked() {
		return this.isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Boolean getIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public UserRole getRole() {
		return this.role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public DateTime getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(DateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public DateTime getExpiresAt() {
		return this.expiresAt;
	}

	public void setExpiresAt(DateTime expiresAt) {
		this.expiresAt = expiresAt;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
