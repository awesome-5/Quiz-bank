package com.example.app;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.jcraft.jsch.JSchException;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class MainView extends VerticalLayout implements View {

	private static QuestionService service = QuestionService.getInstance();
	private static Grid<Question> grid = new Grid<>(Question.class);
	private static TextField filterText = new TextField();
	private QuestionForm form = new QuestionForm(this);
	private static HorizontalLayout toolbar = new HorizontalLayout();


	@Override
	public void enter(ViewChangeEvent event) {

		//main layout
		final VerticalLayout layout = new VerticalLayout();

		//setting filter
		filterText.setPlaceholder("Filter...");
		filterText.addValueChangeListener(e -> updateList());
		filterText.setValueChangeMode(ValueChangeMode.LAZY);

		Button clearFilterTextBtn = new Button();
		clearFilterTextBtn.setIcon(VaadinIcons.CLOSE);
		clearFilterTextBtn.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
		clearFilterTextBtn.setDescription("Clear the current filter");
		clearFilterTextBtn.addClickListener(e -> filterText.clear());

		CssLayout filtering = new CssLayout();
		filtering.addComponents(filterText, clearFilterTextBtn);
		filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

		//add new question button
		Button addQuestionBtn = new Button("Add new question");

		// toolbar (above the grid)    
		toolbar.addComponents(filtering, addQuestionBtn);

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

		//formatting of the grid
		grid.setColumns("id","questionText","questionAnswer","type", "marks", "difficulty","time","lastUsed","variantOf","courseCode");
		grid.getColumn("questionText").setMaximumWidth(250.0);
		grid.getColumn("questionAnswer").setMaximumWidth(250.0);

		HorizontalLayout main = new HorizontalLayout(grid, form);
		main.setSizeFull();
		grid.setSizeFull();
		form.setVisible(false);
		main.setExpandRatio(grid, 0);

		//adding all components to the main layout
		layout.addComponents(toolbar, main);

		// fetch list of Customers from service and assign it to Grid
		updateList();

		addComponent(layout);

//		 when selecting an element on the grid, fills the question form with the fields of the question 
		grid.asSingleSelect().addValueChangeListener(e -> {
			if (e.getValue() == null) {
				form.setVisible(false);
			} else {
				try {
					grid.setVisible(false);
					toolbar.setVisible(false);
					form.setQuestion(e.getValue());
				} catch (ClassNotFoundException | JSchException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public static void updateList() {
		List<Question> questions = service.findAll(filterText.getValue());
		grid.setItems(questions);
		grid.setVisible(true);
		toolbar.setVisible(true);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}


}

