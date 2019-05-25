package com.example.Quiz;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.jcraft.jsch.JSchException;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.dnd.DropEffect;
import com.vaadin.shared.ui.dnd.EffectAllowed;
import com.vaadin.shared.ui.grid.DropLocation;
import com.vaadin.shared.ui.grid.DropMode;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.GridDragSource;
import com.vaadin.ui.components.grid.GridDropTarget;
import com.vaadin.ui.components.grid.GridRowDragger;
import com.vaadin.ui.components.grid.TargetDataProviderUpdater;
import com.vaadin.ui.dnd.DropTargetExtension;
import com.vaadin.ui.themes.ValoTheme;

public class DragView extends VerticalLayout implements View {
	VerticalLayout mainVertLayout = new VerticalLayout();
	HorizontalLayout mainLayout = new HorizontalLayout();
	HorizontalLayout botBar = new HorizontalLayout();
	Label displayLabel = new Label();
	Label label = new Label("Test Name: ");
	Button saveTest = new Button("Save As Test");	
	Button saveExam = new Button("Save As Exam");	
	//Button Display = new Button("Display");	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	LocalDate localDate = LocalDate.now();
	static TextField addQuiz = new TextField();
	Grid<Question> gridFrom = new Grid<>(Question.class);
	Grid<Question> gridTo = new Grid<>(Question.class);
	Label messageLabel = new Label();
	DropTargetExtension<Grid> dropTarget = new DropTargetExtension<>(gridTo);
	Button backBtn = new Button("Back");
	List<Question> draggedItems;
	List<Question> draggedItemsFrom ;
	List<Question> questionObj = new ArrayList<Question>();
	ArrayList<Question> blankObj = new ArrayList<Question>();
	static Button view = new Button("View Quizzes");

	public Boolean checkName(String name) {
		try {
			String temp ="";
			DBConnection dbc = new DBConnection();
			temp = dbc.readQuizName("SELECT quizName FROM Quiz WHERE quizName='"+addQuiz.getValue()+"'");
			//System.out.println(temp);
			if (temp==null){
				return false;
			} else if (temp.equals(name)){
				return true;
			}else {
				return false;
			}

		} catch (ClassNotFoundException | JSchException | SQLException e1) {
			return false;
		}
	}
	//reads all the questions that link to the user that is currently logged in

