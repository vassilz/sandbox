package org.vassilz.arquillian;

import java.io.File;
import java.io.IOException;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.vassilz.arquillian.ArqBean;
import org.vassilz.arquillian.ArqDAO;

//@RunWith(Arquillian.class)
public class ArqBeanJUnitTest {

	// @Deployment
	// public static JavaArchive createJar() {
	// return ShrinkWrap.create(JavaArchive.class, "arq-test.jar")
	// .addClasses(ArqBean.class, ArqDAO.class)
	// .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	// }

	@Deployment
	public static WebArchive createWar() {
		
		System.out.println("create deployment");

		File[] libraries = Maven.resolver().loadPomFromFile("pom.xml")
				.resolve("org.apache.lucene:lucene-core").withTransitivity()
				.asFile();

		return ShrinkWrap.create(WebArchive.class, "arq-test.war")
				.addClasses(ArqBean.class, ArqDAO.class)// , ArqBeanJUnitTest.class) uncomment for jboss-managed
				.addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
				.addAsLibraries(libraries);
	}
	
//	@BeforeClass
	public static void beforeClass() {
		System.out.println("before class");
	}
	
//	@Before
	public void before() {
		System.out.println("before");
	}

	@EJB
	private ArqBean bean;

	// @Ignore
//	@Test
	public void greetMe() {
//		Assert.assertEquals("Hello, Vassil", bean.greetMe("Vassil"));
	}
	
//	@Test
	public void testWithLucene() throws IOException {
		bean.searchWithLucene();
	}

	// @Test
//	@Ignore
	public void foo() {
		System.out.println("Skip tests, please :)");
	}
}
