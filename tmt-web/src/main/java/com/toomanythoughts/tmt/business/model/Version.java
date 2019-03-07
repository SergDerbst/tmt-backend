package com.toomanythoughts.tmt.business.model;

public class Version {

	private int major;
	private int minor;
	private int patch;

	public Version() {
		this.major = 0;
		this.minor = 0;
		this.patch = 0;
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
