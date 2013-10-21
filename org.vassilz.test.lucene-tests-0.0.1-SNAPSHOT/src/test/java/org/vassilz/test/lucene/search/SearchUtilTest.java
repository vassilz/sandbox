package org.vassilz.test.lucene.search;

import static org.vassilz.test.lucene.search.SearchUtil.*;
import static org.vassilz.test.lucene.Fields.*;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.Test;
import org.vassilz.test.lucene.BaseLuceneTest;

public class SearchUtilTest extends BaseLuceneTest {

	// ========================== Positive tests ===========================

	@Test
	public void positiveSearch001() throws IOException, ParseException {
		SearchResult result = prefixSearch(TF + ":v*");

		assertFalse("No results found. Expected > 0", result.getDocs()
				.isEmpty());
		for (Document doc : result.getDocs()) {
			assertTrue(doc.get(TF).startsWith("v"));
		}
	}

	@Test
	public void positiveSearch002() throws IOException, ParseException {
		SearchResult result = wildcardSearch(TF + ":v?");

		for (Document doc : result.getDocs()) {
			assertEquals(2L, doc.get(TF).length());
			assertTrue(doc.get(TF).startsWith("v"));
		}
	}

	@Test
	public void positiveSearch003() throws IOException, ParseException {
		SearchResult result = prefixSearch(NONINDEXED_FIELD + ":v*");
		assertTrue("Unexpected search results!", result.getDocs().isEmpty());
	}
	
	@Test
	public void positiveSearch004() throws IOException, ParseException {
		SearchResult result = termSearch(TF+":value101");
		for (Document doc : result.getDocs()) {
			assertEquals("value101", doc.get(TF));
		}
	}
	
	@Test
	public void positiveSearch005() throws IOException, ParseException {
		SearchResult result = termSearch(TF+":value11 value101");
		for (Document doc : result.getDocs()) {
			// second search term is ignored; use phrase search for multi-term queries
			assertEquals("value11", doc.get(TF));
		}
	}
	
	@Test
	public void positiveSearch006() throws IOException, ParseException {
		SearchResult result = phraseSearch(TF+":\"value11 value101\"");
		for (Document doc : result.getDocs()) {
			// second search term is ignored; use phrase search for multi-term queries
			assertEquals("value11 value101", doc.get(TF));
		}
	}
	
	@Test
	public void positiveSearch007() throws IOException, ParseException {
		SearchResult result = fuzzySearch(TF + ":value20~1");
		for (Document doc : result.getDocs()) {
			String value = doc.get(TF);
			assertTrue(
					"Unexpected search result: " + value,
					"value20".equals(value) || "value10".equals(value)
							|| "value2".equals(value)
							|| value.matches("value20\\\\d"));
		}
	}
	
	@Test
	public void positiveSearch008() throws IOException, ParseException {
		SearchResult result = numericRangeSearchInclusive(IF+":[15 TO 25]");
		for (Document doc : result.getDocs()) {
			int value = Integer.parseInt(doc.get(IF));
			assertTrue("Search result is outside range!" + value, (15 <= value) && (value <= 25));
		}
	}
	
	@Test
	public void positiveSearch009() throws IOException, ParseException {
		SearchResult result = numericRangeSearchExclusive(IF+":{15 TO 25}");
		for (Document doc : result.getDocs()) {
			int value = Integer.parseInt(doc.get(IF));
			assertTrue("Search result is outside range!" + value, (15 < value) && (value < 25));
		}
	}

	// ========================== Negative tests ===========================

	@Test(expected = ParseException.class)
	public void negativeSearch001() throws IOException, ParseException {
		wildcardSearch("*");
	}

	@Test(expected = ParseException.class)
	public void negativeSearch002() throws IOException, ParseException {
		wildcardSearch("?");
	}

	@Test(expected = ParseException.class)
	public void negativeSearch003() throws IOException, ParseException {
		wildcardSearch(TF + ":*");
	}

	@Test(expected = ParseException.class)
	public void negativeSearch004() throws IOException, ParseException {
		wildcardSearch(TF + ":?");
	}

}
