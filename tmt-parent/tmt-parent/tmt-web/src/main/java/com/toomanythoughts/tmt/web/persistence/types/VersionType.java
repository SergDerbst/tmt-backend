package com.toomanythoughts.tmt.web.persistence.types;

import com.toomanythoughts.tmt.commons.layers.persistence.types.JsonType;
import com.toomanythoughts.tmt.web.logic.content.ContentVersion;

public class VersionType extends JsonType<ContentVersion> {

	public VersionType() {
		super(ContentVersion.class);
	}
}
