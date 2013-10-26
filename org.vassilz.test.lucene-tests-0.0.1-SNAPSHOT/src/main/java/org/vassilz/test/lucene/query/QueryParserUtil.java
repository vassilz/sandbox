package org.vassilz.test.lucene.query;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.vassilz.test.lucene.Fields;

public class QueryParserUtil {
	
//	private final class QueryParserManager extends ReferenceManager<QueryParser> {
//
//		@Override
//		protected void decRef(QueryParser reference) throws IOException {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		protected QueryParser refreshIfNeeded(QueryParser referenceToRefresh)
//				throws IOException {
//			// TODO Auto-generated method stub
//			return referenceToRefresh;
//		}
//
//		@Override
//		protected boolean tryIncRef(QueryParser reference) throws IOException {
//			// TODO Auto-generated method stub
//			return false;
//		}
//		
//	}

	private static final Analyzer defaultAnalyzer = new SimpleAnalyzer(Version.LUCENE_45);
	public static final String DEFAULT_FIELD = Fields.TF;
	
	private static QueryParser getParserInstance() {
		return new QueryParser(Version.LUCENE_45, DEFAULT_FIELD, defaultAnalyzer);
	}
	
	public static Query parse(String query) throws ParseException {
		return getParserInstance().parse(query);
	}
	
}
