package com.toomanythoughts.tmt.web.layers.logic.model.geo;

import java.util.Map;

import com.neovisionaries.i18n.CountryCode;
import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

public class CountryNameEntry extends EpicPojo implements Map.Entry<CountryCode, String>, Comparable<CountryNameEntry> {

	private final CountryCode key;
	private String value;

	public CountryNameEntry(final CountryCode countryCode, final String name) {
		this.key = countryCode;
		this.value = name;
	}

	@Override
	public int compareTo(CountryNameEntry other) {
		final int comparison = this.value.compareTo(other.value);
		if (comparison != 0) {
			return comparison;
		}
		return this.key.compareTo(other.key);
	}

	public CountryCode getCountryCode() {
		return this.key;
	}

	public String getName() {
		return this.value;
	}

	@Override
	public CountryCode getKey() {
		return this.key;
	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public String setValue(String value) {
		return this.value = value;
	}
}
