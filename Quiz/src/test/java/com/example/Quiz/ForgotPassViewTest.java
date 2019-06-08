package com.example.Quiz;

import org.junit.Test;

public class ForgotPassViewTest {
	
	@Test
	public void updateUser() {
		ForgotPassView fpv = new ForgotPassView(); 
		fpv.updateUser("");	
		System.out.println("Success");
	}
}
