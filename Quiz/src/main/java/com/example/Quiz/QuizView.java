package com.example.Quiz;

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

public class QuizView extends VerticalLayout implements View {

	HorizontalLayout mainLayout = new HorizontalLayout();
	public Label label = new Label(null);
	Grid<Course> gridFrom = new Grid<>(Course.class);
	Grid<Course> gridTo = new Grid<>(Course.class);
	DropTargetExtension<Grid> dropTarget = new DropTargetExtension<>(gridTo);
	static String CurrentCourse="";
	Boolean sameCourse = false;
	private List<Course> draggedItems;
	ArrayList<Course> addedObj = new ArrayList<Course>();

	//reads all the courses that link to the user that is currently logged in

	public void updateGrid() {
		try {
			DBConnection dbc = new DBConnection();
			dbc.readDBCourse("SELECT courseCode FROM Course WHERE username='"+LoginView.loggedInUser+"'");
			gridFrom.setItems(dbc.courseObj);
		} catch (ClassNotFoundException | JSchException | SQLException e1) {
		}
	}
	public TargetDataProviderUpdater<Course> updateNewGrid() {
		
		gridTo.setItems(addedObj);
		return null;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		updateGrid();
		gridFrom.setCaption("Question Bank");
		gridTo.setCaption("Selected Questions");
		gridTo.addStyleName(ValoTheme.LAYOUT_CARD);
		Page.getCurrent().setTitle("Home Page");	
		mainLayout.addComponents(gridFrom,gridTo);
		addComponent(mainLayout);  

		GridDragSource<Course> dragSource = new GridDragSource<>(gridFrom);
		GridDropTarget<Course> dropTarget = new GridDropTarget<>(gridTo, DropMode.ON_GRID);
		dragSource.setEffectAllowed(EffectAllowed.MOVE);
		dropTarget.setDropEffect(DropEffect.MOVE);

		GridRowDragger<Course> leftToRight = new GridRowDragger<>(gridFrom, gridTo);
		GridRowDragger<Course> rightToLeft = new GridRowDragger<>(gridTo, gridFrom);
		leftToRight.setTargetDataProviderUpdater(updateNewGrid());
		
		
		leftToRight.getGridDragSource().addDragStartListener(evnt -> rightToLeft.getGridDropTarget().setDropEffect(DropEffect.NONE));
		leftToRight.getGridDragSource().addDragEndListener(evet -> rightToLeft.getGridDropTarget().setDropEffect(null));
		rightToLeft.getGridDragSource().addDragStartListener(evnt -> leftToRight.getGridDropTarget().setDropEffect(DropEffect.NONE));
		rightToLeft.getGridDragSource().addDragEndListener(evet -> leftToRight.getGridDropTarget().setDropEffect(null));

		dragSource.addGridDragStartListener(eent ->
		// Keep reference to the dragged items
		draggedItems = eent.getDraggedItems()
				);

		// Add drag end listener
		dragSource.addGridDragEndListener(vent -> {
			// If drop was successful, remove dragged items from source Grid
			if (vent.getDropEffect() == DropEffect.MOVE) {
				gridFrom.getSelectedItems()
				.removeAll(draggedItems);
				gridFrom.getDataProvider().refreshAll();
				// Remove reference to dragged items
				draggedItems = null;
			}
		});

		

	}	
}



