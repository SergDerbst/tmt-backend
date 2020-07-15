package com.toomanythoughts.tmt.web.logic.content.video;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toomanythoughts.tmt.web.logic.content.video.model.VideoCreationModel;
import com.toomanythoughts.tmt.web.logic.content.video.model.VideoModel;

@RequestMapping("/content/video")
public class VideoController {

	@PostMapping("/create")
	public VideoModel create(@RequestBody VideoCreationModel model) {
		return null;
	}
}
