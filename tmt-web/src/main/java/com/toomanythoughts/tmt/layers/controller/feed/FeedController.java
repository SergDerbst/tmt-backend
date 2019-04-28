package com.toomanythoughts.tmt.layers.controller.feed;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.toomanythoughts.tmt.layers.logic.model.article.Article;
import com.toomanythoughts.tmt.layers.logic.services.FeedService;

@RestController
@RequestMapping("/feed")
public class FeedController {

	@Autowired
	FeedService feedService;

	@RequestMapping(method = RequestMethod.GET)
	@PermitAll
	public ResponseEntity<List<Article>> feed() {
		return new ResponseEntity<>(new ArrayList<Article>(), HttpStatus.OK);
	}
}
