package com.toomanythoughts.tmt.web.layers.persistence.entities.auth;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

import org.hibernate.annotations.Type;

import com.toomanythoughts.tmt.commons.layers.persistence.entities.BaseEntity;

@Entity
@Table(name = "users")
//@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class UserEntity extends BaseEntity {

	private static final long serialVersionUID = -7568675960337363730L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "user_gen", sequenceName = "user_seq")
	@Column(name =  "user_id")
	private Integer id;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> roles;

	/*
	 * #######################
	 * ##### credentials #####
	 * #######################
	 */

	@Column(name = "username", unique = true, nullable = false, length = 25)
	private String username;

	@Column(name =" password", nullable = false, length = 50)
	private String password;

	@Email
	@Column(name = "email", unique = true, nullable = false, length = 255)
	private String email;

	/*
	 * #########################
	 * ##### personal data #####
	 * #########################
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "title", nullable = false)
	@Type(type = "com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType")
	private UserTitle title;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "middle_names", nullable = true)
	private String middleNames;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "day_of_birth")
	private Date dayOfBirth;

	@Enumerated(EnumType.STRING)
	@Column(name = "sex", nullable = false)
	@Type(type ="com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType")
	private UserSex sex;

	@Column(name = "preferred_languages", nullable = false)
	private String preferredLanguage;

	@Column(name = "is_email_validated")
	private boolean isEmailValidated;

	@Column(name = "email_validation_key")
	private String emailValidationKey;

	@Column(name = "email_validation_sent")
	private Date emailValidationSent;

	@Column(name = "is_payment_validated")
	private boolean isPaymentValidated;

	@Column(name = "is_postal_validated")
	private boolean isPostalValidated;

	@Override
	public Integer getId() {
		return this.id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Date getDayOfBirth() {
		return this.dayOfBirth;
	}

	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public UserSex getSex() {
		return this.sex;
	}

	public void setSex(UserSex sex) {
		this.sex = sex;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<RoleEntity> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	public boolean isEmailValidated() {
		return this.isEmailValidated;
	}

	public void setEmailValidated(boolean isEmailValidated) {
		this.isEmailValidated = isEmailValidated;
	}

	public boolean isPaymentValidated() {
		return this.isPaymentValidated;
	}

	public void setPaymentValidated(boolean isPaymentValidated) {
		this.isPaymentValidated = isPaymentValidated;
	}

	public boolean isPostalValidated() {
		return this.isPostalValidated;
	}

	public void setPostalValidated(boolean isPostalValidated) {
		this.isPostalValidated = isPostalValidated;
	}

	public Date getEmailValidationSent() {
		return this.emailValidationSent;
	}

	public void setEmailValidationSent(Date emailValidationSent) {
		this.emailValidationSent = emailValidationSent;
	}

	public String getPreferredLanguage() {
		return this.preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public String getEmailValidationKey() {
		return this.emailValidationKey;
	}

	public void setEmailValidationKey(String emailValidationKey) {
		this.emailValidationKey = emailValidationKey;
	}
}
