package org.echocat.kata.java.part1;

/**
 * Contract of all the Journals written here.
 * @author anandmohan
 */
public interface Journal {	
	String getIsbn();
	String getTitle();
	String getType();
	String getAuthors();
}
