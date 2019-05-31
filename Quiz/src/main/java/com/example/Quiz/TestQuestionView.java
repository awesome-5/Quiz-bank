package com.example.Quiz;
import java.awt.Checkbox;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.GridDragSource;
import com.vaadin.ui.components.grid.GridDropTarget;
import com.vaadin.ui.components.grid.GridRowDragger;
import com.vaadin.ui.components.grid.TargetDataProviderUpdater;
import com.vaadin.ui.dnd.DropTargetExtension;
import com.vaadin.ui.themes.ValoTheme;

public class TestQuestionView extends VerticalLayout implements View {
	
	VerticalLayout mainLayout =new VerticalLayout();
	Grid<Question> Grid = new Grid<>(Question.class);
	Button back = new Button("Back");
	String[] ids;
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

	@Override
	public void enter(ViewChangeEvent event) {
		qTestObj.clear();
		ids=getIDS("SELECT questionIDS FROM Quiz WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND quizName='"+TestView.currentDraftQuiz+"'").split(",");
		
		for (int i=0;i<ids.length;i++)
		{
			System.out.print("id is " +ids[i]);
			qTestObj.add(getQuestions("SELECT * FROM Question WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND questionID='"+ids[i]+"'"));
			System.out.println("Q test obj"+qTestObj);
		}
		Grid.setColumns("id","questionText","questionAnswer","type", "marks", "difficulty","time","lastUsed","variantOf","courseCode");
		Grid.setItems(qTestObj);
		Grid.setSizeFull();
		Grid.setBodyRowHeight(50);
		Grid.setHeightMode(HeightMode.ROW);
		mainLayout.setSizeFull();
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		Page.getCurrent().setTitle("View Paper");
		mainLayout.addComponents(Grid,back);
		addComponent(mainLayout);

		
		back.addClickListener(e->
		{
			MyUI.navigator.navigateTo(MyUI.TESTVIEW);

		});
	}
	
	
}
