package com.toomanythoughts.tmt.layers.persistence.types.impl;

import com.toomanythoughts.tmt.layers.logic.Version;
import com.toomanythoughts.tmt.layers.persistence.types.JsonType;

public class VersionType extends JsonType<Version> {

	public VersionType() {
		super(Version.class);
	}
}
