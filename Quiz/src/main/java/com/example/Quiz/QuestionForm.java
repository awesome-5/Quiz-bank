package com.example.Quiz;

import java.sql.SQLException;
import java.util.ArrayList;
import com.jcraft.jsch.JSchException;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.event.selection.SingleSelectionEvent;
import com.vaadin.event.selection.SingleSelectionListener;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

public class QuestionForm extends FormLayout {

	private TextArea questionText = new TextArea("Question Text");
	private TextArea questionAnswer = new TextArea("Question Answer");
	private TextField marks = new TextField("Marks");
	private NativeSelect<QuestionDifficulty> difficulty = new NativeSelect<>("Question Difficulty");
	private TextField time=new TextField("Time");
	private NativeSelect<Question>questions=new NativeSelect<>("Variant of");
	private NativeSelect<QuestionType> type = new NativeSelect<>("Question Type");
	private TextField line = new TextField("Lines");
	private TextField options = new TextField("MCQ Options");
	private Button save = new Button("Save");
	private Button delete = new Button("Delete");
	private Button back = new Button("Back");
	private Button saveVariant= new Button ("Save Variant");
	
	private Question question;
	private QuestionGridView myUI;
	private Binder<Question> binder = new Binder<>(Question.class);

	public QuestionForm(QuestionGridView gridView) {
		this.myUI = gridView;
		questionText.setRequiredIndicatorVisible(true);
		questionText.setWidth("100%");
		questionAnswer.setWidth("100%");
		marks.setRequiredIndicatorVisible(true);
		difficulty.setRequiredIndicatorVisible(true);
		questionAnswer.setRequiredIndicatorVisible(true);
		time.setRequiredIndicatorVisible(true);
		type.setRequiredIndicatorVisible(true);
		options.setVisible(false);
		line.setVisible(false);

		setSizeUndefined();
		
		HorizontalLayout buttons = new HorizontalLayout(save,saveVariant,delete, back);
		addComponents(questionText, marks, difficulty, time, questionAnswer, type, line, options, buttons);
		setWidth("100%");

		type.setItems(QuestionType.values());
		type.addValueChangeListener( event-> {

			if(type.getValue()==QuestionType.StandardQuestion) //if standard question is chosen
			{
				line.setVisible(true);
				options.setVisible(false);
			}
			else 	//if mcq question is chosen
			{
				line.setVisible(false);   
				options.setVisible(true);
			}
		});



		difficulty.setItems(QuestionDifficulty.values());
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(KeyCode.ENTER);

		binder.bindInstanceFields(this);

		save.addClickListener(e -> {
			try {
				this.save();
			} catch (ClassNotFoundException | JSchException | SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		saveVariant.addClickListener(e -> {
			try {
				this.saveAsVariant();
			} catch (ClassNotFoundException | JSchException | SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		delete.addClickListener(e -> {
			try {
				this.delete();
			} catch (ClassNotFoundException | JSchException | SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		back.addClickListener(log -> {
		//Page.getCurrent().reload();
			setVisible(false);
			QuestionGridView.toolbar.setVisible(true);
			QuestionGridView.grid.setVisible(true);
		});

	}

	public void setQuestion(Question question) throws ClassNotFoundException, JSchException, SQLException {
		this.question = question;
		binder.setBean(question); 
		setVisible(true);
		questionText.selectAll();
	}

	private void save() throws ClassNotFoundException, JSchException, SQLException {
		DBConnection dbc = new DBConnection();
		if (!checkFields(question)) {
			Notification.show("Fill all mandatory fields");
		}
		else {
			if (QuestionGridView.CurrentId==null)		
				dbc.sendToDBQuestion(question, false);
			else dbc.sendToDBQuestion(question, true);
			QuestionGridView.updateList();	
			setVisible(false);
		}
	}

	private void saveAsVariant() throws ClassNotFoundException, JSchException, SQLException {
		DBConnection dbc = new DBConnection();
		if (!checkFields(question)) {
			Notification.show("Fill all mandatory fields");
		}
		else {
			dbc.sendToDBQuestion(question, false);
			QuestionGridView.updateList();	
			setVisible(false);
		}
	}
	
	private void delete() throws ClassNotFoundException, JSchException, SQLException {
		DBConnection dbc = new DBConnection();
		dbc.deleteQuestionFromDB(question);
		QuestionGridView.updateList();	
		setVisible(false);
	}
	
	public Boolean checkFields(Question question) {
		if (question.getQuestionText().isEmpty() || question.getMarks().isEmpty() || question.getTime().isEmpty() || question.getCourseCode().isEmpty()) {
			return false;
		}
		return true;
	}
}
