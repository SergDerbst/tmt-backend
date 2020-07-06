package com.toomanythoughts.tmt.web.layers.logic.geo.services;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Locale;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.LanguageCode;
import com.toomanythoughts.tmt.web.logic.geo.model.CountryMetaData;
import com.toomanythoughts.tmt.web.logic.geo.model.CountryNameEntry;
import com.toomanythoughts.tmt.web.logic.geo.services.CountryMetaDataService;

@RunWith(SpringRunner.class)
public class CountryMetaDataService_UnitTest {

	private static final Locale _English = new Locale("en", "US");
	private static final Locale _German = new Locale("de", "DE");
	private static final Locale _Rubbish = new Locale("xx", "XX");

	@TestConfiguration
	static class CountryMetaDataServiceUnitTestConfiguration {
		@Bean
		public CountryMetaDataService toTest() {
			return new CountryMetaDataService();
		}
	}

	@Autowired
	CountryMetaDataService toTest;

	@Test
	public void fetchCountryNames_WithTyped_De_EnglishLocale() {
		//when
		final Set<CountryNameEntry> countryNames = this.toTest.fetchCountryNames("De", _English);
		//then
		assertThat(countryNames, notNullValue());
		assertThat(countryNames.size(), equalTo(2));
		assertThat(((CountryNameEntry) countryNames.toArray()[0]).getKey(), equalTo(CountryCode.CD));
		assertThat(((CountryNameEntry) countryNames.toArray()[0]).getValue(), equalTo("Democratic Republic of Congo"));
		assertThat(((CountryNameEntry) countryNames.toArray()[1]).getKey(), equalTo(CountryCode.DK));
		assertThat(((CountryNameEntry) countryNames.toArray()[1]).getValue(), equalTo("Denmark"));
	}

	@Test
	public void fetchCountryNames_WithTyped_De_GermanLocale() {
		//when
		final Set<CountryNameEntry> countryNames = this.toTest.fetchCountryNames("De", _German);
		//then
		assertThat(countryNames, notNullValue());
		assertThat(countryNames.size(), equalTo(2));
		assertThat(((CountryNameEntry) countryNames.toArray()[0]).getKey(), equalTo(CountryCode.CD));
		assertThat(((CountryNameEntry) countryNames.toArray()[0]).getValue(), equalTo("Demokratische Republik Kongo"));
		assertThat(((CountryNameEntry) countryNames.toArray()[1]).getKey(), equalTo(CountryCode.DE));
		assertThat(((CountryNameEntry) countryNames.toArray()[1]).getValue(), equalTo("Deutschland"));
	}

	@Test(expected=NullPointerException.class)
	public void fetchCountryNames_WithTyped_De_RubbishLocale() {
		//when
		this.toTest.fetchCountryNames("De", _Rubbish);
	}

	@Test
	public void fetchCountryNames_WithTyped_Blank() {
		//when
		final Set<CountryNameEntry> countryNames = this.toTest.fetchCountryNames(" ", _English);
		//then
		assertThat(countryNames, notNullValue());
		assertThat(countryNames.isEmpty(), equalTo(true));
	}

	@Test
	public void fetchCountryNames_WithTyped_Empty() {
		//when
		final Set<CountryNameEntry> countryNames = this.toTest.fetchCountryNames("", _English);
		//then
		assertThat(countryNames, notNullValue());
		assertThat(countryNames.isEmpty(), equalTo(true));
	}

	@Test
	public void fetchCountryNames_WithTyped_Null() {
		//when
		final Set<CountryNameEntry> countryNames = this.toTest.fetchCountryNames(null, _English);
		//then
		assertThat(countryNames, notNullValue());
		assertThat(countryNames.isEmpty(), equalTo(true));
	}

	@Test
	public void metaData_IsBuiltWithCountryNamesIn_EnglishGerman() {
		for (final CountryCode countryCode : CountryCode.values()) {
			for (final CountryMetaData countryMetaData : this.toTest.getCountries()) {
				if (countryMetaData.getCountryCode() == countryCode) {
					assertThat(countryMetaData.getCountryNames().get(LanguageCode.en), notNullValue());
					assertThat(countryMetaData.getCountryNames().get(LanguageCode.de), notNullValue());
				}
			}
		}
	}
}
