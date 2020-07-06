package com.toomanythoughts.tmt.web.logic.content;

/**
 * The status a piece of content can have during its lifecycle.
 *
 * @author Sergio Weigel
 *
 */
public enum ContentStatus {
	/*
	 * Has been created, but is still empty (only title, subtitle, and notes)
	 */
	Created,
	/*
	 *  Has at least an abstract or some other text;
	 *  at least a paragraph or section in articles, some subtitlesfor videos and podcasts.
	 */
	InProcess,
	/*
	 *  Has been finished and was given to another author for review.
	 */
	InReview,
	/*
	 *  Has been reviewed. Note: an article can be reviewed and in review several times before
	 *  being published.
	 */
	Reviewed,
	/*
	 * Has been scheduled for release. This optional for when a future release is targeted
	 * on a specific date or time.
	 */
	Scheduled,
	/*
	 *  Has been released.
	 */
	Published,
	/*
	 * Has been removement from public consumption.
	 */
	Unpublished;
}
