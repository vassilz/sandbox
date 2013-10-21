package org.vassilz.test.lucene.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TopDocs;

public class SearchResult {

	private List<Document> docs = new ArrayList<Document>();
	
	public SearchResult(IndexSearcher searcher, TopDocs topDocs) throws IOException {
		if (topDocs != null && topDocs.scoreDocs.length > 0) {
			for (int i=0; i<topDocs.scoreDocs.length; i++) {
				docs.add(searcher.doc(topDocs.scoreDocs[i].doc));
			}
		}
	}
	
	public List<Document> getDocs() {
		return Collections.unmodifiableList(docs);
	}
	
	@Override
	public String toString() {
		return docs.toString();
	}
}
