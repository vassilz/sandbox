package org.vassilz.test.lucene.search;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.SearcherManager;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.vassilz.test.lucene.index.IndexUtil;

import static org.vassilz.test.lucene.query.QueryParserUtil.*;

public class SearchUtil {
	
	private static SearcherManager manager;
	
	public static SearchResult termSearch(String query) throws IOException, ParseException {
		TermQuery q = (TermQuery) parse(query);
		IndexSearcher s = getInstance();
		SearchResult result = new SearchResult(s, s.search(q, 100));
		
		try {
			return result;
		} finally {
			manager.release(s);
			s = null;
		}
	}
	
	public static SearchResult wildcardSearch(String query) throws IOException, ParseException {
		WildcardQuery q = (WildcardQuery) parse(query);
		IndexSearcher s = getInstance();
		SearchResult result = new SearchResult(s, s.search(q, 100));
		
		try {
			return result;
		} finally {
			manager.release(s);
			s = null;
		}
	}
	
	public static SearchResult prefixSearch(String query) throws IOException, ParseException {
		PrefixQuery q = (PrefixQuery) parse(query);
		IndexSearcher s = getInstance();
		SearchResult result = new SearchResult(s, s.search(q, 100));
		
		try {
			return result;
		} finally {
			manager.release(s);
			s = null;
		}
	}
	
	public static SearchResult phraseSearch(String query) throws IOException, ParseException {
		PhraseQuery q = (PhraseQuery) parse(query);
		IndexSearcher s = getInstance();
		SearchResult result = new SearchResult(s, s.search(q, 100));
		
		try {
			return result;
		} finally {
			manager.release(s);
			s = null;
		}
	}
	
	public static SearchResult fuzzySearch(String query) throws IOException, ParseException {
		FuzzyQuery q = (FuzzyQuery) parse(query);
		IndexSearcher s = getInstance();
		SearchResult result = new SearchResult(s, s.search(q, 100));
		
		try {
			return result;
		} finally {
			manager.release(s);
			s = null;
		}
	}
	
	public static SearchResult numericRangeSearchInclusive(String query) throws IOException, ParseException {
		NumericRangeQuery<Integer> q = (NumericRangeQuery<Integer>) parse(query);
		IndexSearcher s = getInstance();
		SearchResult result = new SearchResult(s, s.search(q, 100));
		
		try {
			return result;
		} finally {
			manager.release(s);
			s = null;
		}
	}
	
	public static SearchResult numericRangeSearchExclusive(String query) throws IOException, ParseException {
		NumericRangeQuery<Integer> q = (NumericRangeQuery<Integer>) parse(query);
		IndexSearcher s = getInstance();
		SearchResult result = new SearchResult(s, s.search(q, 100));
		
		try {
			return result;
		} finally {
			manager.release(s);
			s = null;
		}
	}

	private static IndexSearcher getInstance() throws IOException {
		Directory dir = FSDirectory.open(new File(IndexUtil.LUCENE_INDEX));
		manager = new SearcherManager(dir, null);
		return manager.acquire();
	}

}
