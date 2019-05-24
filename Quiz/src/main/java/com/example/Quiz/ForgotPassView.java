package com.example.Quiz;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class ForgotPassView extends VerticalLayout implements View {
	final VerticalLayout layout = new VerticalLayout();
	final TextField email = new TextField("Email : ");

	HorizontalLayout hl=new HorizontalLayout();
	Button  Submit = new Button("Submit");
	Button back = new Button("back");
	Label label = new Label(null);


	@Override
	public void enter(ViewChangeEvent event) {
		//add elements to display and style them
		Submit.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		back.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		email.setRequiredIndicatorVisible(isVisible());
		
		addComponent(layout);  	
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
				String s=dbc.readDBUser("SELECT * FROM User WHERE username='"+email.getValue()+"'"); 
				if (s.length()> 1)
				{
					//TODO send link to the email to reset password 
					label.setValue("Email sent, please check your email");
					
				}
				else {
					label.setValue("Email doesn't exist, please register");
				}
			} catch (Exception e) {
			}
		});
		
		back.addClickListener(log -> {
			MyUI.navigator.navigateTo(MyUI.LOGINVIEW);
		});
	}
}
