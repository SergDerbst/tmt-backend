package com.toomanythoughts.tmt.web.logic.content.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toomanythoughts.tmt.commons.exceptions.logic.impl.FormDataInvalidException;
import com.toomanythoughts.tmt.web.exceptions.content.ContentNotFoundException;
import com.toomanythoughts.tmt.web.logic.content.video.model.VideoCreationModel;
import com.toomanythoughts.tmt.web.logic.content.video.model.VideoModel;

@Controller
@RequestMapping("/content/video")
public class VideoController {

	@Autowired
	private VideoService videoService;

	@GetMapping("/{id}")
	public ResponseEntity<VideoModel> get(@PathVariable(name = "id") final Integer id) throws FormDataInvalidException {
		return new ResponseEntity<>(this.videoService.get(id), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<VideoModel> create(@RequestBody final VideoCreationModel model) throws FormDataInvalidException {
			return new ResponseEntity<>(this.videoService.create(model), HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<VideoModel> update(@RequestBody final VideoModel model) throws FormDataInvalidException,
																																											 ContentNotFoundException {
		return new ResponseEntity<>(this.videoService.update(model), HttpStatus.OK);
	}
}
