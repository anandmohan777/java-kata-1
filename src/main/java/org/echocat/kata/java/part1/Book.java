package org.echocat.kata.java.part1;

import java.util.Objects;

import com.google.common.base.MoreObjects;

/**
 * Entity to store Book data.
 * @author anandmohan
 *
 */
public class Book implements Journal, Comparable<Book> {
	private String title;
	private String isbn;
	private String authors;
	private String description;
	private String type;

	public Book(String type, String title, String isbn, String authors, String description) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.authors = authors;
		this.description = description;
		this.type = type;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper("Book").add("type", type)
				.add("title", title).add("isbn", isbn).add("authors", authors)
				.add("description", description).toString();
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

		if (obj instanceof Book) {
			return Objects.equals(this.isbn, ((Book) obj).isbn);
		}
		return false;
	}
	
	@Override
	public int compareTo(Book o) {
		 return this.getIsbn().compareTo(o.getIsbn());
	}
}
