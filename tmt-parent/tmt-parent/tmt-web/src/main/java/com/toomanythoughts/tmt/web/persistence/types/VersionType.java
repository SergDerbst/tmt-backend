package com.toomanythoughts.tmt.web.persistence.types;

import com.toomanythoughts.tmt.commons.layers.persistence.types.JsonType;
import com.toomanythoughts.tmt.web.logic.content.Version;

public class VersionType extends JsonType<Version> {

	public VersionType() {
		super(Version.class);
	}
}