	public void updateGrid() {
		try {
			DBConnection dbc = new DBConnection();
			dbc.readDBQuestion();
			gridFrom.setItems(dbc.output);
		} catch (ClassNotFoundException | JSchException | SQLException e1) {
		}
	}
	public TargetDataProviderUpdater<Question> updateNewGrid() {
		gridTo.setItems(blankObj);
		return null;
	}
	public void postToDB(Boolean TestOrExam) {
		String QuestionIDPost = "";
		for (int i = 0 ; i<questionObj.size() ; i++) {
			QuestionIDPost+=""+questionObj.get(i).getId()+",";
		}
		QuestionIDPost=QuestionIDPost.substring(0, QuestionIDPost.length()-1);
		try {
			DBConnection dbca = new DBConnection();
			dbca.postDB("INSERT INTO Quiz VALUES('"+ HomePage.CurrentCourse + "','" + LoginView.loggedInUser + "'," + "NULL" + ",'" + QuestionIDPost + "'," + 0 + ","+"NULL"+","+ TestOrExam +",'"+ addQuiz.getValue() +"')" );
		} catch (ClassNotFoundException | JSchException | SQLException e1) {
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		updateGrid();
		label.addStyleName("large");
		gridFrom.setCaption("Question Bank");
		gridTo.setCaption("Selected Questions");
		//gridTo.addStyleName(ValoTheme.LAYOUT_CARD);
		Page.getCurrent().setTitle("Create Quiz Page");	
		gridFrom.setColumns("questionText","questionAnswer","type", "marks", "difficulty","time","lastUsed","variantOf");
		gridTo.setColumns("questionText","questionAnswer","type", "marks", "difficulty","time","lastUsed","variantOf");
		mainVertLayout.setSizeFull();
		mainVertLayout.setMargin(false);
		mainLayout.setMargin(false);
		gridFrom.setSizeFull();
		gridTo.setSizeFull();
		mainLayout.setSizeFull();
		gridFrom.setBodyRowHeight(60);
		gridFrom.setHeightMode(HeightMode.ROW);
		gridTo.setBodyRowHeight(60);
		gridTo.setHeightMode(HeightMode.ROW);
		botBar.addComponents(label,addQuiz,saveTest,saveExam,view,backBtn);
		botBar.setMargin(false);
		mainLayout.addComponents(gridFrom,gridTo);
		mainVertLayout.addComponents(mainLayout,botBar,displayLabel);
		addComponents(mainVertLayout);  
		addQuiz.setValue(""+dtf.format(localDate)+"_"+HomePage.CurrentCourse);
		addQuiz.setWidth("15em");
		saveTest.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		saveExam.setStyleName(ValoTheme.BUTTON_FRIENDLY);

		GridDragSource<Question> dragSource = new GridDragSource<>(gridFrom);
		GridDropTarget<Question> dropTarget = new GridDropTarget<>(gridTo, DropMode.ON_GRID);
		GridDragSource<Question> dragSource2 = new GridDragSource<>(gridTo);

		dragSource.setEffectAllowed(EffectAllowed.MOVE);
		dropTarget.setDropEffect(DropEffect.MOVE);

		GridRowDragger<Question> leftToRight = new GridRowDragger<>(gridFrom, gridTo);
		GridRowDragger<Question> rightToLeft = new GridRowDragger<>(gridTo, gridFrom);
		leftToRight.setTargetDataProviderUpdater(updateNewGrid());


		leftToRight.getGridDragSource().addDragStartListener(evnt -> {rightToLeft.getGridDropTarget().setDropEffect(DropEffect.NONE);
		//System.out.println("1");
		});
		leftToRight.getGridDragSource().addDragEndListener(evet -> {rightToLeft.getGridDropTarget().setDropEffect(null);
		//System.out.println("2");
		});
		rightToLeft.getGridDragSource().addDragStartListener(evnt -> {leftToRight.getGridDropTarget().setDropEffect(DropEffect.NONE);
		//	System.out.println("3");
		});
		rightToLeft.getGridDragSource().addDragEndListener(evet -> {leftToRight.getGridDropTarget().setDropEffect(null);
		for (int i =0;i<questionObj.size();i++) 
		{ 
			//System.out.println("GRID FROM :::::---::::: "+draggedItemsFrom.toString());
			if (questionObj.get(i) == draggedItemsFrom.get(0)) 
				questionObj.remove(i); 
		} 		
		//	System.out.println("4");
		});
		dragSource.addGridDragStartListener(eent ->{
			// Keep reference to the dragged items
			draggedItems = eent.getDraggedItems();
		});
		dragSource2.addGridDragStartListener(t ->{
			// Keep reference to the dragged items
			draggedItemsFrom = t.getDraggedItems();
		});


		// Add drag end listener
		dragSource.addGridDragEndListener(vent -> {
			// If drop was successful, remove dragged items from source Grid
			if (vent.getDropEffect() == DropEffect.MOVE) {
				gridFrom.getSelectedItems()
				.removeAll(draggedItems);
				gridFrom.getDataProvider().refreshAll();
				// Remove reference to dragged items
				//System.out.println("GRID TO :::::---::::: "+draggedItems.get(0).toString());
				questionObj.add(draggedItems.get(0));
				draggedItems = null;
			}
		});

		backBtn.addClickListener(e -> {
			Page.getCurrent().reload();			
			MyUI.navigator.navigateTo(MyUI.GRIDVIEW);
		});

		view.addClickListener(e -> {
			Page.getCurrent().reload();			
			MyUI.navigator.navigateTo(MyUI.TESTVIEW);
		});

		saveTest.addClickListener(e -> {
			if (checkName(addQuiz.getValue())&&questionObj.size()==0) {
				Notification.show("Please change the name of your test and add some questions to your test");
				//System.out.println(questionObj.size());
			}else if (checkName(addQuiz.getValue())&&questionObj.size()>0) {
				Notification.show("Please change the name of your test");
				//System.out.println(questionObj.size());
			}else if(!checkName(addQuiz.getValue()) && questionObj.size()==0) {
				Notification.show("Please add some questions to your test");
			}
			else  {
				postToDB(false);
				Notification.show("Test Saved");
				Page.getCurrent().reload();			
				MyUI.navigator.navigateTo(MyUI.TESTVIEW);
			}
		});
		saveExam.addClickListener(e -> {
			if (checkName(addQuiz.getValue())&&questionObj.size()==0) {
				Notification.show("Please enter another name and add some questions to your test");
				//System.out.println(questionObj.size());
			}else if (checkName(addQuiz.getValue())&&questionObj.size()>0) {
				Notification.show("Please change the name of your test");
				//System.out.println(questionObj.size());
			}else if(!checkName(addQuiz.getValue()) && questionObj.size()==0) {
				Notification.show("Please add some questions to your test");
			}
			else  {
				postToDB(true);
				Notification.show("Exam Saved");
				Page.getCurrent().reload();			
				MyUI.navigator.navigateTo(MyUI.TESTVIEW);
			}
		});
	}	
}



