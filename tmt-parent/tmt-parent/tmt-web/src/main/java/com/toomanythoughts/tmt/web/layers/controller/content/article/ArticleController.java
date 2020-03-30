package com.toomanythoughts.tmt.web.layers.controller.content.article;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content/article")
public class ArticleController {

	@GetMapping("/write")
	public void write() {

	}
}
