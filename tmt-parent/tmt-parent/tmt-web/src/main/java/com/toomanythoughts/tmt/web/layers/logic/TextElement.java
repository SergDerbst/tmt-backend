package com.toomanythoughts.tmt.web.layers.logic;

/**
 * A text element is the most basic structural element of text. Apart from
 * certain style information such as bold, italic, or underline, it may consist
 * of links (to the web) or sidenotes.
 *
 * @author Sergio Weigel
 *
 */
public abstract class TextElement {

	public enum TextType {
		Bold,
		Italic,
		Link,
		Plain,
		Sidenote,
		Underline;
	}

	private final TextType type;
	private String text;

	public TextElement(final TextType type) {
		this.type = type;
	}

	public TextType getType() {
		return this.type;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}
}

