package org.vassilz.arquillian;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class LuceneHelper {

	public static final LuceneHelper instance = new LuceneHelper();

	private LuceneHelper() {

	}
	
	private IndexWriter writerInstance() throws IOException {
		IndexWriterConfig writerConfig = new IndexWriterConfig(Version.LUCENE_45, new SimpleAnalyzer(Version.LUCENE_45));
		Directory luceneDir = FSDirectory.open(new File(ArqBean.LUCENE_INDEX));
		return new IndexWriter(luceneDir, writerConfig);
	}

	public void indexData() throws IOException {
		IndexWriter writer = writerInstance();

		Document doc = new Document();
		doc.add(new TextField("tf", "value001", Store.YES));
		doc.add(new IntField("if", 100, Store.YES));
		writer.addDocument(doc);

		writer.close();
	}

	public void disposeOfIndex() throws IOException {
		IndexWriter writer = writerInstance();

		writer.deleteAll();

		writer.close();
	}
}
