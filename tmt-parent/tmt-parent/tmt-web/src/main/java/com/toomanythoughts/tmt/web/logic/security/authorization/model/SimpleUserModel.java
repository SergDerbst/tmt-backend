package com.toomanythoughts.tmt.web.logic.security.authorization.model;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

/**
 * A simplified model of a user, meant for display of other users than
 * the currently authorized user.
 *
 * @author Sergio Weigel
 *
 */
public class SimpleUserModel extends EpicPojo {

	private Integer id;
	private String username;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
