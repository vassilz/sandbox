package org.vassilz.arquillian;

import java.io.File;
import java.io.IOException;

import javax.ejb.EJB;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

// TODO @BeforeTest, @BeforeClass, @Before... get familiar with TestNG..!
public class ArqBeanTestNGTest extends Arquillian {

	// @Deployment
	// public static JavaArchive createJar() {
	// return ShrinkWrap.create(JavaArchive.class, "arq-test.jar")
	// .addClasses(ArqBean.class, ArqDAO.class)
	// .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	// }

	@Deployment
	public static WebArchive createWar() {

		Reporter.log("create deployment", true);

		File[] libraries = Maven
				.resolver()
				.loadPomFromFile("pom.xml")
				.resolve("org.apache.lucene:lucene-core",
						"org.apache.lucene:lucene-analyzers-common",
						"org.apache.lucene:lucene-queryparser")
				.withTransitivity().asFile();

		return ShrinkWrap
				.create(WebArchive.class, "arq-test.war")
				.addClasses(ArqBean.class, ArqDAO.class, LuceneHelper.class,
						SearchResult.class, LuceneBean.class)
				// , ArqBeanJUnitTest.class) uncomment for jboss-managed
				.addAsWebInfResource(EmptyAsset.INSTANCE,
						ArchivePaths.create("beans.xml"))
				.addAsLibraries(libraries);
	}

	// @BeforeClass
	// public static void beforeClass() {
	// Reporter.log("before class", true);
	// }

	@BeforeMethod
	public void beforeMethod() throws IOException {
		Reporter.log("before method", true);
		LuceneHelper.instance.createIndex();
	}
	
	@AfterMethod
	public void afterMethod() throws IOException {
		Reporter.log("after method", true);
		LuceneHelper.instance.disposeOfIndex();
	}

	@EJB
	private ArqBean bean;
	
	@EJB
	private LuceneBean luceneBean;

	// @Ignore
	@Test
	public void greetMe() {
		Assert.assertEquals("Hello, Vassil", bean.greetMe("Vassil"));
	}

	@Test
	public void generalSearch() throws IOException {
		SearchResult result = bean.searchWithLucene();
		Reporter.log(result.toString(), true);
	}
	
	@Test
	public void testLuceneQueries() throws ParseException, IOException {
		SearchResult result = luceneBean.termQuerySearch(LuceneHelper.parseQuery("tf:v*"));
		for (Document doc : result.getDocs()) {
			Reporter.log(doc.toString(), true);
			Assert.assertNotNull(doc.getField("tf"));
			Assert.assertTrue(doc.get("tf").startsWith("v"));
		}
	}

	@Test
	public void foo() {
		System.out.println("Skip tests, please :)");
	}

}
