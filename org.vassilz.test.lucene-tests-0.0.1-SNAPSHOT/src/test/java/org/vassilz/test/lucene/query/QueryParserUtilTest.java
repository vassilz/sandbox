package org.vassilz.test.lucene.query;

import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.Test;

import static org.vassilz.test.lucene.query.QueryParserUtil.*;


public class QueryParserUtilTest {

	@Test
	public void testParser001() throws ParseException {
		parse("field:value");
	}
}
