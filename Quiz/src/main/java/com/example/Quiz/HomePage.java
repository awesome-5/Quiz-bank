package com.example.Quiz;

import java.sql.SQLException;
import com.jcraft.jsch.JSchException;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.grid.HeightMode;
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
	TextField tf = new TextField();
	VerticalLayout mainLayout = new VerticalLayout();
	VerticalLayout topBar = new VerticalLayout();
	HorizontalLayout botBar = new HorizontalLayout();
	HorizontalLayout topTop = new HorizontalLayout();
	Label label = new Label();
	Label labelInfo = new Label();
	TextField addCourseCode = new TextField("Please Enter Course Code");
	TextField addCourseName = new TextField("Please Enter Course Name");
	Button selectCourse = new Button("Select Course");
	Button editCourse = new Button("Edit Course");
	Button deleteCourse = new Button("Delete Course");
	Button createCourse = new Button("Create Course");	
	Grid<Course> grid = new Grid<>(Course.class);
	static String CurrentCourse="";
	static String CurrentCourseName="";
	Boolean sameCourse = false;

	//reads all the courses that link to the user that is currently logged in

	public void updateGrid() {
		try {
			DBConnection dbc = new DBConnection();
			dbc.readDBCourse("SELECT courseCode,courseName FROM Course WHERE username='"+LoginView.loggedInUser+"'");
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
		selectCourse.setEnabled(false);
		editCourse.setEnabled(false);
		deleteCourse.setEnabled(false);
		createCourse.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		Page.getCurrent().setTitle("Home Page");
		topTop.addComponents(addCourseCode,addCourseName,createCourse);
		topTop.setComponentAlignment(addCourseCode, Alignment.BOTTOM_CENTER);
		topTop.setComponentAlignment(addCourseName, Alignment.BOTTOM_CENTER);
		topTop.setComponentAlignment(createCourse, Alignment.BOTTOM_CENTER);
		label.addStyleName("large");
		labelInfo.addStyleName("large");
		topBar.addComponents(topTop,label);
		topBar.setComponentAlignment(topTop, Alignment.MIDDLE_CENTER);
		topBar.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
		topBar.setMargin(false);
		grid.setSizeFull();
		grid.setColumns("courseCode","courseName");
		botBar.addComponents(selectCourse,editCourse,deleteCourse);
		mainLayout.addComponents(topBar,grid,botBar,labelInfo);
		mainLayout.setSizeFull();
		mainLayout.setMargin(false);
		mainLayout.setComponentAlignment(topBar,Alignment.TOP_CENTER );			
		addComponent(mainLayout);  
		grid.setBodyRowHeight(50);
		grid.setHeightMode(HeightMode.ROW);
		addCourseCode.setValue("");
		addCourseName.setValue("");
		//grid.getEditor().setEnabled(true);
		
		
		grid.addItemClickListener(e ->
		{
			//Notification.show("Value: " + e.getItem().getcourseCode());
			CurrentCourse=e.getItem().getcourseCode();
			CurrentCourseName=e.getItem().getCourseName();
			System.out.println("Home "+ CurrentCourse );
		});
		
		
		grid.addSelectionListener(e ->
		{

			if (grid.getSelectedItems().size()==0)
			{
				selectCourse.setEnabled(false);
				editCourse.setEnabled(false);
				deleteCourse.setEnabled(false);
				labelInfo.setValue("");


			}
			else {
				selectCourse.setEnabled(true);
				editCourse.setEnabled(true);
				deleteCourse.setEnabled(true);
				labelInfo.setValue("PLease use above fields and click Edit to update an existing course");


			}


		});

		createCourse.addClickListener(e -> {
			try {
				updateGrid(addCourseCode.getValue());
				if (!addCourseCode.getValue().equals("") && sameCourse==false ) {
					DBConnection dbca = new DBConnection();
					dbca.postDB("INSERT INTO Course VALUES('"+LoginView.loggedInUser + "','" + addCourseCode.getValue() + "','"+ addCourseName.getValue() +"')" );
					label.setValue("Course added");
				}else if (addCourseCode.getValue().equals("")){
					label.setValue("Cannot add an empty course");
				}else {
					label.setValue("Cannot add an existing course");

				}
			} catch (ClassNotFoundException | JSchException | SQLException e1) {
				label.setValue("Course not added");
			}
			addCourseCode.setValue("");
			addCourseName.setValue("");
			updateGrid();
			sameCourse = false;
		});
		
		
		selectCourse.addClickListener(e -> {
			MyUI.navigator.navigateTo(MyUI.GRIDVIEW);
		});
		
		editCourse.addClickListener(e -> {
			DBConnection dbc = new DBConnection();
			try {
				if (addCourseCode.getValue()=="") {
				dbc.postDB("UPDATE Course\n"
						+ "SET courseCode = '" + CurrentCourse + "', courseName = '"+ addCourseName.getValue() +"'\n"
								+ "WHERE courseCode = '"+ CurrentCourse +"'"
										+ "AND username = '"+ LoginView.loggedInUser +"'");
				}else if (addCourseName.getValue()=="") {
					dbc.postDB("UPDATE Course\n"
							+ "SET courseCode = '" + addCourseCode.getValue() + "', courseName = '"+ CurrentCourseName +"'\n"
									+ "WHERE courseCode = '"+ CurrentCourse +"'"
											+ "AND username = '"+ LoginView.loggedInUser +"'");
				}else {
					
				}
				updateGrid();
			} catch (ClassNotFoundException | JSchException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			addCourseCode.setValue("");
			addCourseName.setValue("");
	
		});

		deleteCourse.addClickListener(e->
		{
			System.out.println("hey");
			try {
				DBConnection delete = new DBConnection(); 
				delete.postDB("DELETE FROM Course\n"
						+ "WHERE courseCode = '"+ CurrentCourse +"'"
						+ "AND username = '"+ LoginView.loggedInUser +"'");
				updateGrid();
			} catch (ClassNotFoundException | JSchException | SQLException e1) {
			}

		});
		updateGrid();
		

		createCourse.setClickShortcut(KeyCode.ENTER);

	}	
}



