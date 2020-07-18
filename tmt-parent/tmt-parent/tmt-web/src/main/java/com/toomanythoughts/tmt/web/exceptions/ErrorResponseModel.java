package com.toomanythoughts.tmt.web.exceptions;

import java.util.HashMap;
import java.util.Map;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

public class ErrorResponseModel extends EpicPojo {

	private String name;
	private String message;
	private final Map<String, Object> properties = new HashMap<>();

	public String getName() {
		return this.name;
	}

	public void setName(String error) {
		this.name = error;
	}

	public Map<String, Object> getProperties() {
		return this.properties;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
