package com.example.Quiz;

import java.sql.SQLException;
import com.jcraft.jsch.JSchException;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@PushStateNavigation
public class HomePage extends VerticalLayout implements View {
	VerticalLayout mainLayout = new VerticalLayout();
	VerticalLayout topBar = new VerticalLayout();
	HorizontalLayout top = new HorizontalLayout();
	Label label = new Label();
	TextField addCourse = new TextField("Please Enter Course Code");
	Button createCourse = new Button("Create Course");	
	Grid<Course> grid = new Grid<>(Course.class);
	static String CurrentCourse="";
	Boolean sameCourse = false;

	//reads all the courses that link to the user that is currently logged in

	public void updateGrid() {
		try {
			DBConnection dbc = new DBConnection();
			dbc.readDBCourse("SELECT courseCode FROM Course WHERE username='"+LoginView.loggedInUser+"'");
			grid.setItems(dbc.courseObj);
		} catch (ClassNotFoundException | JSchException | SQLException e1) {
		}
	}

	//check if the course already exists
	public void updateGrid(String code) {
		try {
			DBConnection dbc = new DBConnection();
			dbc.readDBCourse("SELECT courseCode FROM Course WHERE username='"+LoginView.loggedInUser+"'");
			grid.setItems(dbc.courseObj);
			for (int i =0; i<dbc.courseObj.size();i++) {
				//System.out.println(dbc.courseObj.get(i).getcourseCode());
				if (dbc.courseObj.get(i).getcourseCode().equalsIgnoreCase(code)) {
					sameCourse=true;
				}
			}
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
			System.out.println("Home "+ CurrentCourse );
			MyUI.navigator.navigateTo(MyUI.GRIDVIEW);
		});

		createCourse.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		Page.getCurrent().setTitle("Home Page");
		topBar.addComponents(addCourse, createCourse, label);
		top.addComponent(topBar);
		grid.setSizeFull();
		mainLayout.addComponents(top,grid);
		mainLayout.setSizeFull();
		
		mainLayout.setComponentAlignment(top,Alignment.TOP_CENTER );			
		addComponent(mainLayout);  
		

		createCourse.addClickListener(e -> {
			try {
				updateGrid(addCourse.getValue());
				if (!addCourse.getValue().equals("") && sameCourse==false ) {
					DBConnection dbca = new DBConnection();
					dbca.postDB("INSERT INTO Course VALUES('"+LoginView.loggedInUser + "','" + addCourse.getValue() + "')" );
					label.setValue("Course added");
				}else if (addCourse.getValue().equals("")){
					label.setValue("Cannot add an empty course");
				}else {
					label.setValue("Cannot add an existing course");

				}
			} catch (ClassNotFoundException | JSchException | SQLException e1) {
				label.setValue("Course not added");
			}
			addCourse.setValue("");
			updateGrid();
			sameCourse = false;
		});

		createCourse.setClickShortcut(KeyCode.ENTER);

	}	
}



