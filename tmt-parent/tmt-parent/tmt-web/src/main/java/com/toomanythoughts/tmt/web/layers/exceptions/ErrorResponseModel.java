package com.toomanythoughts.tmt.web.layers.exceptions;

import java.util.HashMap;
import java.util.Map;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

public class ErrorResponseModel extends EpicPojo {

	private String name;
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
}
