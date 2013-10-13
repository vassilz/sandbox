package org.vassilz.arquillian;

import javax.ejb.EJB;

//import org.apache.lucene.store.Directory;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.vassilz.arquillian.ArqBean;
import org.vassilz.arquillian.ArqDAO;

@RunWith(Arquillian.class)
public class ArqBeanTest {

	@Deployment
	public static JavaArchive deployJar() {
		return ShrinkWrap.create(JavaArchive.class, "arq-test.jar")
				.addClasses(ArqBean.class, ArqDAO.class)//, Directory.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	private ArqBean bean;

	// @Ignore
	@Test
	public void greetMe() {
		Assert.assertEquals("Hello, Vassil", bean.greetMe("Vassil"));
	}

	// @Test
	@Ignore
	public void foo() {
		System.out.println("Skip tests, please :)");
	}
}
