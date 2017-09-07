package org.echocat.kata.java.part1;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MainAppUnitTest {
	
	private MainApp mainApp;
	
	private Map<String, Author> authorCSV;
	private Map<String, Journal> bookCSV;
	
	@BeforeMethod 
	void setup() {
		mainApp = new MainApp();
	}

    @Test
    public void testGetHelloWorldText() throws Exception {
       whenMainIsCalled();
       thenCheckValueRetreved();
    }

	private void whenMainIsCalled() {
		mainApp.main(null);
	}

	private void thenCheckValueRetreved() {
		assertNotNull(mainApp.getBookCSV());
		assertNotNull(mainApp.getAuthorCSV());
		
	}

}
