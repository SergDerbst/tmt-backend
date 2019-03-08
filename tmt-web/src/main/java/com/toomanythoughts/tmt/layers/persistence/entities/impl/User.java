package com.toomanythoughts.tmt.layers.persistence.entities.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.toomanythoughts.tmt.layers.persistence.entities.BaseEntity;
import com.toomanythoughts.tmt.layers.persistence.enums.UserRole;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;

@Entity
@Table(name = "user")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class User extends BaseEntity {

	private static final long serialVersionUID = -7568675960337363730L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "user_gen", sequenceName = "user_seq")
	@Column(name =  "user_id")
	private Integer id;

	@Column(name = "username", unique = true, nullable = false, length = 25)
	private String userName;

	@Column(name =" password", nullable = false, length = 50)
	private String password;

	@Email
	@Column(name = "email", unique = true, nullable = false, length = 255)
	private String email;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "is_active", nullable = false)
	private Boolean isActive;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	@Type(type = "pgsql_enum")
	private UserRole role;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login")
	private Date lastLogin;

	@Override
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public UserRole getRole() {
		return this.role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
}
