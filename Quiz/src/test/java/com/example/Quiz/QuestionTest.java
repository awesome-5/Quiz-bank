package com.example.Quiz;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class QuestionTest {

	private Long id;
	private String questionText = "";
	private String questionAnswer = "";
	private QuestionType type;
	private String marks = "";
	private QuestionDifficulty difficulty;
	private String time = "";
	private Date lastUsed = null;
	private String variantOf;
	private String courseCode="";
	private int lines=0;
	private String options="";

	@Test
	public void shouldReturnQuestionID() {
		Question q = new Question();
		q.setId(1L);
		assertEquals("Should return 1 for id","1",q.getId().toString());
	}

	@Test
	public void shouldReturnQuestionText() {
		Question q = new Question();
		q.setQuestionText("Test");
		assertEquals("Should return Test for question answer","Test",q.getQuestionText());
		System.out.println("Success");
	}

	@Test
	public void shouldQuestionAnswer() {
		Question q = new Question();
		q.setQuestionAnswer("Test");
		assertEquals("Should return Test for question answer","Test",q.getQuestionAnswer());
		System.out.println("Success");
	}

	@Test
	public void shouldReturnQuestionType() {
		Question q = new Question();
		q.setType(QuestionType.StandardQuestion);
		assertEquals("Should return StandardQuestion","StandardQuestion",q.getType().toString());
		System.out.println("Success");
	}

	@Test
	public void shouldReturnQuestionMarks() {
		Question q = new Question();
		q.setMarks("1");
		assertEquals("Should return 1 for marks","1",q.getMarks());
		System.out.println("Success");
	}

	@Test
	public void shouldReturnQuestionDifficulty() {
		Question q = new Question();
		q.setDifficulty(QuestionDifficulty.Easy);
		assertEquals("Should return Easy for difficulty","Easy",q.getDifficulty().toString());
		System.out.println("Success");
	}

	@Test
	public void shouldReturnQuestionTime() {
		Question q = new Question();
		q.setTime("10");
		assertEquals("Should return 10 for time","10",q.getTime());
		System.out.println("Success");
	}

	@Test
	public void shouldReturnQuestionLastUsed() throws ParseException {
		Question q = new Question();
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		q.setLastUsed(simpleDateFormat.parse("2018-09-09"));
		assertEquals("Should return 2018-09-09 for lastUsed","Sun Sep 09 00:00:00 UST 2018",q.getLastUsed().toString());
		System.out.println("Success");
	}

	@Test
	public void shouldReturnQuestionVariantOf() {
		Question q = new Question();
		q.setVariantOf("1");
		assertEquals("Should return 1 for variant of","1",q.getVariantOf());
		System.out.println("Success");
	}

	@Test
	public void shouldReturnQuestionCourseCode() {
		Question q = new Question();
		HomePage.CurrentCourse="TEST";
		q.setCourseCode("TEST");
		assertEquals("Should return TEST for courseCode","TEST",q.getCourseCode());
		System.out.println("Success");
	}

	@Test
	public void shouldReturnQuestionLines() {
		Question q = new Question();
		q.setLines(5);
		assertEquals("Should return 5 for lines",5,q.getLines());
		System.out.println("Success");
	}

	@Test
	public void shouldReturnQuestionOptions() {
		Question q = new Question();
		q.setOptions("1");
		assertEquals("Should return 1 for options","1",q.getOptions());
		System.out.println("Success");
	}


}
