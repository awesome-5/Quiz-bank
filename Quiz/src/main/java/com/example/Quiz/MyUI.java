package com.example.Quiz;

import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

import com.jcraft.jsch.JSchException;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
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


@Theme("mytheme")
public class MyUI extends UI implements View {
	
	

	//Initializing components
	final VerticalLayout layout = new VerticalLayout();
	final TextField userName = new TextField("Username : ");
	final PasswordField password = new PasswordField("Password : ", "");
	HorizontalLayout hl=new HorizontalLayout();
	Button logIn = new Button("Log in");
	Button create = new Button("Sign Up");
	Button forgot = new Button("Forgot Password");
	Label label = new Label(null);

	@Override
	protected void init(VaadinRequest vaadinRequest) {
	    
		//add elements to display and style them
		logIn.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		create.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		forgot.setStyleName(ValoTheme.BUTTON_LINK);
		userName.setRequiredIndicatorVisible(isVisible());
		password.setRequiredIndicatorVisible(isVisible());
		
		setContent(layout);  	
		layout.setSizeFull();
	    final Panel panel = new Panel ();
		panel.setWidth(null);
		layout.addComponent(panel);
		layout.setComponentAlignment(panel,Alignment.MIDDLE_CENTER );		
		final FormLayout formlayout = new FormLayout();
		formlayout.setMargin(true);
		formlayout.setStyleName("loginForm");
		hl.addComponents(logIn,create);
		formlayout.addComponents(userName, password, label, hl, forgot);
		panel.setContent(formlayout);
		
		logIn.addClickListener(log -> {
			try {
				DBConnection dbc = new DBConnection();
				String s=dbc.readDB("SELECT * FROM User WHERE username='"+userName.getValue()+"' AND password='"+password.getValue()+"'");
				if (s.equals("Failed to SQL"))
				{
					
					label.setValue("Invalid Login");
					userName.clear();
					password.clear();
				}
				else {
					Notification.show("Login Successful");
				}
			} catch (ClassNotFoundException | JSchException | SQLException e1) {
			}
		});
		
		userName.addFocusListener(new FocusListener(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void focus(FocusEvent event) {
				label.setValue(null);
			}
			
		});
		password.addFocusListener(new FocusListener(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void focus(FocusEvent event) {
				label.setValue(null);
			}
			
		});
		create.addClickListener(e -> {
			
		  	//Initializing components
			final VerticalLayout layout = new VerticalLayout();
			final TextField firstName = new TextField("First name : ");
			final TextField lastName  =  new TextField("Last name : ");
			final TextField email = new TextField("Email : ");
//			final TextField chooseUsername = new TextField("Choose username : ");
			final TextField userName = new TextField("Username : ");
			final PasswordField password = new PasswordField("Password : ", "");
//			final PasswordField confirmPassword = new PasswordField("Confirm password : ");
			
			
			HorizontalLayout hl=new HorizontalLayout();
			Button  signUp = new Button("Sign up");
			Button back = new Button("Back");
			Label label = new Label(null);

			
			//add elements to display and style them
			signUp.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			back.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			
			firstName.setRequiredIndicatorVisible(isVisible());
			lastName.setRequiredIndicatorVisible(isVisible());
			email.setRequiredIndicatorVisible(isVisible());
			//chooseUsername.setRequiredIndicatorVisible(isVisible());
			userName.setRequiredIndicatorVisible(isVisible());
			password.setRequiredIndicatorVisible(isVisible());
		//	confirmPassword.setRequiredIndicatorVisible(isVisible());
		
			
			setContent(layout);  	
			layout.setSizeFull();
		    final Panel panel_signin = new Panel ();
		    panel_signin.setWidth(null);
			layout.addComponent(panel_signin);
			layout.setComponentAlignment(panel_signin,Alignment.MIDDLE_CENTER );		
			final FormLayout formalayout_ = new FormLayout();
			formalayout_.setMargin(true);
			formalayout_.setStyleName("Signup form");
			hl.addComponents(signUp,back);
			formalayout_.addComponents(firstName,lastName,email,userName, password, label, hl);
			panel_signin.setContent(formalayout_);
			
			
			signUp.addClickListener(log -> {
				try {
					DBConnection dbc = new DBConnection();
					String s=dbc.readDB("SELECT * FROM User WHERE username='"+userName.getValue()+"' AND password='"+password.getValue()+"'");
					if (s.length()> 1)
					{
						
						label.setValue("Already register");
						userName.clear();
						password.clear();
					}
					else {
						s=dbc.readDB("INSERT INTO User (firstname,lastname,email,username,password) VALUES ("+firstName.getValue()+","+lastName.getValue()+","+email.getValue()+","+userName.getValue()+","+password.getValue());
						
					
							
							label.setValue("Registration successful");
				
						
					}
				} catch (ClassNotFoundException | JSchException | SQLException e1) {
				}
			});
			
			back.addClickListener(log -> {
				//Done straight back
				 UI.getCurrent().getPage().reload();
			});
			
		});
		
		forgot.addClickListener(e -> {

			//Initializing components
			final VerticalLayout layout = new VerticalLayout();
			final TextField email = new TextField("Email : ");
			
			HorizontalLayout hl=new HorizontalLayout();
			Button  Submit = new Button("Submit");
			Button back = new Button("back");
			Label label = new Label(null);
			
			
			//add elements to display and style them
			Submit.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			back.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			email.setRequiredIndicatorVisible(isVisible());
			
			setContent(layout);  	
			layout.setSizeFull();
			final Panel panel_signin = new Panel ();
			panel_signin.setWidth(null);
			layout.addComponent(panel_signin);
			layout.setComponentAlignment(panel_signin,Alignment.MIDDLE_CENTER );		
			final FormLayout formalayout_ = new FormLayout();
			formalayout_.setMargin(true);
			formalayout_.setStyleName("Reset password");
			hl.addComponents(Submit,back);
			formalayout_.addComponents(email, label,hl);
			panel_signin.setContent(formalayout_ );
			
			
			Submit.addClickListener(log -> {
				try {
					DBConnection dbc = new DBConnection();
					String s=dbc.readDB("SELECT * FROM User WHERE username='"+email.getValue()+"'"); 
					if (s.length()> 1)
					{
						//TODO send link to the email to reset password 
						label.setValue("Email sent, please check your email");
						
					}
					else {
						label.setValue("Email doesn't exist, please register");
					}
				} catch (ClassNotFoundException | JSchException | SQLException e1) {
				}
			});
			
			back.addClickListener(log -> {
				 UI.getCurrent().getPage().reload();
			});
			
			
			
		});
	
	}
	


	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	}


}
