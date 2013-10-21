package org.vassilz.test.lucene.index;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class IndexUtil {
	
	public static final String LUCENE_INDEX = "C:/tmp/lucene-index";
	private static final Analyzer defaultAnalyzer = new SimpleAnalyzer(Version.LUCENE_45);
	
	private static IndexWriter openWriter() throws IOException {
		Directory dir = FSDirectory.open(new File(LUCENE_INDEX));
		IndexWriterConfig writerCfg = new IndexWriterConfig(Version.LUCENE_45, defaultAnalyzer);
		return new IndexWriter(dir, writerCfg);
	}
	
	public static void createIndex(Document... docs) throws IOException {
		IndexWriter w = openWriter();
		w.addDocuments(Arrays.asList(docs), defaultAnalyzer);
		w.close(true);
	}
	
	public static void deleteIndex() throws IOException {
		IndexWriter w = openWriter();
		w.deleteAll();
		w.close(true);
	}

}
