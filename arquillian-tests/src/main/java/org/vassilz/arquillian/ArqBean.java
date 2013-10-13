package org.vassilz.arquillian;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

@LocalBean
@Stateless
public class ArqBean {
	
	@EJB
	private ArqDAO dao;
	
	@EJB
	private LuceneBean lucene;

	public String greetMe(String myName) {
		
		System.out.println("Bean invoked!");
		
		String greeting = "Hello, " + myName;
		dao.storeGreeting(greeting);
		
		return greeting;
	}
	
	public SearchResult searchWithLucene() throws IOException {
		return lucene.allDocsSearch();
	}
}
