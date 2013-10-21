package org.vassilz.test.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.TextField;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import static org.vassilz.test.lucene.index.IndexUtil.*;
import static org.vassilz.test.lucene.Fields.*;

public abstract class BaseLuceneTest {
	
	protected static final String VALUE_PREFIX = "value";

	
	@BeforeClass
	public static void buildSampleIndex() throws IOException {
		Document d;
		Field f;
		
		List<Document> docs = new ArrayList<Document>();
		
		for (int i=10; i<1000; i++) {
			d = new Document();
			f = new TextField(TF, VALUE_PREFIX+i, Store.YES);
			d.add(f);
			f = new IntField(IF, i, Store.YES);
			d.add(f);
			f = new LongField(DF, new Integer(i).longValue(), Store.YES);
			d.add(f);
			
			docs.add(d);
		}
		
		createIndex(docs.toArray(new Document[docs.size()]));
	}
	
	@AfterClass
	public static void deleteSampleIndex() throws IOException {
		deleteIndex();
	}
}
