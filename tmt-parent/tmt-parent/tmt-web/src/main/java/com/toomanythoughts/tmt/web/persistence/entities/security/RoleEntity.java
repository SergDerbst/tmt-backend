package com.toomanythoughts.tmt.web.persistence.entities.security;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.toomanythoughts.tmt.commons.annotations.ExcludeFromObjectContract;
import com.toomanythoughts.tmt.commons.layers.persistence.entities.BaseEntity;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "role_gen", sequenceName = "role_seq")
	@Column(name =  "role_id")
	private Integer id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@ManyToMany(mappedBy="roles", fetch = FetchType.LAZY)
	@ExcludeFromObjectContract
	private Set<UserEntity> users;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "roles_permissions",
			joinColumns = @JoinColumn(name = "role_id"),
			inverseJoinColumns = @JoinColumn(name = "permission_id"))
	private Set<PermissionEntity> permissions;

	@Override
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserEntity> getUsers() {
		return this.users;
	}

	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}

	public Set<PermissionEntity> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(Set<PermissionEntity> permissions) {
		this.permissions = permissions;
	}
}
