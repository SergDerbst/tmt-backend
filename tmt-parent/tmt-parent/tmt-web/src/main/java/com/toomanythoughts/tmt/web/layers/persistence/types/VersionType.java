package com.toomanythoughts.tmt.web.layers.persistence.types;

import com.toomanythoughts.tmt.commons.layers.persistence.types.JsonType;
import com.toomanythoughts.tmt.web.layers.logic.Version;

public class VersionType extends JsonType<Version> {

	public VersionType() {
		super(Version.class);
	}
}
