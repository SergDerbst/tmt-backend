package com.toomanythoughts.tmt.web.persistence.entities.auth;

import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.toomanythoughts.tmt.commons.annotations.ExcludeFromObjectContract;
import com.toomanythoughts.tmt.commons.layers.persistence.entities.BaseEntity;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

@Entity
@Table(name = "permissions")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class PermissionEntity extends BaseEntity {

	private static final long serialVersionUID = -9221956547678679159L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "permission_gen", sequenceName = "permission_seq")
	@Column(name =  "permission_id")
	private Integer id;

	@Column(name = "name", nullable = false, unique = true, length = 25)
	private String name;

	@Type(type = "jsonb")
	@Column(name = "configuration", columnDefinition="jsonb")
	private Map<String, Object> configuration;

	@ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
	@ExcludeFromObjectContract
	private Set<RoleEntity> roles;

	@Override
	public Integer getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<RoleEntity> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<String, Object> getConfiguration() {
		return this.configuration;
	}

	public void setConfiguration(Map<String, Object> configuration) {
		this.configuration = configuration;
	}
}
