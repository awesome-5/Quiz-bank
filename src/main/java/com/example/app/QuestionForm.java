package com.example.app;

import java.sql.SQLException;
import java.util.ArrayList;
import com.jcraft.jsch.JSchException;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.event.selection.SingleSelectionEvent;
import com.vaadin.event.selection.SingleSelectionListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
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
	private QuestionService service = QuestionService.getInstance();
	private Question question;
	private MainView myUI;
	private Binder<Question> binder = new Binder<>(Question.class);

	public QuestionForm(MainView mainView) {
		this.myUI = mainView;
		questionText.setRequiredIndicatorVisible(true);
		questionText.setWidth("100%");
		questionAnswer.setWidth("100%");
		marks.setRequiredIndicatorVisible(true);
		difficulty.setRequiredIndicatorVisible(true);
		time.setRequiredIndicatorVisible(true);
		type.setRequiredIndicatorVisible(true);
		line.setVisible(false);
		options.setVisible(false);

		setSizeUndefined();
		
		HorizontalLayout buttons = new HorizontalLayout(save);
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
	}

	public void setQuestion(Question question) throws ClassNotFoundException, JSchException, SQLException {
		this.question = question;
		binder.setBean(question); 
		setVisible(true);
		questionText.selectAll();
	}

	private void save() throws ClassNotFoundException, JSchException, SQLException {
		service.save(question);
		DBConnection dbc = new DBConnection();
		dbc.sendToDBQuestion(question);
		MainView.updateList();
		setVisible(false);
	}
}
