package com.example.Quiz;

import java.sql.SQLException;

import org.junit.Test;

import com.jcraft.jsch.JSchException;
import com.vaadin.ui.TextField;

public class ResetPasswordViewTest {
	
	@Test
	public void resetPassword() throws ClassNotFoundException, JSchException, SQLException {
		ResetPasswordView rpv = new ResetPasswordView(); 
		rpv.resetPassword();	
		System.out.println("Success");
	}
	
	@Test
	public void deleteEntry() {
		ResetPasswordView rpv = new ResetPasswordView(); 
		rpv.deleteEntry();	
		System.out.println("Success");
	}
	
	@Test
	public void checkField() {
		TextField tf = new TextField();
		ResetPasswordView rpv = new ResetPasswordView(); 
		rpv.checkField(tf);	
		System.out.println("Success");
	}

	
}
