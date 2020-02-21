package com.toomanythoughts.tmt.web.layers.persistence.entities;

import java.util.Date;
import java.util.List;

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
import org.hibernate.annotations.TypeDef;

import com.toomanythoughts.tmt.commons.layers.persistence.entities.BaseEntity;
import com.toomanythoughts.tmt.commons.layers.persistence.enums.UserRole;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;

@Entity
@Table(name = "users")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class UserEntity extends BaseEntity {

	private static final long serialVersionUID = -7568675960337363730L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "user_gen", sequenceName = "user_seq")
	@Column(name =  "user_id")
	private Integer id;

	@Column(name = "username", unique = true, nullable = false, length = 25)
	private String username;

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

	@Column(name = "is_locked", nullable = false)
	private Boolean isLocked;

	@Column(name = "is_enabled", nullable = false)
	private Boolean isEnabled;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_role", nullable = false)
	@Type(type = "pgsql_enum")
	private UserRole role;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login")
	private Date lastLogin;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expires_at")
	private Date expiresAt;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_articles",
							joinColumns = @JoinColumn(name = "user_id"),
							inverseJoinColumns = @JoinColumn(name = "article_id"))
	private List<ArticleEntity> articles;

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

	public Boolean getIsLocked() {
		return this.isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Date getExpiresAt() {
		return this.expiresAt;
	}

	public void setExpiresAt(Date expiresAt) {
		this.expiresAt = expiresAt;
	}

	public Boolean getIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<ArticleEntity> getArticles() {
		return this.articles;
	}

	public void setArticles(List<ArticleEntity> articles) {
		this.articles = articles;
	}

}
