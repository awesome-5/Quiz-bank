package com.example.Quiz;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jcraft.jsch.JSchException;


public class QuestionService {

	public QuestionService instance;
	private final Logger LOGGER = Logger.getLogger(QuestionService.class.getName());

	private final HashMap<Long, Question> questions = new HashMap<>();

	public QuestionService() {
	}

	public static QuestionService getInstance() {
		QuestionService instance = new QuestionService().instance;
		if (instance == null) {
			instance = new QuestionService();
			try {
				instance.populateGrid();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;
	}

	public synchronized List<Question> findAll() {
		return findAll(null);
	}
	
	public synchronized List<Question> findAll(String stringFilter) {
		ArrayList<Question> arrayList = new ArrayList<>();
		for (Question contact : questions.values()) {
			try {
				boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(contact.clone());
				}
			} catch (CloneNotSupportedException ex) {
				Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Collections.sort(arrayList, new Comparator<Question>() {

			@Override
			public int compare(Question o1, Question o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});
		return arrayList;
	}

	public synchronized List<Question> findAll(String stringFilter, int start, int maxresults) {
		ArrayList<Question> arrayList = new ArrayList<>();
		for (Question contact : questions.values()) {
			try {
				boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(contact.clone());
				}
			} catch (CloneNotSupportedException ex) {
				Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Collections.sort(arrayList, new Comparator<Question>() {

			@Override
			public int compare(Question o1, Question o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});
		int end = start + maxresults;
		if (end > arrayList.size()) {
			end = arrayList.size();
		}
		return arrayList.subList(start, end);
	}

	public void populateGrid() throws ClassNotFoundException, JSchException, SQLException {
	
			if (findAll().isEmpty()) {
				System.out.println("in populate grid");
				DBConnection dbc = new DBConnection();
				ArrayList<Question> results=new ArrayList<>();
				results=dbc.readDBQuestion();

				for (int i =0;i<results.size();i++) {
					Question c = new Question();
					c.setId(results.get(i).getId());
					c.setQuestionText(results.get(i).getQuestionText());
					c.setQuestionAnswer(results.get(i).getQuestionAnswer());
					c.setType(results.get(i).getType());
					c.setMarks(results.get(i).getMarks());
					c.setDifficulty(results.get(i).getDifficulty());
					c.setTime(results.get(i).getTime());
					c.setLastUsed(results.get(i).getLastUsed());
					c.setVariantOf(results.get(i).getVariantOf());
					c.setQuestionAnswer(results.get(i).getQuestionAnswer());
					c.setCourseCode(results.get(i).getCourseCode());
//					c.setLines(results.get(i).getLines());
//					c.setOptions(results.get(i).getOptions());
					
					questions.put(c.getId(), c);
				}
			}
			
	}
}