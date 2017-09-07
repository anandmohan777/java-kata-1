package org.echocat.kata.java.part1;

import java.util.Objects;

import com.google.common.base.MoreObjects;

/**
 * Entity to store Magazine data.
 * @author anandmohan
 *
 */
public class Magazine implements Journal, Comparable<Magazine>  {
	private String title;
	private String isbn;
	private String authors;
	private String type;
	private String publishedAt;

	public Magazine(String type, String title, String isbn, String authors,
			String publishedAt) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.authors = authors;
		this.type = type;
		this.publishedAt = publishedAt;
	}

	public String getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper("Magazine").add("type", type)
				.add("title", title).add("isbn", isbn).add("authors", authors)
				.add("publishedAt", publishedAt).toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.isbn);
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}

		if (obj instanceof Magazine) {
			return Objects.equals(this.isbn, ((Magazine) obj).isbn);
		}
		return false;
	}
	
	@Override
	public int compareTo(Magazine o) {
		 return this.getIsbn().compareTo(o.getIsbn());
	}
}
