package com.example.app;

import java.awt.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import com.jcraft.jsch.JSchException;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.ItemClickListener;
import com.vaadin.ui.components.grid.SingleSelectionModel;
import com.vaadin.ui.themes.ValoTheme;

@PushStateNavigation
public class HomePage extends VerticalLayout implements View {
	HorizontalLayout mainLayout = new HorizontalLayout();
	VerticalLayout vl = new VerticalLayout();
	Label label = new Label(null);
	TextField addCourse = new TextField();
	Button createCourse = new Button("Create Course");	
	Button logOut = new Button("Questions");
	Grid<course> grid = new Grid<>(course.class);
	static String CurrentCourse="";

	//reads all the courses that link to the user that is currently logged in
	public void updateGrid() {
		try {
			MyUI myObj = new MyUI();
			DBConnection dbc = new DBConnection();
			dbc.readDBCourse("SELECT courseCode FROM Course WHERE username='"+LoginView.loggedInUser+"'");
			grid.setItems(dbc.courseObj);

		} catch (ClassNotFoundException | JSchException | SQLException e1) {
		}
	}
	@Override
	public void enter(ViewChangeEvent event) {

		updateGrid();

		grid.addItemClickListener(e ->
		{
			Notification.show("Value: " + e.getItem().getcourseCode());
			CurrentCourse=e.getItem().getcourseCode();
			MyUI.navigator.navigateTo(MyUI.MAINVIEW);
		});


		createCourse.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		logOut.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		Page.getCurrent().setTitle("Home Page");	
		vl.addComponents(addCourse,createCourse,logOut);
		mainLayout.addComponents(grid,vl);
		//mainLayout.setSizeFull();
		addComponent(mainLayout);  



		createCourse.addClickListener(e -> {
			try {
				DBConnection dbca = new DBConnection();
				dbca.postDB("INSERT INTO Course VALUES('"+LoginView.loggedInUser + "','" + addCourse.getValue() + "')" );
				Notification.show("Course added");
			} catch (ClassNotFoundException | JSchException | SQLException e1) {
				Notification.show("Course not added");
			}
			addCourse.setValue("");
			updateGrid();
		});


		logOut.addClickListener(e -> {
			MyUI.navigator.navigateTo(MyUI.MAINVIEW);
		});
	}	
}

