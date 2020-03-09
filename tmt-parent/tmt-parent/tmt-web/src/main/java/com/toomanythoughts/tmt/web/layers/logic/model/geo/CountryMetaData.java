package com.toomanythoughts.tmt.web.layers.logic.model.geo;

import java.util.EnumMap;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.LanguageCode;
import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

public class CountryMetaData extends EpicPojo {
	private final CountryCode countryCode;
	private final EnumMap<LanguageCode, String> countryNames = new EnumMap<>(LanguageCode.class);

	public CountryMetaData(final CountryCode countryCode, final String... names) {
		this.countryCode = countryCode;
		this.countryNamesMap(names);
	}

	public CountryCode getCountryCode() {
		return this.countryCode;
	}

	public EnumMap<LanguageCode, String> getCountryNames() {
		return this.countryNames;
	}

	private void countryNamesMap(String[] names) {
		for (final String name : names) {
			final String[] split = name.split("\\|");
			this.countryNames.put(LanguageCode.valueOf(split[0]), this.namesString(split));
		}
	}

	private String namesString(String[] split) {
		String names = "";
		for (int i = 1; i < split.length; i++) {
			names = names + "|" + split[i];
		}
		return names;
	}
}
