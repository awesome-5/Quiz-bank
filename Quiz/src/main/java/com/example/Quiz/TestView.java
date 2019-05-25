package com.example.Quiz;
import java.awt.Checkbox;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

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


public class TestView extends VerticalLayout implements View {
	VerticalLayout wrap =new VerticalLayout();
	HorizontalLayout mainLayout = new HorizontalLayout();
	VerticalLayout draftVLayout = new VerticalLayout();
	VerticalLayout finalVLayout = new VerticalLayout();
	HorizontalLayout draftBar = new HorizontalLayout();
	HorizontalLayout finalBar = new HorizontalLayout();
	HorizontalLayout bottomDraftBar = new HorizontalLayout();
	HorizontalLayout bottomFinalBar = new HorizontalLayout();
	CheckBox draftTestBox = new CheckBox("Tests");
	CheckBox draftExamBox = new CheckBox("Exams");
	CheckBox finalTestBox = new CheckBox("Tests");
	CheckBox finalExamBox = new CheckBox("Exams");
	CheckBox memoBox = new CheckBox("Memo");
	static Grid<Quiz> draftGrid = new Grid<>(Quiz.class);
	static Grid<Quiz> finalGrid = new Grid<>(Quiz.class);
	static Boolean draftTestFlag = true;
	static Boolean draftExamFlag = true;
	static Boolean finalTestFlag = true;
	static Boolean finalExamFlag = true;
	Boolean memoFlag = false;

	Button delete = new Button("Delete Quiz");
	Button edit = new Button("Edit Quiz");
	Button view = new Button("View Quiz");
	Button moveFinal = new Button("Move to Final");
	Button moveDraft = new Button("Move to Draft");
	Button viewFinal = new Button("View Quiz");
	Button exportDraft = new Button("Export Draft");
	Button exportFinal = new Button("Export Final");
	Button backBtn = new Button("Back");
	ArrayList<Question> qTestObj=new ArrayList<Question>();

	static String currentDraftQuiz="";
	static String currentFinalQuiz="";


