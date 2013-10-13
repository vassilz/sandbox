package org.vassilz.arquillian;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class ArqDAO {

	public String getGreetings() {
		return "none";
	}
	
	public void storeGreeting(String greeting) {
		// noop
		
		System.out.println("DAO invoked!");
	}
}
