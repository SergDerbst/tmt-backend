package com.toomanythoughts.tmt.web.logic.content.transcript;

import java.util.List;
import java.util.Locale;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;
import com.toomanythoughts.tmt.web.logic.content.ContentModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.model.SimpleUserModel;

public abstract class TranslationModel<Content extends ContentModel> extends EpicPojo {

	private Integer id;
	private Content content;
	private Locale language;
	private Transcript transcript;
	private SimpleUserModel owner;
	private List<SimpleUserModel> contributors;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Content getContent() {
		return this.content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public Locale getLanguage() {
		return this.language;
	}

	public void setLanguage(Locale language) {
		this.language = language;
	}

	public Transcript getTranscript() {
		return this.transcript;
	}

	public void setTranscript(Transcript transcript) {
		this.transcript = transcript;
	}

	public SimpleUserModel getOwner() {
		return this.owner;
	}

	public void setOwner(SimpleUserModel owner) {
		this.owner = owner;
	}

	public List<SimpleUserModel> getContributors() {
		return this.contributors;
	}

	public void setContributors(List<SimpleUserModel> contributors) {
		this.contributors = contributors;
	}
}