	public static String getIDS(String s) {
		String ret="";
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

	public static void updateDraftGrid(String s) {
		try {
			DBConnection dbcdraft = new DBConnection(); 
			dbcdraft.readTests(s);
			draftGrid.setItems(dbcdraft.quizObj);
		} catch (ClassNotFoundException | JSchException | SQLException e1) {
		}
	}
	public static void updateFinalGrid(String s) {
		try {
			DBConnection dbcfinal = new DBConnection(); 
			dbcfinal.readTests(s);
			finalGrid.setItems(dbcfinal.quizObj);
		}catch(ClassNotFoundException | JSchException | SQLException e1) {
		}
	}
	@Override
	public void enter(ViewChangeEvent event) {
		updateDraftGrid("SELECT quizName FROM Quiz WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND draftOrFinal=0");
		updateFinalGrid("SELECT quizName FROM Quiz WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND draftOrFinal=1");
		Page.getCurrent().setTitle("Drafts and Final Papers");
		draftBar.setMargin(false);
		finalBar.setMargin(false);
		draftVLayout.setMargin(false);
		draftVLayout.setSizeFull();
		draftVLayout.setWidth("100%");
		finalVLayout.setMargin(false);
		finalVLayout.setWidth("100%");
		bottomDraftBar.setMargin(false);
		bottomFinalBar.setMargin(false);
		mainLayout.setMargin(false);
		mainLayout.setSizeFull();
		draftBar.addComponents(draftTestBox,draftExamBox);
		finalBar.addComponents(finalTestBox,finalExamBox);
		draftBar.setComponentAlignment(draftTestBox, Alignment.MIDDLE_CENTER );	
		draftBar.setComponentAlignment(draftExamBox, Alignment.MIDDLE_CENTER );	
		finalBar.setComponentAlignment(finalTestBox, Alignment.MIDDLE_CENTER );	
		finalBar.setComponentAlignment(finalExamBox, Alignment.MIDDLE_CENTER );	

		draftVLayout.addComponents(draftBar,draftGrid,bottomDraftBar);
		finalVLayout.addComponents(finalBar,finalGrid,bottomFinalBar,memoBox);
		bottomDraftBar.addComponents(view,edit,delete,moveFinal,exportDraft);
		bottomFinalBar.addComponents(viewFinal,moveDraft,exportFinal,backBtn);
		mainLayout.addComponents(draftVLayout,finalVLayout);
		delete.setEnabled(false);
		edit.setEnabled(false);
		view.setEnabled(false);
		moveFinal.setEnabled(false);
		moveDraft.setEnabled(false);
		viewFinal.setEnabled(false);
		exportDraft.setEnabled(false);
		exportFinal.setEnabled(false);
		addComponent(mainLayout);
		wrap.setSizeFull();
		draftTestBox.setValue(true);
		draftExamBox.setValue(true);
		finalTestBox.setValue(true);
		finalExamBox.setValue(true);
		draftGrid.setCaption("Draft Papers");
		finalGrid.setCaption("Final Papers");
		draftGrid.setSizeFull();
		finalGrid.setSizeFull();
		draftGrid.setBodyRowHeight(60);
		draftGrid.setHeightMode(HeightMode.ROW);
		finalGrid.setBodyRowHeight(60);
		finalGrid.setHeightMode(HeightMode.ROW);

		// updates grids if boxes are ticked, exam, test or both
		draftTestBox.addValueChangeListener(eent ->{
			draftTestFlag=draftTestBox.getValue();
			updateDraftGrid();
		});

		draftExamBox.addValueChangeListener(eent ->{
			draftExamFlag=draftExamBox.getValue();
			updateDraftGrid();
		});

		finalTestBox.addValueChangeListener(eent ->{
			finalTestFlag=finalTestBox.getValue();
			updateFinalGrid();
		});
		memoBox.addValueChangeListener(eent ->{
			memoFlag=memoBox.getValue();
		});

		finalExamBox.addValueChangeListener(eent ->{
			finalExamFlag=finalExamBox.getValue();
			updateFinalGrid();
		});


		draftGrid.addItemClickListener(e ->
		{
			currentDraftQuiz=e.getItem().getQuizName();
		});

		finalGrid.addItemClickListener(e ->
		{
			currentFinalQuiz=e.getItem().getQuizName();
		});


		draftGrid.addSelectionListener(e ->
		{
			if (draftGrid.getSelectedItems().size()==0)
			{
				delete.setEnabled(false);
				edit.setEnabled(false);
				view.setEnabled(false);
				moveFinal.setEnabled(false);
				exportDraft.setEnabled(false);
			}
			else {
				delete.setEnabled(true);
				edit.setEnabled(true);
				view.setEnabled(true);
				moveFinal.setEnabled(true);
				exportDraft.setEnabled(true);
			}
		});


		finalGrid.addSelectionListener(e ->
		{

			if (finalGrid.getSelectedItems().size()==0)
			{
				viewFinal.setEnabled(false);
				moveDraft.setEnabled(false);
				exportFinal.setEnabled(false);

			}
			else {
				viewFinal.setEnabled(true);
				moveDraft.setEnabled(true);
				exportFinal.setEnabled(true);

			}


		});


		delete.addClickListener(e->
		{
			try {
				DBConnection delete = new DBConnection(); 
				delete.deleteQuiz(currentDraftQuiz);
				updateDraftGrid();
			} catch (ClassNotFoundException | JSchException | SQLException e1) {
			}

		});

		//moves to final grid
		moveFinal.addClickListener(e->
		{
			try {
				DBConnection moveToFinal = new DBConnection(); 
				moveToFinal.moveFinal(currentDraftQuiz);
			} catch (ClassNotFoundException | JSchException | SQLException e1) {
			}
			updateLastUsed();

			updateDraftGrid("SELECT quizName FROM Quiz WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND draftOrFinal=0");
			updateFinalGrid("SELECT quizName FROM Quiz WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND draftOrFinal=1");

		});
		//moves to draft grid
		moveDraft.addClickListener(e->
		{
			try {
				DBConnection moveToDraft = new DBConnection(); 
				moveToDraft.moveDraft(currentFinalQuiz);
			} catch (ClassNotFoundException | JSchException | SQLException e1) {
			}

			updateDraftGrid("SELECT quizName FROM Quiz WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND draftOrFinal=0");
			updateFinalGrid("SELECT quizName FROM Quiz WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND draftOrFinal=1");

		});


		view.addClickListener(e->
		{
			MyUI.navigator.navigateTo(MyUI.TESTQUESTIONS);

		});
		edit.addClickListener(e->
		{
			MyUI.navigator.navigateTo(MyUI.EDITDRAGVIEW);
		});

		viewFinal.addClickListener(e->
		{
			MyUI.navigator.navigateTo(MyUI.TESTQUESTIONS);

		});

		exportDraft.addClickListener(e->
		{

			DBConnection dbc = new DBConnection();
			latexTemplate tex = new latexTemplate ();
			String[] ids;
			ids=getIDS("SELECT questionIDS FROM Quiz WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND quizName='"+TestView.currentDraftQuiz+"'").split(",");

			for (int i=0;i<ids.length;i++)
			{
				System.out.print("id is " +ids[i]);
				qTestObj.add(getQuestions("SELECT * FROM Question WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND questionID='"+ids[i]+"'"));
				System.out.println("Q test obj"+qTestObj);
			}
			//String sb = "Ezpz";
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("/home/me/Documents"));
			int retrival = chooser.showSaveDialog(null);
			if (retrival == JFileChooser.APPROVE_OPTION) {
				try {
					FileWriter fw = new FileWriter(chooser.getSelectedFile()+".tex");
					if (dbc.readTestOrExam("SELECT testOrExam FROM Quiz WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND quizName='"+currentDraftQuiz+"'")==0){
						fw.write(tex.coverPageTestDraft);
					}else {
						fw.write(tex.coverPageExamDraft);
					}	
					fw.write("\\begin{enumerate}");
					fw.write("\n");
					fw.write("\n");
					if (!memoFlag) {
					for (int i=0;i<ids.length;i++)
					{
						if (qTestObj.get(i).getType()==QuestionType.MCQ) {
							String Options =dbc.readOptions("SELECT options FROM MCQ WHERE questionID= '"+qTestObj.get(i).getId()+"'");
							qTestObj.get(i).setOptions(Options);
							fw.write("\\item "+qTestObj.get(i).getQuestionText().toString());
							fw.write("\\mk{"+qTestObj.get(i).getMarks()+"}");
							fw.write("\n");
							fw.write("\\begin{enumerate}");
							fw.write("\n");
							String [] temp = Options.split(",");
							for (int j =0; j<temp.length;j++) {
								fw.write("\\item " +temp[j]);
								fw.write("\n");
							}
							fw.write("\\end{enumerate}");
							fw.write("\n");
							fw.write("\n");	
							
						}
						else {
						String line =dbc.readLines("SELECT line FROM Standard WHERE questionID= '"+qTestObj.get(i).getId()+"'");
						qTestObj.get(i).setLines(Integer.parseInt(line));
						fw.write("\\item "+qTestObj.get(i).getQuestionText().toString());
						fw.write("\\mk{"+qTestObj.get(i).getMarks()+"}");
						fw.write("\n");
						//fw.write("\\item "+line);
						for (int j =0; j<qTestObj.get(i).getLines();j++) {
							fw.write("\\ansline");
						}						
						fw.write("\n");
						fw.write("\n");	
					}
					}
					fw.write("\\end{enumerate}");
					fw.write("\n");
					fw.write("\\end{document}");
					fw.close();
					}else {

						for (int i=0;i<ids.length;i++)
						{
							if (qTestObj.get(i).getType()==QuestionType.MCQ) {
								String Options =dbc.readOptions("SELECT options FROM MCQ WHERE questionID= '"+qTestObj.get(i).getId()+"'");
								qTestObj.get(i).setOptions(Options);
								fw.write("\\item "+qTestObj.get(i).getQuestionText().toString());
								fw.write("\\mk{"+qTestObj.get(i).getMarks()+"}");
								fw.write("\n");
								fw.write("\\begin{enumerate}");
								fw.write("\n");
								String [] temp = Options.split(",");
								for (int j =0; j<temp.length;j++) {
									fw.write("\\item " +temp[j]);
									fw.write("\n");
								}
								fw.write("\\item "+qTestObj.get(i).getQuestionAnswer().toString());
								fw.write("\\end{enumerate}");
								fw.write("\\textbf{"+qTestObj.get(i).getQuestionAnswer().toString()+"}");
								fw.write("\n");
								fw.write("\n");	
								
							}
							else {
							String line =dbc.readLines("SELECT line FROM Standard WHERE questionID= '"+qTestObj.get(i).getId()+"'");
							qTestObj.get(i).setLines(Integer.parseInt(line));
							fw.write("\\item "+qTestObj.get(i).getQuestionText().toString());
							fw.write("\\mk{"+qTestObj.get(i).getMarks()+"}");
							fw.write("\\linebreak");
							fw.write("\n");
							//fw.write("\\item "+line);
							fw.write("\\textbf{"+qTestObj.get(i).getQuestionAnswer().toString()+"}");						
							fw.write("\n");
							fw.write("\n");	
						}
						}
						fw.write("\\end{enumerate}");
						fw.write("\n");
						fw.write("\\end{document}");
						fw.close();
						
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			String[] command =
				{
						"cmd",
						//"bash",
				};
			Process p;
			try {
				p = Runtime.getRuntime().exec(command);
				new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
				new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
				PrintWriter stdin = new PrintWriter(p.getOutputStream());
				stdin.println("cd "+chooser.getCurrentDirectory());
				stdin.println("pdflatex --file-line-error --synctex=1 --shell-escape --interaction=nonstopmode");
				stdin.println(chooser.getSelectedFile().getName());
				stdin.close();
				p.waitFor();
			} catch (Exception ex) {
				ex.printStackTrace();
			}



		});

		exportFinal.addClickListener(e->
		{

			DBConnection dbc = new DBConnection();
			latexTemplate tex = new latexTemplate ();
			String[] ids;
			ids=getIDS("SELECT questionIDS FROM Quiz WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND quizName='"+currentFinalQuiz+"'").split(",");

			for (int i=0;i<ids.length;i++)
			{
				System.out.print("id is " +ids[i]);
				qTestObj.add(getQuestions("SELECT * FROM Question WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND questionID='"+ids[i]+"'"));
				System.out.println("Q test obj"+qTestObj);
			}
			//String sb = "Ezpz";
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("/home/me/Documents"));
			int retrival = chooser.showSaveDialog(null);
			if (retrival == JFileChooser.APPROVE_OPTION) {
				try {
					FileWriter fw = new FileWriter(chooser.getSelectedFile()+".tex");
					if (dbc.readTestOrExam("SELECT testOrExam FROM Quiz WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND quizName='"+currentFinalQuiz+"'")==0){
						fw.write(tex.coverPageTestFinal);
					}else {
						fw.write(tex.coverPageExamFinal);
					}	
					fw.write("\\begin{enumerate}");
					fw.write("\n");
					fw.write("\n");
					if (!memoFlag) {
					for (int i=0;i<ids.length;i++)
					{
						if (qTestObj.get(i).getType()==QuestionType.MCQ) {
							String Options =dbc.readOptions("SELECT options FROM MCQ WHERE questionID= '"+qTestObj.get(i).getId()+"'");
							qTestObj.get(i).setOptions(Options);
							fw.write("\\item "+qTestObj.get(i).getQuestionText().toString());
							fw.write("\\mk{"+qTestObj.get(i).getMarks()+"}");
							fw.write("\n");
							fw.write("\\begin{enumerate}");
							fw.write("\n");
							String [] temp = Options.split(",");
							for (int j =0; j<temp.length;j++) {
								fw.write("\\item " +temp[j]);
								fw.write("\n");
							}
							fw.write("\\end{enumerate}");
							fw.write("\n");
							fw.write("\n");	
							
						}
						else {
						String line =dbc.readLines("SELECT line FROM Standard WHERE questionID= '"+qTestObj.get(i).getId()+"'");
						qTestObj.get(i).setLines(Integer.parseInt(line));
						fw.write("\\item "+qTestObj.get(i).getQuestionText().toString());
						fw.write("\\mk{"+qTestObj.get(i).getMarks()+"}");
						fw.write("\n");
						//fw.write("\\item "+line);
						for (int j =0; j<qTestObj.get(i).getLines();j++) {
							fw.write("\\ansline");
						}						
						fw.write("\n");
						fw.write("\n");	
					}
					}
					fw.write("\\end{enumerate}");
					fw.write("\n");
					fw.write("\\end{document}");
					fw.close();
					}else {

						for (int i=0;i<ids.length;i++)
						{
							if (qTestObj.get(i).getType()==QuestionType.MCQ) {
								String Options =dbc.readOptions("SELECT options FROM MCQ WHERE questionID= '"+qTestObj.get(i).getId()+"'");
								qTestObj.get(i).setOptions(Options);
								fw.write("\\item "+qTestObj.get(i).getQuestionText().toString());
								fw.write("\\mk{"+qTestObj.get(i).getMarks()+"}");
								fw.write("\n");
								fw.write("\\begin{enumerate}");
								fw.write("\n");
								String [] temp = Options.split(",");
								for (int j =0; j<temp.length;j++) {
									fw.write("\\item " +temp[j]);
									fw.write("\n");
								}
								fw.write("\\item "+qTestObj.get(i).getQuestionAnswer().toString());
								fw.write("\\end{enumerate}");
								fw.write("\\textbf{"+qTestObj.get(i).getQuestionAnswer().toString()+"}");
								fw.write("\n");
								fw.write("\n");	
								
							}
							else {
							String line =dbc.readLines("SELECT line FROM Standard WHERE questionID= '"+qTestObj.get(i).getId()+"'");
							qTestObj.get(i).setLines(Integer.parseInt(line));
							fw.write("\\item "+qTestObj.get(i).getQuestionText().toString());
							fw.write("\\mk{"+qTestObj.get(i).getMarks()+"}");
							fw.write("\\linebreak");
							fw.write("\n");
							//fw.write("\\item "+line);
							fw.write("\\textbf{"+qTestObj.get(i).getQuestionAnswer().toString()+"}");						
							fw.write("\n");
							fw.write("\n");	
						}
						}
						fw.write("\\end{enumerate}");
						fw.write("\n");
						fw.write("\\end{document}");
						fw.close();
						
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			String[] command =
				{
						"cmd",
						//"bash",
				};
			Process p;
			try {
				p = Runtime.getRuntime().exec(command);
				new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
				new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
				PrintWriter stdin = new PrintWriter(p.getOutputStream());
				stdin.println("cd "+chooser.getCurrentDirectory());
				stdin.println("pdflatex --file-line-error --synctex=1 --shell-escape --interaction=nonstopmode");
				stdin.println(chooser.getSelectedFile().getName());
				stdin.close();
				p.waitFor();
			} catch (Exception ex) {
				ex.printStackTrace();
			}



		});
		backBtn.addClickListener(e -> {	
			MyUI.navigator.navigateTo(MyUI.DRAGVIEW);
			Page.getCurrent().reload();		
		});

	}


	public static void updateDraftGrid()
	{
		if (draftTestFlag&&!draftExamFlag)
		{
			updateDraftGrid("SELECT quizName FROM Quiz WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND draftOrFinal=0 AND testOrExam=0");
		}
		else if (!draftTestFlag&&draftExamFlag)
		{
			updateDraftGrid("SELECT quizName FROM Quiz WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND draftOrFinal=0 AND testOrExam=1");
		}
		else if (draftTestFlag&&draftExamFlag)
		{
			updateDraftGrid("SELECT quizName FROM Quiz WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND draftOrFinal=0");
		}
		else if (!draftTestFlag&&!draftExamFlag)
		{
			updateDraftGrid("SELECT * FROM Quiz WHERE 1 = 0");
		}

	}

	public static void updateFinalGrid()
	{
		if (finalTestFlag&&!finalExamFlag)
		{
			updateFinalGrid("SELECT quizName FROM Quiz WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND draftOrFinal=1 AND testOrExam=0");
		}
		else if (!finalTestFlag&&finalExamFlag)
		{
			updateFinalGrid("SELECT quizName FROM Quiz WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND draftOrFinal=1 AND testOrExam=1");
		}
		else if (finalTestFlag&&finalExamFlag)
		{
			updateFinalGrid("SELECT quizName FROM Quiz WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND draftOrFinal=1");
		}
		else if (!finalTestFlag&&!finalExamFlag)
		{
			updateFinalGrid("SELECT * FROM Quiz WHERE 1 = 0");
		}

	}
	public void updateLastUsed()
	{
		String[] ids;
		ids=TestQuestionView.getIDS("SELECT questionIDS FROM Quiz WHERE username ='"+ LoginView.loggedInUser + "' AND courseCode='"+HomePage.CurrentCourse+"' AND quizName='"+currentDraftQuiz+"'").split(",");
		for (int i=0;i<ids.length;i++)
		{
			System.out.print("id is " +ids[i]);
			changeQuestions(ids[i]);
		}
		try {
			DBConnection changeQuizLastUsed = new DBConnection();
			changeQuizLastUsed.setQuizLastUsed(currentDraftQuiz);
		} catch (ClassNotFoundException | JSchException | SQLException e1) {
		}
	}
	public void changeQuestions(String id) {
		try {
			DBConnection dbcQuestionChange = new DBConnection();
			dbcQuestionChange.setLastUsed(id);
		} catch (ClassNotFoundException | JSchException | SQLException e1) {
		}
	}
}


