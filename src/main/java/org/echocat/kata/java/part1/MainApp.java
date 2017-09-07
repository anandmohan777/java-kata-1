package org.echocat.kata.java.part1;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.google.common.base.Optional;
import com.opencsv.CSVReader;

/**
 * Class to handle to logic of read csv and some basic functions.
 * 
 * @author anandmohan
 *
 */
public class MainApp {

	/*
	 * Constants.
	 */
	public static final String AUTHOR = "AUTHOR";
	public static final String BOOK = "BOOK";
	public static final String MAGAZINE = "MAGAZINE";

	private final Map<String, Author> authorCSV;
	private final Map<String, Journal> bookCSV;

	MainApp() {
		authorCSV = new ConcurrentHashMap<>();
		bookCSV = new ConcurrentHashMap<>();
	}

	public Map<String, Author> getAuthorCSV() {
		return authorCSV;
	}

	public Map<String, Journal> getBookCSV() {
		return bookCSV;
	}

	public static void main(String[] args) {
		MainApp obj = new MainApp();

		/*
		 * Constant path of csv files.
		 */
		final String authorPath = "src/main/resources/org/echocat/kata/java/part1/data/authors.csv";
		final String bookPath = "src/main/resources/org/echocat/kata/java/part1/data/books.csv";
		final String magPath = "src/main/resources/org/echocat/kata/java/part1/data/magazines.csv";

		obj.readCSV(authorPath, AUTHOR);
		obj.readCSV(bookPath, BOOK);
		obj.readCSV(magPath, MAGAZINE);

		System.out
				.println("######## Printing out all books and magazines ##########");
		obj.printJournal();

		System.out
				.println("\n######## Find a book or magazine by its isbn: 2145-8548-3325 ##########");
		obj.findByISBN("2145-8548-3325");

		System.out
				.println("\n######## Find all books and magazines by their authors’ email: null-walter@echocat.org ##########");
		obj.findByAuthorsEmail("null-walter@echocat.org");

		System.out
				.println("\n######## Print out all books and magazines with all their details sorted by title ##########");
		obj.printBySortedTitle();
	}

	private void printJournal() {
		bookCSV.entrySet().stream().forEach(entry -> {
			Journal data = entry.getValue();
			printLogic(data);
		});
	}

	private void findByISBN(final String isbn) {
		Journal data = bookCSV.get(isbn);
		printLogic(data);
	}

	private void findByAuthorsEmail(final String authorEmail) {
		bookCSV.entrySet()
				.stream()
				.filter(input -> {
					Journal obj = input.getValue();
					String author = obj.getAuthors();
					if (author != null && author.contains(authorEmail)
							&& authorCSV.get(authorEmail) != null) {
						return true;
					}
					return false;
				}).collect(Collectors.toList())
				.forEach(data -> (printLogic(data.getValue())));
	}

	private void printBySortedTitle() {
		bookCSV.entrySet().stream().sorted((journal1, journal2) -> {
			Journal j1 = journal1.getValue();
			Journal j2 = journal1.getValue();
			if (j1 == null || j2 == null) {
				return -1;
			}

			return j1.getTitle().compareTo(j2.getTitle());
		}).collect(Collectors.toList())
				.forEach(data -> (printLogic(data.getValue())));
	}

	private void printLogic(final Journal data) {
		if (data != null) {
			if (BOOK.equals(data.getType()))
				printBook(data);
			else if (MAGAZINE.equals(data.getType()))
				printMagazine(data);
		}
	}

	private void printBook(final Journal data) {
		Book book = (Book) data;
		String authors = book.getAuthors();
		String name = "";
		Author author = null;

		for (String anotherSplit : authors.split(",")) {
			Author author2 = Optional.fromNullable(authorCSV.get(anotherSplit))
					.or(new Author());
			name += author2.getFirstName() + " ";

			if (author == null)
				author = authorCSV.get(anotherSplit);
		}

		String authorName = (author != null) ? (", AUTHORS NAME: " + name) : "";
		System.out.println(data.getType() + " ISBN: " + data.getIsbn()
				+ ", TITLE: " + data.getTitle() + authorName
				+ ", DESCRIPTION: " + book.getDescription());
	}

	private void printMagazine(final Journal data) {
		Magazine magazine = (Magazine) data;
		String authors = magazine.getAuthors();
		String name = "";
		Author author = null;

		for (String anotherSplit : authors.split(",")) {
			Author author2 = Optional.fromNullable(authorCSV.get(anotherSplit))
					.or(new Author());
			name += author2.getFirstName() + " ";

			if (author == null)
				author = authorCSV.get(anotherSplit);
		}

		String authorName = (author != null) ? (", AUTHORS NAME: " + name) : "";
		System.out.println(MAGAZINE + " ISBN: " + data.getIsbn() + ", TITLE: "
				+ data.getTitle() + authorName + ", PUBLISHED AT: "
				+ magazine.getPublishedAt());
	}

	private void readCSV(String authorPath, String detail) {
		try {
			CSVReader reader = new CSVReader(new FileReader(authorPath), ';');
			List<String[]> records = reader.readAll();
			Iterator<String[]> iterator = records.iterator();

			while (iterator.hasNext()) {
				String[] record = iterator.next();
				if (AUTHOR.equals(detail)) {
					if (!"email".equals(record[0])) {
						Author response = new Author(record[0], record[1],
								record[2]);
						authorCSV.put(response.getEmail(), response);
					}
				} else if (BOOK.equals(detail)) {
					if (!"isbn".equals(record[1])) {
						Journal response = new Book(detail, record[0],
								record[1], record[2], record[3]);
						bookCSV.put(response.getIsbn(), response);
					}
				} else if (MAGAZINE.equals(detail)) {
					if (!"isbn".equals(record[1])) {
						Journal response = new Magazine(detail, record[0],
								record[1], record[2], record[3]);
						bookCSV.put(response.getIsbn(), response);
					}
				}
			}
			reader.close();
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}
}
