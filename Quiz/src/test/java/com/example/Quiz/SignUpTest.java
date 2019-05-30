package com.example.Quiz;

import org.junit.Test;

import com.vaadin.ui.TextField;

public class SignUpTest {
	@Test
	public void checkField(){
		TextField tf= new TextField();
		SignupView sv = new SignupView(); 
		sv.checkField(tf);	
		System.out.println("Success");
	}
}
