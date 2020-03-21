package com.toomanythoughts.tmt.web.layers.controller.geo;

import java.util.Locale;
import java.util.Set;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toomanythoughts.tmt.web.layers.logic.model.geo.CountryNameEntry;
import com.toomanythoughts.tmt.web.layers.logic.services.geo.CountryMetaDataService;

@RestController
@CrossOrigin
@RequestMapping("/geo/country")
public class CountryController {

	@Autowired
	CountryMetaDataService countryMetaDataService;

	@GetMapping(path="/names")
	@PermitAll
	public ResponseEntity<Set<CountryNameEntry>> contryNames(final String typed, final Locale lang) {
		return new ResponseEntity<>(this.countryMetaDataService.fetchCountryNames(typed, lang), HttpStatus.OK);
	}
}
