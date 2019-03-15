package com.example.Quiz;

import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import com.jcraft.jsch.JSchException;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.navigator.View;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;


@Theme("mytheme")
public class MyUI extends UI implements View {

	private static final long serialVersionUID = 1L;
	//initialising components
	final VerticalLayout layout = new VerticalLayout();
	FormLayout fl= new FormLayout();
	HorizontalLayout h1 = new HorizontalLayout();
	HorizontalLayout h2 = new HorizontalLayout();
	HorizontalLayout h3 = new HorizontalLayout();
	HorizontalLayout h4 = new HorizontalLayout();

	final TextField firstName = new TextField("First Name : ");
	final TextField surName = new TextField("Last Name : ");
	final TextField email = new TextField("Email Address : ");
	final TextField userName = new TextField("Username : ");
	final PasswordField password = new PasswordField("Password : ");
	final PasswordField passwordRepeat = new PasswordField("Repeat Password : ");
	Button submit = new Button("Submit");
	static boolean blank=true;
	int checkUser=1;
	Panel panel = new Panel();
	TextField fieldEmailValidator = new TextField();

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		firstName.setRequiredIndicatorVisible(true);
		surName.setRequiredIndicatorVisible(true);
		email.setRequiredIndicatorVisible(true);
		userName.setRequiredIndicatorVisible(true);
		password.setRequiredIndicatorVisible(true);
		passwordRepeat.setRequiredIndicatorVisible(true);

		submit.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		setContent(layout);
		layout.setSizeFull();
		panel.setSizeUndefined();
		layout.addComponent(panel);
		layout.setComponentAlignment(panel,Alignment.MIDDLE_CENTER);		
		fl.setMargin(true);
		h1.addComponents(firstName,surName);
		h2.addComponents(password,passwordRepeat);
		h3.addComponents(email);
		h4.addComponents(userName);
		fl.addComponents(h1,h3,h4,h2,submit);
		panel.setContent(fl);


		submit.addClickListener(log -> {
			blank=false;
			checkField(firstName);
			checkField(surName);
			checkField(email);
			checkField(userName);
			checkField(password);
			checkField(passwordRepeat);

			if(!password.getValue().equals(passwordRepeat.getValue()))		// checks if passwords match
			{
				passwordRepeat.setComponentError(new UserError("Passwords do not match"));
				blank=true;

			}

			
			if(blank==false) // if all fields have values 
			{
				try {
					DBConnectionGet dbc = new DBConnectionGet();
					String s=dbc.readDB("SELECT * from User WHERE username='"+userName.getValue()+"'");
					System.out.println("s is "+s);
					System.out.println("username is "+userName.getValue());
					if (s.equals(userName.getValue()))
					{
						checkUser=1;
						userName.setComponentError(new UserError("Username taken"));
					}
					else {
						checkUser=0;
						System.out.println("check user is 0 so not taken");
					}
				} catch (ClassNotFoundException | JSchException | SQLException e1) 
				{
					Notification.show("Couldn't connect to database");
				}

				if (checkUser==0)
				{

					try {

						DBConnectionAdd dbca = new DBConnectionAdd();
						dbca.postDB("INSERT INTO User VALUES('"+userName.getValue() + "','" + password.getValue() + "','" + firstName.getValue() + "','" + surName.getValue() + "','" + email.getValue() + "')" );
						Notification.show("User added");
					} catch (ClassNotFoundException | JSchException | SQLException e1) {
						Notification.show("User not added");
					}
				}
			}
		});


	}


	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {

		private static final long serialVersionUID = 1L;
	}

	public static void checkField(TextField tf)
	{
		if(tf.getValue().isEmpty())
		{
			tf.setComponentError(new UserError("Cannot be blank"));
			blank=true;
		}
		tf.addFocusListener(new FocusListener()
		{

			private static final long serialVersionUID = 1L;

			@Override
			public void focus(FocusEvent event) {
				tf.setComponentError(null);

			}

		});
	}

}

