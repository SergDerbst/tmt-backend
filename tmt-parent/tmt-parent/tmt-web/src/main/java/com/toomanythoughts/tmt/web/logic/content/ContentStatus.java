package com.toomanythoughts.tmt.web.logic.content;

/**
 * The status a piece of content can have during its lifecycle.
 *
 * @author Sergio Weigel
 *
 */
public enum ContentStatus {
	/*
	 * Has been created, but is still empty (only mandatory data).
	 */
	Created,
	/*
	 *  Has at least an abstract or some other text;
	 *  at least a paragraph or section in articles, some subtitlesfor videos and podcasts.
	 */
	InProcess,
	/*
	 *  Has been finished and was given to another author for review (optional).
	 */
	InReview,
	/*
	 *  Has been reviewed (optional).
	 *  Note: an articles and transcipts can be reviewed and in review several times before
	 *  being published.
	 */
	Reviewed,
	/*
	 * Has been scheduled for release (optional, when a future release is targeted
	 * on a specific date or time).
	 */
	Scheduled,
	/*
	 *  Has been released.
	 */
	Published,
	/*
	 * Has been removed from public consumption.
	 */
	Unpublished;
}
