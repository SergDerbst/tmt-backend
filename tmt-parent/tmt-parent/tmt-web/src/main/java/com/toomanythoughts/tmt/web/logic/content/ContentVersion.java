package com.toomanythoughts.tmt.web.logic.content;

public class ContentVersion {

	private int major;
	private int minor;
	private int patch;

	public ContentVersion() {
		this.major = 0;
		this.minor = 0;
		this.patch = 0;
	}

	public ContentVersion(final int major, final int minor, final int patch) {
		this.minor = major;
		this.minor = minor;
		this.patch = patch;
	}

	public void incrementMajor() {
		this.major = this.major + 1;
		this.minor = 0;
		this.patch = 0;
	}

	public void incrementMinor() {
		this.minor = this.minor + 1;
		this.patch = 0;
	}

	public void incrementPatch() {
		this.patch = this.patch + 1;
	}

	public int getMajor() {
		return this.major;
	}

	public int getMinor() {
		return this.minor;
	}

	public int getPatch() {
		return this.patch;
	}
}
