package com.toomanythoughts.tmt.web.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toomanythoughts.tmt.web.exceptions.security.UsernameAlreadyExistsException;

@RestController
@RequestMapping("/data/validation")
public class DataValidationController {

	@Autowired
	DataValidationService dataValidationService;

	@GetMapping(path="/unique/username/{username}")
	public void uniqueUsername(@PathVariable final String username) throws UsernameAlreadyExistsException {
		this.dataValidationService.uniqueUsername(username);
	}
}
