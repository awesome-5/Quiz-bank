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

public class EditDragView extends VerticalLayout implements View {
	VerticalLayout mainVertLayout = new VerticalLayout();
	HorizontalLayout mainLayout = new HorizontalLayout();
	HorizontalLayout botBar = new HorizontalLayout();
	Grid<Question> gridFrom = new Grid<>(Question.class);
	Grid<Question> gridTo = new Grid<>(Question.class);
	DropTargetExtension<Grid> dropTarget = new DropTargetExtension<>(gridTo);
	Button backBtn = new Button("Back");
	Button saveTest = new Button("Update");	
	List<Question> draggedItems;
	List<Question> draggedItemsFrom ;
	List<Question> questionObj = new ArrayList<Question>();
	ArrayList<Question> blankObj = new ArrayList<Question>();
	ArrayList<String> idss=new ArrayList<String> ();
	ArrayList<Question> qTestObj=new ArrayList<Question>();



	public static String getIDS(String s) {
		String ret=null;
		try {
			DBConnection dbcGetIds = new DBConnection(); 
			ret=dbcGetIds.getIDS(s);
		} catch (ClassNotFoundException | JSchException | SQLException e1) {
		}
		return ret;
	}

	public static Question getQuestions(String s) {
		Question q = new Question();
		try {
			DBConnection dbcQuestionTest = new DBConnection(); 
			q= dbcQuestionTest.readQuestionsTest(s);

		} catch (ClassNotFoundException | JSchException | SQLException e1) {
		}
		return q;
	}



	void updateFromGrid() {
		try {
			DBConnection dbc = new DBConnection();
			dbc.readDBQuestion();
			ArrayList<Question> n = dbc.output;
			n.removeAll(qTestObj);
			gridFrom.setItems(n);
		} catch (ClassNotFoundException | JSchException | SQLException e1) {
		}
	}
	public TargetDataProviderUpdater<Question> updateNewGrid() {
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
			System.out.println("QuestionIDPost is "+QuestionIDPost);
			dbca.postDB("UPDATE Quiz SET questionIDS='" +QuestionIDPost+ "' WHERE quizName='"+TestView.currentDraftQuiz+"'");
		} catch (ClassNotFoundException | JSchException | SQLException e1) {
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		String[] s;
		s = (getIDS("SELECT questionIDS FROM Quiz WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND quizName='"+TestView.currentDraftQuiz+"'").split(","));
		for (int i=0;i<s.length;i++)
		{
			idss.add(s[i]);
		}
		for (int i=0;i<idss.size();i++)
		{
			System.out.println("idss is"+idss);
			qTestObj.add(getQuestions("SELECT * FROM Question WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND questionID='"+idss.get(i)+"'"));
			System.out.println("Q test obj"+qTestObj);
		}
		updateFromGrid();
		gridTo.setItems(qTestObj);
		questionObj.addAll(qTestObj);
		gridFrom.setCaption("Question Bank");
		gridTo.setCaption("Selected Questions");
		Page.getCurrent().setTitle("Edit Quiz Page");	
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
		botBar.addComponents(backBtn,saveTest);
		botBar.setMargin(false);
		mainLayout.addComponents(gridFrom,gridTo);
		mainVertLayout.addComponents(mainLayout,botBar);
		addComponents(mainVertLayout);  
		saveTest.setStyleName(ValoTheme.BUTTON_FRIENDLY);

		GridDragSource<Question> dragSource = new GridDragSource<>(gridFrom);
		GridDropTarget<Question> dropTarget = new GridDropTarget<>(gridTo, DropMode.ON_GRID);
		GridDragSource<Question> dragSource2 = new GridDragSource<>(gridTo);

		dragSource.setEffectAllowed(EffectAllowed.MOVE);
		dropTarget.setDropEffect(DropEffect.MOVE);

		GridRowDragger<Question> leftToRight = new GridRowDragger<>(gridFrom, gridTo);
		GridRowDragger<Question> rightToLeft = new GridRowDragger<>(gridTo, gridFrom);
		leftToRight.setTargetDataProviderUpdater(updateNewGrid());


		leftToRight.getGridDragSource().addDragStartListener(evnt -> {rightToLeft.getGridDropTarget().setDropEffect(DropEffect.NONE);
		});
		leftToRight.getGridDragSource().addDragEndListener(evet -> {rightToLeft.getGridDropTarget().setDropEffect(null);
		});
		rightToLeft.getGridDragSource().addDragStartListener(evnt -> {leftToRight.getGridDropTarget().setDropEffect(DropEffect.NONE);
		});
		rightToLeft.getGridDragSource().addDragEndListener(evet -> {leftToRight.getGridDropTarget().setDropEffect(null);
		for (int i =0;i<questionObj.size();i++) 
		{ 
			if (questionObj.get(i) == draggedItemsFrom.get(0)) 
				questionObj.remove(i); 
		} 		
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
				questionObj.add(draggedItems.get(0));
				draggedItems = null;
			}
		});

		backBtn.addClickListener(e -> {
			Page.getCurrent().reload();			
			MyUI.navigator.navigateTo(MyUI.TESTVIEW);
		});



		saveTest.addClickListener(e -> {
			//postToDB();
			System.out.println("Changes Saved");
			Page.getCurrent().reload();			
			MyUI.navigator.navigateTo(MyUI.TESTVIEW);
		});

	}	
}



