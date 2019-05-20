package com.example.Quiz;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.vaadin.server.UserError;
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

public class ResetPasswordView extends VerticalLayout implements View 
{
	final VerticalLayout layout = new VerticalLayout();
	final TextField temp = new TextField("Temporary Password: ");
	final TextField newPass = new TextField("New Password: ");
	final TextField newRepeat = new TextField("Re-enter New Password: ");
	final TextField email = new TextField("E-mail: ");
	HorizontalLayout hl=new HorizontalLayout();
	final Panel panel = new Panel ();
	Button reset = new Button("Reset");
	Label label = new Label();
	final FormLayout formlayout = new FormLayout();
	Timestamp em;
	static boolean blank=true;


	@Override
	public void enter(ViewChangeEvent event) {
		reset.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		addComponent(layout);  	
		panel.setWidth(null);
		layout.setSizeFull();
		layout.addComponent(panel);
		layout.setComponentAlignment(panel,Alignment.MIDDLE_CENTER );		
		formlayout.setMargin(true);
		formlayout.addComponents(email,temp,newPass,newRepeat,reset,label);
		panel.setContent(formlayout);
		Timestamp now = new Timestamp(System.currentTimeMillis());
		reset.setClickShortcut(KeyCode.ENTER);
		email.focus();

		reset.addClickListener(log -> {
			blank=false;
			checkField(email);
			checkField(temp);
			checkField(newPass);
			checkField(newRepeat);
			if(!newPass.getValue().equals(newRepeat.getValue()))		// checks if passwords match
			{
				newRepeat.setComponentError(new UserError("Passwords do not match"));
				blank=true;

			}
			else if(blank==false)
			{
				try {
					DBConnection dbc = new DBConnection();
					em=dbc.readReset("SELECT timestamp from Reset WHERE email='"+email.getValue()+"' AND ID='"+temp.getValue()+"'");
					System.out.println("current time is"+em);
				} catch (ClassNotFoundException | JSchException | SQLException e1) {
				}
				if (em==null)
				{
					label.setValue("We couldn't find your account");
					System.out.println("We couldn't find your password reset request");
				}
				else
				{
					em.setTime(em.getTime() + 12*60*60*1000);
					if (now.after(em))
					{
						label.setValue("This token has expired");	
						System.out.println("This token has expired");
						deleteEntry();
					}
					else
					{
						try {
							resetPassword();
							label.setValue("Password reset");	
							System.out.println("Password reset");
						} catch (ClassNotFoundException | JSchException | SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});



	}
	public void resetPassword() throws ClassNotFoundException, JSchException, SQLException
	{
		DBConnection dbc = new DBConnection();
		dbc.postDB("UPDATE User SET password='"+newPass.getValue()+"' WHERE email='"+email.getValue()+"'");
		deleteEntry();
		System.out.println("reset Password method");
	}
	public void deleteEntry()
	{
		try {
			DBConnection dbc = new DBConnection();
			dbc.deleteID("DELETE FROM Reset WHERE email='"+email.getValue()+"'");
			System.out.println("delete entry method");

		} catch (ClassNotFoundException | JSchException | SQLException e1) {
		}
	}

	public void checkField(TextField tf)
	{
		if(tf.getValue().isEmpty())
		{
			tf.setComponentError(new UserError("Cannot be blank"));
			blank=true;
		}
		tf.addFocusListener(new FocusListener()
		{

			@Override
			public void focus(FocusEvent event) {
				tf.setComponentError(null);

			}

		});
	}
}