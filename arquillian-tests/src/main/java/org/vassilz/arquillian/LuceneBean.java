package org.vassilz.arquillian;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
public class LuceneBean {

	public static final String INDEX_LOCATION = "lucene/index";
	private static IndexSearcher searcher;
	{
		try {
			searcher = new IndexSearcher(DirectoryReader.open(FSDirectory
					.open(new File(INDEX_LOCATION))));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public SearchResult allDocsSearch() throws IOException {
		TopDocs topDocs = search(new MatchAllDocsQuery());
		return new SearchResult(toLuceneDocs(topDocs));
	}

	public SearchResult termQuerySearch(Query q) throws IOException {
		return new SearchResult(toLuceneDocs(search(q)));
	}

	public SearchResult termRangeQuerySearch(Query q) {
		// TODO add logic
		return null;
	}

	public SearchResult numericQuerySearch(Query q) {
		// TODO add logic
		return null;
	}

	public SearchResult numericRangeQuerySearch(Query q) {
		// TODO add logic
		return null;
	}

	public SearchResult dateRangeQuerySearch(Query q) {
		// TODO add logic
		return null;
	}

	public SearchResult fuzzyQuerySearch(Query q) {
		// TODO add logic
		return null;
	}

	public SearchResult proximityQuerySearch(Query q) {
		// TODO add logic
		return null;
	}

	public SearchResult andQuerySearch(Query q) {
		// TODO add logic
		return null;
	}

	public SearchResult notQuerySearch(Query q) {
		// TODO add logic
		return null;
	}

	public SearchResult requiredQuerySearch(Query q) {
		// TODO add logic
		return null;
	}

	public SearchResult exclusiveQuerySearch(Query q) {
		// TODO add logic
		return null;
	}

	private TopDocs search(Query q) throws IOException {
		TopDocs topDocs = searcher.search(q, 10);
		
		// uncomment for verbose log
		
//		for (int i = 0; i < topDocs.totalHits; i++) {
//			ScoreDoc sd = topDocs.scoreDocs[i];
//			searcher.explain(q, sd.doc);
//		}
		
		return topDocs;
	}
	
	private Document[] toLuceneDocs(TopDocs topDocs) throws IOException {
		if (topDocs == null) {
			return new Document[]{};
		}

		List<Document> docs = new ArrayList<Document>();
		for (int i = 0; i < topDocs.totalHits; i++) {
			ScoreDoc sd = topDocs.scoreDocs[i];
			Document doc = searcher.doc(sd.doc);
			docs.add(doc);
		}
		return docs.toArray(new Document[]{});
	}
}
