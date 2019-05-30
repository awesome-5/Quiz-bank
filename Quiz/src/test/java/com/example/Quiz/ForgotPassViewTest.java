package com.example.Quiz;

import org.junit.Test;

public class ForgotPassViewTest {
	@Test
	public void sendEmail() {
		ForgotPassView fpv = new ForgotPassView(); 
		fpv.sendEmail("natp4444@yahoo.com");	
		System.out.println("Success");
	}
	
	@Test
	public void updateUser() {
		ForgotPassView fpv = new ForgotPassView(); 
		fpv.updateUser("");	
		System.out.println("Success");
	}
}
