package org.echocat.kata.java.part1;

import java.util.Objects;

import com.google.common.base.MoreObjects;

/**
 * Entity to store Author data.
 * @author anandmohan
 *
 */
public class Author {
	private String email;
	private String firstName;
	private String lastName;

	public Author() {
	}

	public Author(String email, String firstName, String lastName) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper("Author").add("email", email)
				.add("firstName", firstName).add("lastName", lastName)
				.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.email);
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}

		if (obj instanceof Author) {
			return Objects.equals(this.email, ((Author) obj).email);
		}
		return false;
	}
}
