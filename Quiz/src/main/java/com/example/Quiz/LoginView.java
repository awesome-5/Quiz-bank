package com.example.Quiz;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
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

public class LoginView extends VerticalLayout implements View 
{
	static String loggedInUser="";
	final VerticalLayout layout = new VerticalLayout();
	final TextField userName = new TextField("Username : ");
	final PasswordField password = new PasswordField("Password : ", "");
	HorizontalLayout hl=new HorizontalLayout();
	final Panel panel = new Panel ();
	Button logIn = new Button("Log in");
	Button create = new Button("Sign Up");
	Button forgot = new Button("Forgot Password");
	Label label = new Label(null);
	final FormLayout formlayout = new FormLayout();
 
	
	@Override
	public void enter(ViewChangeEvent event) {
		logIn.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		create.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		forgot.setStyleName(ValoTheme.BUTTON_LINK);
		userName.setRequiredIndicatorVisible(isVisible());
		password.setRequiredIndicatorVisible(isVisible());

		addComponent(layout);  	
		
		
		panel.setWidth(null);
		layout.setSizeFull();
		layout.addComponent(panel);
		layout.setComponentAlignment(panel,Alignment.MIDDLE_CENTER );		
		formlayout.setMargin(true);
		formlayout.setStyleName("loginForm");
		hl.addComponents(logIn,create);
		hl.setComponentAlignment(logIn, Alignment.MIDDLE_CENTER);
		hl.setComponentAlignment(create, Alignment.MIDDLE_CENTER);
		formlayout.addComponents(userName, password, label, hl, forgot);
		formlayout.setComponentAlignment(userName, Alignment.MIDDLE_CENTER);
		formlayout.setComponentAlignment(password, Alignment.MIDDLE_CENTER);
		formlayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
		formlayout.setComponentAlignment(hl, Alignment.MIDDLE_CENTER);
		formlayout.setComponentAlignment(forgot, Alignment.MIDDLE_CENTER);
		
		panel.setContent(formlayout);
		
		logIn.setClickShortcut(KeyCode.ENTER);
		userName.focus();
		
		logIn.addClickListener(log -> {
			try {
				DBConnection dbc = new DBConnection();
				String s=dbc.readDBUser("SELECT * FROM User WHERE username='"+userName.getValue()+"' AND password='"+password.getValue()+"'");
				System.out.println("s is "+s);
				if (s==null)
				{
					label.setValue("Invalid Login");
					System.out.println("Invalid");
					userName.clear();
					password.clear();
				}
				else {
					//Notification.show("Login Successful");
					loggedInUser=userName.getValue();
	            	MyUI.navigator.navigateTo(MyUI.HOMEPAGE);

				}
			} catch (ClassNotFoundException | JSchException | SQLException e1) {
			}
		});

		userName.addFocusListener(new FocusListener(){

			@Override
			public void focus(FocusEvent event) {
				label.setValue(null);
			}

		});
		password.addFocusListener(new FocusListener(){

			@Override
			public void focus(FocusEvent event) {
				label.setValue(null);
			}

		});

		create.addClickListener(e -> {
			MyUI.navigator.navigateTo(MyUI.SIGNUP);
			});

		forgot.addClickListener(e -> {
			MyUI.navigator.navigateTo(MyUI.FORGOTVIEW);
		});

	}


}