package org.vassilz.arquillian;

//import java.io.File;
//import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

//import org.apache.lucene.index.DirectoryReader;
//import org.apache.lucene.search.IndexSearcher;
//import org.apache.lucene.search.MatchAllDocsQuery;
//import org.apache.lucene.search.Query;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.FSDirectory;

@LocalBean
@Stateless
public class ArqBean {
	
	@EJB
	private ArqDAO dao;
	
//	public static final String LUCENE_INDEX = "lucene/index";

	public String greetMe(String myName) {
		
		System.out.println("Bean invoked!");
		
		String greeting = "Hello, " + myName;
		dao.storeGreeting(greeting);
		
		return greeting;
	}
	
//	public void searchWithLucene() throws IOException {
//		Directory luceneDir = FSDirectory.open(new File(LUCENE_INDEX));
//		IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(luceneDir));
//		
//		Query query = new MatchAllDocsQuery();
//		searcher.search(query, 10);
//	}
}
