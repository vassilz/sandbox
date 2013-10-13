package org.vassilz.arquillian;

import java.util.Arrays;
import java.util.List;

import org.apache.lucene.document.Document;

public class SearchResult {
	
	private final Document[] docs;

	public SearchResult(Document... docs) {
		this.docs = docs;
	}
	
	public List<Document> getDocs() {
		return Arrays.asList(docs.clone());
	}
	
	@Override
	public String toString() {
		if (docs == null) {
			return super.toString();
		}
		
		int hitCount = docs.length;
		
		StringBuilder resultString = new StringBuilder();
		for (int i=0; i<hitCount; i++) {
			resultString.append(docs[i].toString());
		}
		
		return resultString.toString();
	}
}
