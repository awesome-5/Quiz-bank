package com.example.Quiz;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import com.jcraft.jsch.JSchException;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class QuestionGridView extends VerticalLayout implements View {

	static Grid<Question> grid = new Grid<>(Question.class);
	static TextField filterText = new TextField();
	QuestionForm form = new QuestionForm(this);
	static HorizontalLayout toolbar = new HorizontalLayout();
	//main layout
	VerticalLayout layout = new VerticalLayout();
	//add new question button
	static Button addQuestionBtn = new Button("Add new question");
	static Button clearFilterTextBtn = new Button();
	static Button newTest = new Button("Create Test");
	static Button back = new Button ("Back");
	HorizontalLayout main = new HorizontalLayout();
	static CssLayout filtering = new CssLayout();
	QuestionService service = QuestionService.getInstance();
	static Long CurrentId=null;

	@Override
	public void enter(ViewChangeEvent event) {
		Page.getCurrent().setTitle("Question Grid");	
		CurrentId=null;
		//setting filter
		filterText.setPlaceholder("Filter...");
		filterText.addValueChangeListener(e -> updateList());
		filterText.setValueChangeMode(ValueChangeMode.LAZY);

		clearFilterTextBtn.setIcon(VaadinIcons.CLOSE);
		clearFilterTextBtn.setStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
		clearFilterTextBtn.setDescription("Clear the current filter");
		clearFilterTextBtn.addClickListener(e -> filterText.clear());

		filtering.addComponents(filterText, clearFilterTextBtn);
		filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

		// toolbar (above the grid)    
		toolbar.addComponents(filtering, addQuestionBtn,newTest, back);		
		addQuestionBtn.setStyleName(ValoTheme.BUTTON_PRIMARY);

		addQuestionBtn.addClickListener(e -> {
			grid.asSingleSelect().clear();
			try {
				grid.setVisible(false);
				toolbar.setVisible(false);		
				form.setQuestion(new Question());
			} catch (ClassNotFoundException | JSchException | SQLException e1) {

				e1.printStackTrace();
			}
		});
		
		back.addClickListener(log -> {
			MyUI.navigator.navigateTo(MyUI.HOMEPAGE);
		});
		
		
		//formatting of the grid
		grid.setColumns("id","questionText","questionAnswer","type", "marks", "difficulty","time","lastUsed","variantOf","courseCode");
		grid.getColumn("questionText").setMaximumWidth(250.0);
		grid.getColumn("questionAnswer").setMaximumWidth(250.0);
				

		main.addComponents(grid, form);
		main.setSizeFull();
		grid.setSizeFull();
		form.setVisible(false);
		grid.setBodyRowHeight(65);
		grid.setHeightMode(HeightMode.ROW);
		layout.setSizeFull();
		layout.setMargin(false);
		//adding all components to the main layout
		layout.addComponents(toolbar, main);
		layout.setComponentAlignment(toolbar,Alignment.MIDDLE_CENTER );

		// fetch list of Customers from service and assign it to Grid
		updateList();
		System.out.println("List Updated");
		addComponent(layout);
		
		//when selecting an element on the grid, fills the question form with the fields of the question 
		grid.asSingleSelect().addValueChangeListener(e -> {
			if (e.getValue() == null) {
				form.setVisible(false);
			} else {
				try {
					CurrentId= e.getValue().getId();
					System.out.println("id of mother question"+CurrentId);
					grid.setVisible(false);
					toolbar.setVisible(false);
					form.setQuestion(e.getValue());
				} catch (ClassNotFoundException | JSchException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		newTest.addClickListener(e -> {
			MyUI.navigator.navigateTo(MyUI.DRAGVIEW);
			
		});
	}

	public static void updateList() {
		List<Question> questions = new QuestionGridView().service.findAll(filterText.getValue());
		grid.setItems(questions);
		grid.setVisible(true);
		toolbar.setVisible(true);

	}



}

