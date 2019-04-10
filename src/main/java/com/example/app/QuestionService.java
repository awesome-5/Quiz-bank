package com.example.app;
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

	private static QuestionService instance;
	private static final Logger LOGGER = Logger.getLogger(QuestionService.class.getName());

	private final HashMap<Long, Question> contacts = new HashMap<>();
	private long nextId = 0;

	private QuestionService() {
	}


	public static QuestionService getInstance() {
		if (instance == null) {
			instance = new QuestionService();
			try {
				instance.ensureTestData();
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

	/**
	 * Finds all Customer's that match given filter.
	 *
	 * @param stringFilter
	 *            filter that returned objects should match or null/empty string
	 *            if all objects should be returned.
	 * @return list a Customer objects
	 */
	public synchronized List<Question> findAll(String stringFilter) {
		ArrayList<Question> arrayList = new ArrayList<>();
		for (Question contact : contacts.values()) {
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

	/**
	 * Finds all Customer's that match given filter and limits the resultset.
	 *
	 * @param stringFilter
	 *            filter that returned objects should match or null/empty string
	 *            if all objects should be returned.
	 * @param start
	 *            the index of first result
	 * @param maxresults
	 *            maximum result count
	 * @return list a Customer objects
	 */
	public synchronized List<Question> findAll(String stringFilter, int start, int maxresults) {
		ArrayList<Question> arrayList = new ArrayList<>();
		for (Question contact : contacts.values()) {
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

	/**
	 * @return the amount of all customers in the system
	 */
	public synchronized long count() {
		return contacts.size();
	}

	/**
	 * Deletes a customer from a system
	 *
	 * @param value
	 *            the Customer to be deleted
	 */
	public synchronized void delete(Question value) {
		contacts.remove(value.getId());
	}

	/**
	 * Persists or updates customer in the system. Also assigns an identifier
	 * for new Customer instances.
	 *
	 * @param entry
	 */
	public synchronized void save(Question entry) {
		if (entry == null) {
			LOGGER.log(Level.SEVERE,
					"Customer is null. Are you sure you have connected your form to the application?");
			return;
		}
		if (entry.getId() == null) {
			entry.setId(nextId++);
		}
		try {
			entry = (Question) entry.clone();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		contacts.put(entry.getId(), entry);
	}

	/**
	 * @throws SQLException 
	 * @throws JSchException 
	 * @throws ClassNotFoundException 
	 */

	public void ensureTestData() throws ClassNotFoundException, JSchException, SQLException {
	
			if (findAll().isEmpty()) {
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
					save(c);
				}
			}
			
	}
}