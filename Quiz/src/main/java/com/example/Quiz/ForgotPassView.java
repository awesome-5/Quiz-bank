package com.example.Quiz;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.annotation.WebServlet;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.jcraft.jsch.JSchException;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class ForgotPassView extends VerticalLayout implements View 
{
	final VerticalLayout layout = new VerticalLayout();
	final TextField email = new TextField("E-mail: ");
	HorizontalLayout hl=new HorizontalLayout();
	final Panel panel = new Panel ();
	Button send = new Button("Send E-mail");
	Button back = new Button("Back");
	Label label = new Label(null);
	final FormLayout formlayout = new FormLayout();
	String em=null;

	@Override
	public void enter(ViewChangeEvent event) {
		send.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		email.setRequiredIndicatorVisible(isVisible());
		addComponent(layout);  	
		panel.setWidth(null);
		layout.setSizeFull();
		layout.addComponent(panel);
		layout.setComponentAlignment(panel,Alignment.MIDDLE_CENTER );		
		formlayout.setMargin(true);
		hl.addComponents(send,back);
		formlayout.addComponents(email,hl);
		panel.setContent(formlayout);

		send.setClickShortcut(KeyCode.ENTER);
		email.focus();

		send.addClickListener(log -> {
			try {
				DBConnection dbc = new DBConnection();
				em=dbc.readDBUser("SELECT * from User WHERE email='"+email.getValue()+"'");
				System.out.println("email is "+em);
			} catch (ClassNotFoundException | JSchException | SQLException e1) {
			}

			if (em!=null)
			{
				System.out.println("email is in database"+em);
				sendEmail(em);
			}
			Notification.show("E-mail Sent");
			MyUI.navigator.navigateTo(MyUI.LOGINVIEW);

		});


		back.addClickListener(e -> {
			MyUI.navigator.navigateTo(MyUI.LOGINVIEW);
		});


	}


	private void sendEmail(String to) {
		try {
			String token;
			final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			SecureRandom rnd = new SecureRandom();
			StringBuilder sb = new StringBuilder(8);	
			for( int i = 0; i < 10; i++ ) 
			{
				sb.append( AB.charAt(rnd.nextInt(AB.length()) ) );
			}
			token=sb.toString();
			
			// all values as variables to clarify its usage
			String from = "sender@test.com";
			String recipient = "natp4444@gmail.com";	
			String subject = "Password Reset Token";	
			String text = "Good Day \n Please click on the following link to reset your password. \n http://localhost:8080/#!forgotPassword \n Your token is " +token+ " and should be entered on the forgot password page. \n This token will only be valid for 12 hours";	

			HtmlEmail email = new HtmlEmail();
			email.setHostName("localhost");
			email.setSmtpPort(9090);
			email.setAuthentication("sender@test.com", "password");
			email.setFrom("sender@test.com");
			email.addTo("recipient1@test.com", "recipient2@test.com");
			email.setSubject("Password Reset Token");
			email.setHtmlMsg("Good Day \n Please click on the following link to reset your password. \n http://localhost:8080/#!resetPassword \n Your token is " +token+ " and should be entered on the forgot password page. \n This token will only be valid for 12 hours");
			email.send();
			Notification.show("Email sent");

			updateUser(token);
		} catch (Exception e) {
			e.printStackTrace();
			Notification.show("Error sending the email", Notification.Type.ERROR_MESSAGE);
		}
	}
	void updateUser(String token)
	{
			try {
				DBConnection dbc = new DBConnection();
				dbc.addID("INSERT INTO Reset VALUES('"+email.getValue()+ "', NULL,'"+token+"')");
				System.out.println("delete entry method");

			} catch (ClassNotFoundException | JSchException | SQLException e1) {
			}
	}
}

