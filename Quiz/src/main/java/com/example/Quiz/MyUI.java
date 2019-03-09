package com.example.Quiz;

import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

import com.jcraft.jsch.JSchException;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
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


@Theme("mytheme")
public class MyUI extends UI {
	final VerticalLayout layout = new VerticalLayout();
	final TextField userName = new TextField("Username : ");
	final PasswordField password = new PasswordField("Password : ", "");


	Button logIn = new Button("Log in");
	
	Button create = new Button("Sign Up");
	Button forgot = new Button("Forgot Password");

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		try {
			DBConnection dbc = new DBConnection();
			Notification.show(dbc.readDB());
		} catch (ClassNotFoundException | JSchException | SQLException e1) {
		}
		logIn.setStyleName(ValoTheme.BUTTON_BORDERLESS);
		setContent(layout);  
		userName.setRequiredIndicatorVisible(isVisible());
		password.setRequiredIndicatorVisible(isVisible());
		final Panel panel = new Panel ();
		layout.setSizeFull();
		layout.addComponent(panel);
		panel.setWidth(null);
		layout.setComponentAlignment(panel,Alignment.MIDDLE_CENTER );		
		final FormLayout formlayout = new FormLayout();
		formlayout.setMargin(true);
		formlayout.setStyleName("loginForm");
		formlayout.addComponent(userName);
		formlayout.addComponent(password);
		formlayout.addComponent(logIn);
		formlayout.addComponent(create);
		formlayout.addComponent(forgot);
		logIn.addClickListener(log -> {
			formlayout.addComponent(new Label("Thanks " + userName.getValue() + password.getValue()
			+ ", it works!"));
		});
		panel.setContent(formlayout);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}


}
