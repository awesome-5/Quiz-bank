package com.example.Quiz_Bank;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class CreateTestView extends VerticalLayout implements View {
	Label test = new Label("This is the Drag and Drop Page");
	 HorizontalLayout hl = new HorizontalLayout();

	public void enter(ViewChangeEvent event) {
		hl.addComponent(test);
		addComponent(hl);
	}

	
}
